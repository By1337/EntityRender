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
    private final ToIntFunction<World> ids;
    private final Map<Integer, BaseVirtualEntity> entities = new HashMap<>();
    private final ArrayDeque<BaseVirtualEntity> pending = new ArrayDeque<>();
    private Plugin plugin;
    private BukkitTask ticker;
    private boolean closed;

    PaperEntityRenderer(PacketSink sink, ToIntFunction<World> ids) {
        this.sink = sink;
        this.ids = ids;
    }

    public static EntityRenderer create(Plugin owner, boolean automatic) {
        Objects.requireNonNull(owner);
        if (!Bukkit.isPrimaryThread()) throw new IllegalStateException("Create EntityRender on the server thread");
        if (!owner.isEnabled()) throw new IllegalStateException("Owner plugin is not enabled");
        if (!Bukkit.getMinecraftVersion().equals("1.21.4")) throw new IllegalStateException("This EntityRender build requires Paper 1.21.4; got " + Bukkit.getMinecraftVersion());
        var renderer = new PaperEntityRenderer((player, packet) -> ((CraftPlayer) player).getHandle().connection.connection.send(packet, null, false),
                world -> Bukkit.getUnsafe().nextEntityId());
        renderer.plugin = owner;
        try {
            Bukkit.getPluginManager().registerEvents(renderer, owner);
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

    void enqueue(BaseVirtualEntity entity) {
        if (!entity.queued && !entity.isClosed()) {
            entity.queued = true;
            pending.addLast(entity);
        }
    }

    void forget(BaseVirtualEntity entity) {
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
        HandlerList.unregisterAll(this);
        RuntimeException failure = null;
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
        if (failure != null) throw failure;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        forgetViewer(event.getPlayer().getUniqueId());
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
    }
}
