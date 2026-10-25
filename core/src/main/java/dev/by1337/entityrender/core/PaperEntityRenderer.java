package dev.by1337.entityrender.core;

import dev.by1337.entityrender.api.EntityRenderer;
import dev.by1337.entityrender.api.entity.*;
import org.bukkit.*;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import java.util.*;
import java.util.function.ToIntFunction;

public final class PaperEntityRenderer implements EntityRenderer, Listener {
    private final Thread thread = Thread.currentThread();
    private final PacketSink sink;
    final SpawnPackets spawnPackets = new SpawnPackets();
    private final ToIntFunction<World> ids;
    private final Map<Integer, BaseVirtualEntity> entities = new java.util.concurrent.ConcurrentHashMap<>();
    private final ArrayDeque<BaseVirtualEntity> pending = new ArrayDeque<>();
    private final Map<org.bukkit.entity.EntityType, dev.by1337.entityrender.core.metadata.MetadataLayout> templates = new EnumMap<>(org.bukkit.entity.EntityType.class);
    private final Map<org.bukkit.entity.EntityType, Integer> spawnData = new EnumMap<>(org.bukkit.entity.EntityType.class);
    private Plugin plugin;
    private BukkitTask ticker;
    private volatile boolean closed;
    private InteractionHooks interactions;
    private final Set<SceneTracker> trackers = new HashSet<>();
    private final Map<Integer, SceneTracker> memberships = new HashMap<>();
    private final java.util.function.Function<World, List<org.bukkit.entity.Player>> players;

    PaperEntityRenderer(PacketSink sink, ToIntFunction<World> ids) {
        this(sink, ids, World::getPlayers);
    }

    PaperEntityRenderer(PacketSink sink, ToIntFunction<World> ids, java.util.function.Function<World, List<org.bukkit.entity.Player>> players) {
        this.sink = sink;
        this.ids = ids;
        this.players = players;
    }

    @Override
    public dev.by1337.entityrender.api.ViewerTracker createTracker(Location center, double range) {
        checkOpen();
        var tracker = new SceneTracker(this, center, range);
        trackers.add(tracker);
        return tracker;
    }

    void attachTracker(BaseVirtualEntity entity, SceneTracker tracker) {
        var previous = memberships.putIfAbsent(entity.getId(), tracker);
        if (previous != null && previous != tracker) throw new IllegalArgumentException("Entity already belongs to another tracker");
    }

    void detachTracker(BaseVirtualEntity entity, SceneTracker tracker) {
        memberships.remove(entity.getId(), tracker);
    }

    void forgetTracker(SceneTracker tracker) {
        trackers.remove(tracker);
    }

    public static EntityRenderer create(Plugin owner, boolean automatic) {
        Objects.requireNonNull(owner);
        if (!Bukkit.isPrimaryThread()) throw new IllegalStateException("Create EntityRender on the server thread");
        if (!owner.isEnabled()) throw new IllegalStateException("Owner plugin is not enabled");
        if (!Bukkit.getMinecraftVersion().equals(TargetVersion.MINECRAFT_VERSION)) throw new IllegalStateException("This EntityRender build requires Paper " + TargetVersion.MINECRAFT_VERSION + "; got " + Bukkit.getMinecraftVersion());
        var renderer = new PaperEntityRenderer((player, packet) -> ((CraftPlayer) player).getHandle().connection.connection.send(packet, null, false),
                world -> Bukkit.getUnsafe().nextEntityId());
        renderer.plugin = owner;
        try {
            Bukkit.getPluginManager().registerEvents(renderer, owner);
            renderer.interactions = new InteractionHooks(renderer, task -> {
                if (renderer.closed) return;
                try {
                    Bukkit.getScheduler().runTask(owner, task);
                } catch (org.bukkit.plugin.IllegalPluginAccessException disabled) {
                    if (!renderer.closed && owner.isEnabled()) throw disabled;
                }
            });
            for (var player : Bukkit.getOnlinePlayers()) renderer.bindInteractions(player);
            if (automatic) renderer.ticker = Bukkit.getScheduler().runTaskTimer(owner, renderer::flush, 1, 1);
            return renderer;
        } catch (RuntimeException failure) {
            renderer.close();
            throw failure;
        }
    }

    void checkThread() {
        if (Thread.currentThread() != thread) throw new IllegalStateException("EntityRender is confined to its owning server thread");
    }

    void checkOpen() {
        checkThread();
        if (closed) throw new IllegalStateException("Renderer is closed");
    }

    void send(org.bukkit.entity.Player player, net.minecraft.network.protocol.Packet<?> packet) {
        sink.send(player, packet);
    }

    boolean containsEntity(int id) {
        return !closed && entities.containsKey(id);
    }

    void dispatchInteraction(org.bukkit.entity.Player player, int id, dev.by1337.entityrender.api.EntityInteraction.Type type,
        org.bukkit.inventory.EquipmentSlot hand, org.bukkit.util.Vector hit, boolean secondary) {
        checkThread();
        if (closed) return;
        var entity = entities.get(id);
        if (entity != null) entity.interact(player, type, hand, hit, secondary);
    }

    private void bindInteractions(org.bukkit.entity.Player player) {
        if (interactions != null) interactions.bind(player, ((CraftPlayer) player).getHandle().connection.connection.channel);
    }

    void enqueue(BaseVirtualEntity entity) {
        if (!entity.queued && !entity.isClosed()) {
            entity.queued = true;
            pending.addLast(entity);
        }
    }

    void forget(BaseVirtualEntity entity) {
        var tracker = memberships.remove(entity.getId());
        if (tracker != null) tracker.detachClosedEntity(entity.getId());
        entities.remove(entity.getId());
        if (entity.queued) {
            pending.remove(entity);
            entity.queued = false;
        }
    }

    private int allocate(Location location) {
        checkOpen();
        BaseVirtualEntity.validateLocation(location);
        return ids.applyAsInt(location.getWorld());
    }

    dev.by1337.entityrender.core.metadata.MetadataLayout template(org.bukkit.entity.EntityType type, World world,
        net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout generated) {
        checkOpen();
        return templates.computeIfAbsent(type, ignored -> {
            var level = ((CraftWorld) world).getHandle();
            var prototype = nativeType.create(level, net.minecraft.world.entity.EntitySpawnReason.COMMAND);
            if (prototype == null) throw new IllegalArgumentException("Entity requires additional construction context: " + type);
            var tracker = new net.minecraft.server.level.ServerEntity(level, prototype, 1, false, new net.minecraft.server.level.ServerEntity.Synchronizer() {
                public void sendToTrackingPlayers(net.minecraft.network.protocol.Packet<? super net.minecraft.network.protocol.game.ClientGamePacketListener> p0) {
                }

                public void sendToTrackingPlayersAndSelf(net.minecraft.network.protocol.Packet<? super net.minecraft.network.protocol.game.ClientGamePacketListener> p0) {
                }

                public void sendToTrackingPlayersFiltered(net.minecraft.network.protocol.Packet<? super net.minecraft.network.protocol.game.ClientGamePacketListener> p0, java.util.function.Predicate<net.minecraft.server.level.ServerPlayer> p1) {
                }
            }, Set.of());
            var packet = prototype.getAddEntityPacket(tracker);
            int data = packet instanceof net.minecraft.network.protocol.game.ClientboundAddEntityPacket add ? add.getData()
                    : 0;
            spawnData.put(type, data);
            return generated.withSnapshot(prototype.getEntityData().packAll());
        });
    }

    @Override
    public VirtualEntity create(org.bukkit.entity.EntityType type, Location location) {
        checkOpen();
        Objects.requireNonNull(type);
        return switch (type) {
            case ARMOR_STAND -> createArmorStand(location);
            case TEXT_DISPLAY -> createTextDisplay(location);
            case ITEM_DISPLAY -> createItemDisplay(location);
            case BLOCK_DISPLAY -> createBlockDisplay(location);
            default -> {
                int id = allocate(location);
                // The client assigns the dragon body parts the eight IDs following its parent.
                if (type == org.bukkit.entity.EntityType.ENDER_DRAGON) {
                    for (int part = 0; part < 8; part++) allocate(location);
                }
                var nativeType = org.bukkit.craftbukkit.entity.CraftEntityType.bukkitToMinecraft(type);
                var entity = GeneratedEntityFactory.create(this, id, location, type, nativeType);
                entity.initializeSpawnData(spawnData.getOrDefault(type, 0));
                if (entities.putIfAbsent(id, entity) != null) throw new IllegalStateException("Duplicate entity id");
                yield entity;
            }
        };
    }

    @Override
    public VirtualArmorStand createArmorStand(Location location) {
        var entity = new ArmorStandEntity(this, allocate(location), location);
        if (entities.putIfAbsent(entity.getId(), entity) != null) throw new IllegalStateException("Duplicate entity id");
        return entity;
    }

    @Override
    public VirtualTextDisplay createTextDisplay(Location location) {
        var entity = new TextDisplayEntity(this, allocate(location), location);
        if (entities.putIfAbsent(entity.getId(), entity) != null) throw new IllegalStateException("Duplicate entity id");
        return entity;
    }

    @Override
    public VirtualItemDisplay createItemDisplay(Location location) {
        var entity = new ItemDisplayEntity(this, allocate(location), location);
        if (entities.putIfAbsent(entity.getId(), entity) != null) throw new IllegalStateException("Duplicate entity id");
        return entity;
    }

    @Override
    public VirtualBlockDisplay createBlockDisplay(Location location) {
        var entity = new BlockDisplayEntity(this, allocate(location), location);
        if (entities.putIfAbsent(entity.getId(), entity) != null) throw new IllegalStateException("Duplicate entity id");
        return entity;
    }

    @Override
    public void flush() {
        checkOpen();
        var indices = new HashMap<World, ViewerIndex>();
        for (var tracker : trackers) tracker.update(indices.computeIfAbsent(tracker.world(), world -> new ViewerIndex(world, players)));
        RuntimeException failure = null;
        int count = pending.size();
        for (int i = 0; i < count; i++) {
            BaseVirtualEntity entity = pending.removeFirst();
            entity.queued = false;
            if (entity.isClosed()) continue;
            try {
                entity.flushChanges();
            } catch (RuntimeException e) {
                enqueue(entity);
                if (failure == null) failure = e;
                else failure.addSuppressed(e);
            }
        }
        if (failure != null) throw failure;
    }

    @Override
    public int size() {
        checkThread();
        return entities.size();
    }

    @Override
    public boolean isClosed() {
        checkThread();
        return closed;
    }

    @Override
    public void close() {
        checkThread();
        if (closed) return;
        closed = true;
        if (ticker != null) ticker.cancel();
        if (interactions != null) interactions.close();
        HandlerList.unregisterAll(this);
        for (var entity : pending) entity.queued = false;
        pending.clear();
        RuntimeException failure = null;
        for (var tracker : List.copyOf(trackers)) try {
            tracker.close();
        } catch (RuntimeException e) {
            if (failure == null) failure = e;
            else failure.addSuppressed(e);
        }
        for (var entity : List.copyOf(entities.values())) {
            try {
                entity.close();
            } catch (RuntimeException e) {
                if (failure == null) failure = e;
                else failure.addSuppressed(e);
            }
        }
        entities.clear();
        pending.clear();
        templates.clear();
        spawnData.clear();
        spawnPackets.close();
        if (failure != null) throw failure;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (interactions != null) interactions.unbind(event.getPlayer().getUniqueId());
        forgetViewer(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        bindInteractions(event.getPlayer());
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        forgetViewer(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        forgetViewer(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onDisable(PluginDisableEvent event) {
        if (event.getPlugin() == plugin) close();
    }

    private void forgetViewer(UUID player) {
        checkThread();
        for (var entity : entities.values()) entity.forgetViewer(player);
        for (var tracker : trackers) tracker.forgetViewer(player);
    }
}
