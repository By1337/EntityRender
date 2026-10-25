package dev.by1337.entityrender.core;

import io.papermc.paper.event.packet.*;
import org.bukkit.*;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.function.Supplier;

/** One sampler, player index and transport worker shared by all renderers of a plugin owner. */
final class RenderService implements Listener {
    private static final Map<Plugin, RenderService> SERVICES = new IdentityHashMap<>(); // main only
    private final Plugin plugin;
    private final ExecutorService worker;
    private final Set<PaperEntityRenderer> renderers = ConcurrentHashMap.newKeySet();
    private final Map<Integer, BaseVirtualEntity> targets = new ConcurrentHashMap<>();
    private final Map<World, WorldResources> worlds = new ConcurrentHashMap<>();
    private final Map<UUID, Session> sessions = new ConcurrentHashMap<>(); // main writes, network captures session tokens
    private final PlayerDirectory players = new PlayerDirectory(); // worker only
    private final Set<CompletableFuture<?>> mainRequests = ConcurrentHashMap.newKeySet();
    private final AtomicBoolean closed = new AtomicBoolean();
    private final InteractionHooks interactions;
    private final BukkitTask ticker;
    private long epoch;
    private final List<ChunkChange> chunkChanges = new ArrayList<>(); // main only, batched per tick
    final EntityIdPool ids;
    volatile Thread workerThread;

    static RenderService acquire(Plugin plugin) {
        if (!Bukkit.isPrimaryThread()) throw new IllegalStateException("Initialize EntityRender on main");
        return SERVICES.computeIfAbsent(plugin, RenderService::new);
    }

    private RenderService(Plugin plugin) {
        this.plugin = plugin;
        worker = Executors.newSingleThreadExecutor(runnable -> {
            var thread = new Thread(runnable, "EntityRender-" + plugin.getName());
            thread.setDaemon(true);
            workerThread = thread;
            return thread;
        });
        ids = new EntityIdPool(this);
        for (var world : Bukkit.getWorlds()) worlds.put(world, new WorldResources(this, world));
        interactions = new InteractionHooks(this, task -> {
            onMain(() -> {
                task.run();
                return null;
            }).whenComplete((ignored, error) -> {
                if (error != null && !closed.get()) plugin.getLogger().log(java.util.logging.Level.SEVERE, "EntityRender interaction callback failed", error);
            });
        });
        Bukkit.getPluginManager().registerEvents(this, plugin);
        for (var player : Bukkit.getOnlinePlayers()) bind(player);
        sample();
        ticker = Bukkit.getScheduler().runTaskTimer(plugin, this::sample, 1, 1);
    }

    void checkOpen() {
        if (closed.get()) throw new IllegalStateException("Render service is closed");
    }

    void register(PaperEntityRenderer renderer) {
        checkOpen();
        renderers.add(renderer);
    }

    void target(BaseVirtualEntity entity) {
        targets.put(entity.getId(), entity);
    }

    void untarget(BaseVirtualEntity entity) {
        targets.remove(entity.getId(), entity);
    }

    boolean containsEntity(int id) {
        var entity = targets.get(id);
        return entity != null && !entity.isClosed();
    }

    java.util.function.BooleanSupplier interactionGuard(Player player) {
        var session = sessions.get(player.getUniqueId());
        return () -> session != null && session.player == player && session.valid.get() && !closed.get();
    }

    void dispatchInteraction(Player player, int id, dev.by1337.entityrender.api.EntityInteraction.Type type, org.bukkit.inventory.EquipmentSlot hand, org.bukkit.util.Vector hit, boolean secondary) {
        var entity = targets.get(id);
        if (entity != null && !entity.owner.isClosed()) entity.interact(player, type, hand, hit, secondary);
    }

    WorldResources world(World world) {
        var result = worlds.get(world);
        if (result == null || result.world != world) throw new IllegalArgumentException("World is not registered with EntityRender: " + world);
        return result;
    }

    <T> CompletableFuture<T> onMain(Supplier<T> action) {
        var future = new CompletableFuture<T>();
        if (closed.get()) {
            future.completeExceptionally(new IllegalStateException("Render service is closed"));
            return future;
        }
        mainRequests.add(future);
        if (closed.get()) {
            mainRequests.remove(future);
            future.completeExceptionally(new IllegalStateException("Render service is closed"));
            return future;
        }
        Runnable task = () -> {
            try {
                if (closed.get()) throw new IllegalStateException("Render service is closed");
                future.complete(action.get());
            } catch (Throwable error) {
                future.completeExceptionally(error);
            } finally {
                mainRequests.remove(future);
            }
        };
        if (Bukkit.isPrimaryThread()) task.run();
        else try {
            Bukkit.getScheduler().runTask(plugin, task);
        } catch (RuntimeException error) {
            mainRequests.remove(future);
            future.completeExceptionally(error);
        }
        return future;
    }

    CompletableFuture<Void> submit(Runnable task) {
        var result = new CompletableFuture<Void>();
        try {
            worker.execute(() -> {
                try {
                    task.run();
                    result.complete(null);
                } catch (Throwable error) {
                    result.completeExceptionally(error);
                    plugin.getLogger().log(java.util.logging.Level.SEVERE, "EntityRender transport failure", error);
                }
            });
        } catch (RejectedExecutionException error) {
            result.completeExceptionally(error);
        }
        return result;
    }

    CompletableFuture<Void> publish(PaperEntityRenderer renderer, List<PublishedEntity> frames, List<SceneTracker.Snapshot> scenes) {
        return submit(() -> {
            if (renderer.isClosed()) return;
            renderer.transport.accept(frames, scenes);
            renderer.transport.render(players);
        });
    }

    private Session session(Player player) {
        UUID id = player.getUniqueId();
        World world = player.getWorld();
        var old = sessions.get(id);
        if (old != null && old.player == player && old.world == world) return old;
        if (old != null) old.valid.set(false);
        var valid = new AtomicBoolean(true);
        var connection = ((CraftPlayer) player).getHandle().connection.connection;
        java.util.function.Consumer<net.minecraft.network.protocol.Packet<?>> sender = packet -> {
            if (!valid.get()) return;
            try {
                connection.channel.eventLoop().execute(() -> {
                    if (valid.get() && connection.isConnected()) connection.send(packet, null, false);
                });
            } catch (RejectedExecutionException error) {
                if (connection.isConnected()) throw error;
            }
        };
        var created = new Session(player, world, ++epoch, Set.copyOf(player.getSentChunkKeys()), sender, valid);
        sessions.put(id, created);
        return created;
    }

    private void sample() {
        if (closed.get()) return;
        var samples = new ArrayList<PlayerDirectory.Sample>();
        var present = new HashSet<UUID>();
        for (var player : Bukkit.getOnlinePlayers()) {
            var session = session(player);
            var location = player.getLocation();
            UUID id = player.getUniqueId();
            present.add(id);
            samples.add(new PlayerDirectory.Sample(player, id, session.world, location.getX(), location.getY(), location.getZ(), session.epoch, session.initial, session.sender, session.valid));
        }
        sessions.keySet().retainAll(present);
        var chunks = List.copyOf(chunkChanges);
        chunkChanges.clear();
        submit(() -> {
            players.replace(samples);
            for (var change : chunks) players.chunk(change.player(), change.epoch(), change.key(), change.loaded());
            for (var renderer : renderers) if (!renderer.isClosed()) try {
                if (renderer.automatic) renderer.flush();
                else renderer.transport.render(players);
            } catch (RuntimeException error) {
                if (!renderer.isClosed()) plugin.getLogger().log(java.util.logging.Level.SEVERE, "EntityRender visibility/publication failure", error);
            }
        });
    }

    private void renderAll() {
        for (var renderer : renderers) if (!renderer.isClosed()) try {
            renderer.transport.render(players);
        } catch (RuntimeException error) {
            plugin.getLogger().log(java.util.logging.Level.SEVERE, "EntityRender visibility failure", error);
        }
    }

    private void bind(Player player) {
        interactions.bind(player, ((CraftPlayer) player).getHandle().connection.connection.channel);
    }

    private void chunk(Player player, long key, boolean loaded) {
        var session = session(player);
        UUID id = player.getUniqueId();
        chunkChanges.add(new ChunkChange(id, session.epoch, key, loaded));
    }

    @EventHandler
    public void onChunkLoad(PlayerChunkLoadEvent event) {
        if (event.getWorld() == event.getPlayer().getWorld()) chunk(event.getPlayer(), event.getChunk().getChunkKey(), true);
    }

    @EventHandler
    public void onChunkUnload(PlayerChunkUnloadEvent event) {
        if (event.getWorld() == event.getPlayer().getWorld()) chunk(event.getPlayer(), event.getChunk().getChunkKey(), false);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        bind(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        UUID id = event.getPlayer().getUniqueId();
        var removed = sessions.remove(id);
        if (removed != null) removed.valid.set(false);
        interactions.unbind(id);
        submit(() -> {
            players.remove(id);
            renderAll();
        });
    }

    private void reset(Player player) {
        UUID id = player.getUniqueId();
        var removed = sessions.remove(id);
        if (removed != null) removed.valid.set(false);
        submit(() -> {
            players.remove(id);
            renderAll();
        });
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        reset(event.getPlayer());
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        reset(event.getPlayer());
    }

    @EventHandler
    public void onWorldLoad(WorldLoadEvent event) {
        worlds.put(event.getWorld(), new WorldResources(this, event.getWorld()));
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onWorldUnload(org.bukkit.event.world.WorldUnloadEvent event) {
        var resources = worlds.remove(event.getWorld());
        if (resources != null) resources.invalidate();
        for (var renderer : renderers) renderer.closeWorld(event.getWorld());
    }

    @EventHandler
    public void onDisable(PluginDisableEvent event) {
        if (event.getPlugin() == plugin) shutdown();
    }

    void release(PaperEntityRenderer renderer) {
        submit(() -> renderer.transport.close(players));
        renderers.remove(renderer);
        // Keep the shared ID/default caches for the plugin lifetime, including between animations.
    }

    private void shutdown() {
        if (!closed.compareAndSet(false, true)) return;
        ticker.cancel();
        interactions.close();
        HandlerList.unregisterAll(this);
        SERVICES.remove(plugin);
        for (var future : mainRequests) future.completeExceptionally(new IllegalStateException("Owner plugin disabled"));
        mainRequests.clear();
        for (var renderer : List.copyOf(renderers)) renderer.close();
        submit(() -> {
            for (var renderer : renderers) renderer.transport.close(players);
        }).join();
        renderers.clear();
        targets.clear();
        worlds.clear();
        worker.shutdown();
    }

    void send(Player player, net.minecraft.network.protocol.Packet<?> packet) {
        var view = players.route(player);
        if (view != null && view.sample.valid().get()) view.sample.sender().accept(packet);
    }

    CompletableFuture<Void> fence() {
        return submit(() -> {
        });
    }

    private record ChunkChange(UUID player, long epoch, long key, boolean loaded) {
    }

    private record Session(Player player, World world, long epoch, Set<Long> initial,
        java.util.function.Consumer<net.minecraft.network.protocol.Packet<?>> sender, AtomicBoolean valid) {
    }
}
