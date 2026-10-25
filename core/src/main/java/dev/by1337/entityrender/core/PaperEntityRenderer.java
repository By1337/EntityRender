package dev.by1337.entityrender.core;

import dev.by1337.entityrender.api.EntityRenderer;
import dev.by1337.entityrender.api.entity.*;
import dev.by1337.entityrender.core.metadata.MetadataLayout;
import org.bukkit.*;
import org.bukkit.craftbukkit.entity.CraftEntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.*;

/** Concurrent editing/publication boundary; packets and actual viewers belong to RenderState. */
public final class PaperEntityRenderer implements EntityRenderer {
    private final PacketSink sink;
    final SpawnPackets spawnPackets = new SpawnPackets();
    final RenderState transport = new RenderState(this);
    final boolean automatic;
    private final RenderService service;
    private final ToIntFunction<World> testIds;
    private final Function<World, List<Player>> testPlayers;
    private final PlayerDirectory testDirectory = new PlayerDirectory();
    private final Map<UUID, Player> testKnownViewers = new HashMap<>();
    private final Map<World, WorldResources> testWorlds = new ConcurrentHashMap<>();
    private final Map<Integer, BaseVirtualEntity> entities = new ConcurrentHashMap<>();
    private final Queue<BaseVirtualEntity> pending = new ConcurrentLinkedQueue<>();
    private final Set<SceneTracker> trackers = ConcurrentHashMap.newKeySet();
    private final Map<Integer, SceneTracker> memberships = new ConcurrentHashMap<>();
    private final ReentrantLock publication = new ReentrantLock();
    private final Object lifecycle = new Object();
    private volatile boolean closed;
    private volatile CompletableFuture<Void> completion = CompletableFuture.completedFuture(null);

    PaperEntityRenderer(PacketSink sink, ToIntFunction<World> ids) {
        this(sink, ids, ignored -> List.of());
    }

    PaperEntityRenderer(PacketSink sink, ToIntFunction<World> ids, Function<World, List<Player>> players) {
        this.sink = sink;
        testIds = ids;
        testPlayers = players;
        service = null;
        automatic = false;
    }

    private PaperEntityRenderer(RenderService service, boolean automatic) {
        this.service = service;
        this.automatic = automatic;
        testIds = null;
        testPlayers = null;
        sink = service::send;
        service.register(this);
    }

    public static EntityRenderer create(Plugin owner, boolean automatic) {
        Objects.requireNonNull(owner);
        if (!Bukkit.isPrimaryThread()) throw new IllegalStateException("Initialize EntityRender on the main thread; entity operations may run asynchronously");
        if (!owner.isEnabled()) throw new IllegalStateException("Owner plugin is not enabled");
        if (!Bukkit.getMinecraftVersion().equals(TargetVersion.MINECRAFT_VERSION)) throw new IllegalStateException("This EntityRender build requires Paper " + TargetVersion.MINECRAFT_VERSION);
        return new PaperEntityRenderer(RenderService.acquire(owner), automatic);
    }

    void checkOpen() {
        if (closed) throw new IllegalStateException("Renderer is closed");
    }

    void send(Player player, net.minecraft.network.protocol.Packet<?> packet) {
        sink.send(player, packet);
    }

    boolean containsEntity(int id) {
        var entity = entities.get(id);
        return !closed && entity != null && !entity.isClosed();
    }

    void dispatchInteraction(Player player, int id, dev.by1337.entityrender.api.EntityInteraction.Type type, org.bukkit.inventory.EquipmentSlot hand, org.bukkit.util.Vector hit, boolean secondary) {
        var entity = entities.get(id);
        if (!closed && entity != null) entity.interact(player, type, hand, hit, secondary);
    }

    private WorldResources resources(World world) {
        return service == null ? testWorlds.computeIfAbsent(world, key -> new WorldResources(null, key)) : service.world(world);
    }

    net.minecraft.core.RegistryAccess registries(World world) {
        if (service == null && !(world instanceof org.bukkit.craftbukkit.CraftWorld)) return net.minecraft.core.RegistryAccess.fromRegistryOfRegistries(net.minecraft.core.registries.BuiltInRegistries.REGISTRY);
        return resources(world).registries;
    }

    MetadataLayout template(org.bukkit.entity.EntityType type, World world, net.minecraft.world.entity.EntityType<?> nativeType, MetadataLayout generated) {
        checkOpen();
        return resources(world).template(type, generated).layout();
    }

    void enqueue(BaseVirtualEntity entity) {
        if (entity.queued.compareAndSet(false, true)) pending.add(entity);
    }

    void entityClosed(BaseVirtualEntity entity) {
        entities.remove(entity.getId(), entity);
        var tracker = memberships.remove(entity.getId());
        if (tracker != null) tracker.detachClosedEntity(entity.getId());
        if (service != null) service.untarget(entity);
    }

    private <T extends BaseVirtualEntity> T register(T entity) {
        synchronized (lifecycle) {
            checkOpen();
            if (service != null) service.world(entity.getWorld());
            if (entities.putIfAbsent(entity.getId(), entity) != null) throw new IllegalStateException("Duplicate entity ID");
            if (service != null) service.target(entity);
            enqueue(entity);
            return entity;
        }
    }

    private int allocate(Location location, int count) {
        checkOpen();
        BaseVirtualEntity.validateLocation(location);
        if (service != null || location.getWorld() instanceof org.bukkit.craftbukkit.CraftWorld) resources(location.getWorld());
        if (service != null) return service.ids.take(count);
        int first = testIds.applyAsInt(location.getWorld());
        for (int i = 1; i < count; i++) if (testIds.applyAsInt(location.getWorld()) != first + i) throw new IllegalStateException("Multipart entity IDs must be contiguous");
        return first;
    }

    @Override
    public VirtualEntity create(org.bukkit.entity.EntityType type, Location location) {
        Objects.requireNonNull(type);
        return switch (type) {
            case ARMOR_STAND -> createArmorStand(location);
            case TEXT_DISPLAY -> createTextDisplay(location);
            case ITEM_DISPLAY -> createItemDisplay(location);
            case BLOCK_DISPLAY -> createBlockDisplay(location);
            default -> {
                int id = allocate(location, type == org.bukkit.entity.EntityType.ENDER_DRAGON ? 9 : 1);
                var nativeType = CraftEntityType.bukkitToMinecraft(type);
                var entity = GeneratedEntityFactory.create(this, id, location, type, nativeType);
                entity.initializeSpawnData(resources(location.getWorld()).spawnData(type));
                yield register(entity);
            }
        };
    }

    @Override
    public VirtualArmorStand createArmorStand(Location location) {
        return register(new ArmorStandEntity(this, allocate(location, 1), location));
    }

    @Override
    public VirtualTextDisplay createTextDisplay(Location location) {
        return register(new TextDisplayEntity(this, allocate(location, 1), location));
    }

    @Override
    public VirtualItemDisplay createItemDisplay(Location location) {
        return register(new ItemDisplayEntity(this, allocate(location, 1), location));
    }

    @Override
    public VirtualBlockDisplay createBlockDisplay(Location location) {
        return register(new BlockDisplayEntity(this, allocate(location, 1), location));
    }

    @Override
    public dev.by1337.entityrender.api.ViewerTracker createTracker(Location center, double range) {
        synchronized (lifecycle) {
            checkOpen();
            resources(center.getWorld());
            var tracker = new SceneTracker(this, center, range);
            trackers.add(tracker);
            return tracker;
        }
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

    @Override
    public void flush() {
        publication.lock();
        try {
            checkOpen();
            var frames = new ArrayList<PublishedEntity>();
            int count = pending.size();
            for (int i = 0; i < count; i++) {
                var entity = pending.poll();
                if (entity == null) break;
                entity.queued.set(false);
                frames.add(entity.publish());
            }
            var scenes = trackers.stream().map(SceneTracker::snapshot).toList();
            if (service != null) completion = service.publish(this, List.copyOf(frames), scenes);
            else {
                // Deterministic injection seam for native packet tests; production never samples here.
                for (var entity : entities.values()) testKnownViewers.putAll(entity.requestedViewers());
                var viewers = new HashMap<UUID, Player>(testKnownViewers);
                for (var world : testWorlds.keySet()) for (var player : testPlayers.apply(world)) viewers.put(player.getUniqueId(), player);
                var samples = new ArrayList<PlayerDirectory.Sample>();
                for (var player : viewers.values()) {
                    World world = player.getWorld();
                    double x = 0, y = 0, z = 0;
                    if (!trackers.isEmpty()) {
                        var p = player.getLocation();
                        x = p.getX();
                        y = p.getY();
                        z = p.getZ();
                    }
                    samples.add(new PlayerDirectory.Sample(player, player.getUniqueId(), world, x, y, z, 0, null));
                }
                // Keep manually requested viewers in idle flushes as well.
                for (var entity : entities.values()) for (var id : entity.getViewers()) if (!viewers.containsKey(id)) {
                    var previous = testDirectory.get(id);
                    if (previous != null) samples.add(previous.sample);
                }
                testDirectory.replace(samples);
                transport.accept(frames, scenes);
                transport.render(testDirectory);
            }
        } finally {
            publication.unlock();
        }
    }

    /** Test/diagnostic fence; public flush itself is nonblocking with respect to packet delivery. */
    CompletableFuture<Void> completion() {
        return completion;
    }

    RenderService service() {
        return service;
    }

    void closeWorld(World world) {
        synchronized (lifecycle) {
            if (closed) return;
            for (var tracker : List.copyOf(trackers)) if (tracker.snapshot().settings().center().getWorld() == world) tracker.close();
            for (var entity : entities.values()) if (entity.getWorld() == world) entity.close();
        }
        try {
            flush();
        } catch (IllegalStateException error) {
            if (!closed) throw error;
        }
    }

    @Override
    public int size() {
        return entities.size();
    }

    @Override
    public boolean isClosed() {
        return closed;
    }

    @Override
    public void close() {
        publication.lock();
        try {
            synchronized (lifecycle) {
                if (closed) return;
                closed = true;
            }
            for (var tracker : List.copyOf(trackers)) tracker.close();
            for (var entity : List.copyOf(entities.values())) entity.close();
            entities.clear();
            pending.clear();
            memberships.clear();
            trackers.clear();
            if (service != null) service.release(this);
            else transport.close(testDirectory);
        } finally {
            publication.unlock();
        }
    }
}
