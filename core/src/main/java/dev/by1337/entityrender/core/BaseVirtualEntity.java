package dev.by1337.entityrender.core;

import dev.by1337.entityrender.api.entity.VirtualEntity;
import dev.by1337.entityrender.core.metadata.*;
import io.papermc.paper.adventure.PaperAdventure;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;
import java.util.*;
import dev.by1337.entityrender.core.metadata.GeneratedMetadata;

public abstract class BaseVirtualEntity implements VirtualEntity {
    protected final PaperEntityRenderer owner;
    protected final MetadataState metadata;
    private final int id;
    private final UUID uuid = UUID.randomUUID();
    private final World world;
    private final EntityType type;
    private final net.minecraft.world.entity.EntityType<?> nmsType;
    private volatile int spawnData;
    private final java.util.concurrent.atomic.AtomicLong respawn = new java.util.concurrent.atomic.AtomicLong();
    private final java.util.concurrent.atomic.AtomicReference<PublishedEntity.Position> position;
    private final java.util.concurrent.atomic.AtomicBoolean closed = new java.util.concurrent.atomic.AtomicBoolean();
    final java.util.concurrent.atomic.AtomicBoolean queued = new java.util.concurrent.atomic.AtomicBoolean();
    private final Map<UUID, Player> requested = new java.util.concurrent.ConcurrentHashMap<>();
    private volatile Set<UUID> actualViewers = Set.of();
    private final boolean living;
    private volatile EquipmentState equipment;
    private volatile java.util.function.Consumer<dev.by1337.entityrender.api.EntityInteraction> interactionHandler;
    private final net.minecraft.core.RegistryAccess registryAccess;
    // Only the renderer's serialized publication boundary accesses these fields.
    private List<net.minecraft.network.syncher.SynchedEntityData.DataValue<?>> publishedMetadata;
    private List<com.mojang.datafixers.util.Pair<net.minecraft.world.entity.EquipmentSlot, net.minecraft.world.item.ItemStack>> publishedEquipment = List.of();
    private PublishedEntity.Position publishedPosition;

    protected BaseVirtualEntity(PaperEntityRenderer owner, int id, Location location, EntityType type,
        net.minecraft.world.entity.EntityType<?> nmsType, MetadataLayout layout) {
        validateLocation(location);
        this.owner = owner;
        this.id = id;
        this.type = type;
        living = type.getEntityClass() != null && LivingEntity.class.isAssignableFrom(type.getEntityClass());
        this.nmsType = nmsType;
        world = location.getWorld();
        position = new java.util.concurrent.atomic.AtomicReference<>(new PublishedEntity.Position(location.getX(), location.getY(), location.getZ(), location.getYaw(), Math.clamp(location.getPitch(), -90f, 90f), false));
        registryAccess = owner.registries(world);
        metadata = new MetadataState(layout);
    }

    static void validateLocation(Location location) {
        Objects.requireNonNull(location);
        Objects.requireNonNull(location.getWorld(), "Location needs a world");
        finite(location.getX());
        finite(location.getY());
        finite(location.getZ());
        finite(location.getYaw());
        finite(location.getPitch());
    }

    protected static void finite(double value) {
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Coordinates and rotations must be finite");
    }

    protected void checkOpen() {
        owner.checkOpen();
        if (closed.get()) throw new IllegalStateException("Entity is closed");
    }

    protected final net.minecraft.core.RegistryAccess registries() {
        return registryAccess;
    }

    final void initializeSpawnData(int data) {
        spawnData = data;
    }

    protected final int getSpawnData() {
        checkOpen();
        return spawnData;
    }

    protected final void setSpawnData(int data) {
        checkOpen();
        if (spawnData != data) {
            spawnData = data;
            respawn();
        }
    }

    @Override
    public final void respawn() {
        checkOpen();
        respawn.incrementAndGet();
        owner.enqueue(this);
    }

    protected <T> T get(MetadataKey<T> key) {
        checkOpen();
        return metadata.getOwned(key);
    }

    @Override
    public final void setInteractionHandler(java.util.function.Consumer<dev.by1337.entityrender.api.EntityInteraction> handler) {
        checkOpen();
        interactionHandler = handler;
    }

    final void interact(Player player, dev.by1337.entityrender.api.EntityInteraction.Type type, org.bukkit.inventory.EquipmentSlot hand, Vector hit, boolean secondary) {
        var handler = interactionHandler;
        if (closed.get() || !actualViewers.contains(player.getUniqueId()) || !world.equals(player.getWorld()) || handler == null) return;
        handler.accept(new dev.by1337.entityrender.api.EntityInteraction(this, player, type, hand, hit, secondary));
    }

    protected <T> void set(MetadataKey<T> key, T value) {
        checkOpen();
        if (metadata.setOwned(key, value, false)) owner.enqueue(this);
    }

    protected boolean flag(MetadataKey<Byte> key, int mask) {
        return (get(key) & mask) != 0;
    }

    protected void flag(MetadataKey<Byte> key, int mask, boolean enabled) {
        update(key, current -> (byte) (enabled ? current | mask : current & ~mask));
    }

    protected <T> void update(MetadataKey<T> key, java.util.function.UnaryOperator<T> operation) {
        checkOpen();
        if (metadata.update(key, operation)) owner.enqueue(this);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public UUID getUniqueId() {
        return uuid;
    }

    @Override
    public EntityType getType() {
        return type;
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public Location getLocation() {
        checkOpen();
        var p = position.get();
        return new Location(world, p.x(), p.y(), p.z(), p.yaw(), p.pitch());
    }

    @Override
    public Vector getPosition() {
        checkOpen();
        var p = position.get();
        return new Vector(p.x(), p.y(), p.z());
    }

    @Override
    public void setPosition(Vector value) {
        Objects.requireNonNull(value);
        setPosition(value.getX(), value.getY(), value.getZ());
    }

    @Override
    public void setPosition(double x, double y, double z) {
        checkOpen();
        finite(x);
        finite(y);
        finite(z);
        position.updateAndGet(p -> new PublishedEntity.Position(x, y, z, p.yaw(), p.pitch(), p.onGround()));
        owner.enqueue(this);
    }

    @Override
    public void teleport(Location location) {
        checkOpen();
        validateLocation(location);
        if (!world.equals(location.getWorld())) throw new IllegalArgumentException("Virtual entity teleports must stay in the same world");
        position.updateAndGet(p -> new PublishedEntity.Position(location.getX(), location.getY(), location.getZ(), location.getYaw(), Math.clamp(location.getPitch(), -90f, 90f), p.onGround()));
        owner.enqueue(this);
    }

    @Override
    public void setRotation(float yaw, float pitch) {
        checkOpen();
        finite(yaw);
        finite(pitch);
        float limited = Math.clamp(pitch, -90f, 90f);
        position.updateAndGet(p -> new PublishedEntity.Position(p.x(), p.y(), p.z(), yaw, limited, p.onGround()));
        owner.enqueue(this);
    }

    @Override
    public boolean isOnGround() {
        checkOpen();
        return position.get().onGround();
    }

    @Override
    public void setOnGround(boolean value) {
        checkOpen();
        position.updateAndGet(p -> new PublishedEntity.Position(p.x(), p.y(), p.z(), p.yaw(), p.pitch(), value));
        owner.enqueue(this);
    }

    @Override
    public Component getCustomName() {
        return get(GeneratedMetadata.Entity.DATA_CUSTOM_NAME).map(PaperAdventure::asAdventure).orElse(null);
    }

    @Override
    public void setCustomName(Component name) {
        set(GeneratedMetadata.Entity.DATA_CUSTOM_NAME, Optional.ofNullable(name).map(PaperAdventure::asVanilla));
    }

    @Override
    public boolean isCustomNameVisible() {
        return get(GeneratedMetadata.Entity.DATA_CUSTOM_NAME_VISIBLE);
    }

    @Override
    public void setCustomNameVisible(boolean visible) {
        set(GeneratedMetadata.Entity.DATA_CUSTOM_NAME_VISIBLE, visible);
    }

    @Override
    public boolean isInvisible() {
        return flag(GeneratedMetadata.Entity.DATA_SHARED_FLAGS_ID, 0x20);
    }

    @Override
    public void setInvisible(boolean invisible) {
        flag(GeneratedMetadata.Entity.DATA_SHARED_FLAGS_ID, 0x20, invisible);
    }

    @Override
    public boolean isGlowing() {
        return flag(GeneratedMetadata.Entity.DATA_SHARED_FLAGS_ID, 0x40);
    }

    @Override
    public void setGlowing(boolean glowing) {
        flag(GeneratedMetadata.Entity.DATA_SHARED_FLAGS_ID, 0x40, glowing);
    }

    @Override
    public boolean isSilent() {
        return get(GeneratedMetadata.Entity.DATA_SILENT);
    }

    @Override
    public void setSilent(boolean silent) {
        set(GeneratedMetadata.Entity.DATA_SILENT, silent);
    }

    @Override
    public boolean hasGravity() {
        return !get(GeneratedMetadata.Entity.DATA_NO_GRAVITY);
    }

    @Override
    public void setGravity(boolean gravity) {
        set(GeneratedMetadata.Entity.DATA_NO_GRAVITY, !gravity);
    }

    @Override
    public Pose getPose() {
        var pose = get(GeneratedMetadata.Entity.DATA_POSE);
        return pose == net.minecraft.world.entity.Pose.CROUCHING ? Pose.SNEAKING : Pose.valueOf(pose.name());
    }

    @Override
    public void setPose(Pose pose) {
        Objects.requireNonNull(pose);
        set(GeneratedMetadata.Entity.DATA_POSE, pose == Pose.SNEAKING ? net.minecraft.world.entity.Pose.CROUCHING : net.minecraft.world.entity.Pose.valueOf(pose.name()));
    }

    @Override
    public void show(Player player) {
        checkOpen();
        Objects.requireNonNull(player);
        requested.put(player.getUniqueId(), player);
        owner.enqueue(this);
    }

    @Override
    public void hide(Player player) {
        checkOpen();
        Objects.requireNonNull(player);
        requested.remove(player.getUniqueId());
        owner.enqueue(this);
    }

    @Override
    public Set<UUID> getViewers() {
        return actualViewers;
    }

    final Map<UUID, Player> requestedViewers() {
        return Map.copyOf(requested);
    }

    final void clearRequested() {
        requested.clear();
        owner.enqueue(this);
    }

    final boolean hasViewer(UUID id) {
        return actualViewers.contains(id);
    }

    final void viewers(Set<UUID> value) {
        if (!actualViewers.equals(value)) actualViewers = Set.copyOf(value);
    }

    @Override
    public boolean isClosed() {
        return closed.get();
    }

    PublishedEntity publish() {
        var changed = metadata.drainOwned();
        if (publishedMetadata == null) publishedMetadata = metadata.fullOwnedSnapshot();
        else if (!changed.isEmpty()) {
            var next = new ArrayList<>(publishedMetadata);
            for (var value : changed) next.set(value.id(), value);
            publishedMetadata = List.copyOf(next);
        }
        var state = equipment;
        boolean equipmentChanged = state != null && state.drain();
        if (equipmentChanged) publishedEquipment = state.snapshot();
        var p = position.get();
        boolean headChanged = living && publishedPosition != null && p.yaw() != publishedPosition.yaw();
        publishedPosition = p;
        return new PublishedEntity(this, id, uuid, world, type, nmsType, p, spawnData, respawn.get(), headChanged,
                publishedMetadata, changed, publishedEquipment, equipmentChanged, Map.copyOf(requested), closed.get());
    }

    public org.bukkit.inventory.ItemStack getEquipment(org.bukkit.inventory.EquipmentSlot slot) {
        checkOpen();
        Objects.requireNonNull(slot);
        var state = equipment;
        return state == null ? null : state.get(slot);
    }

    public void setEquipment(org.bukkit.inventory.EquipmentSlot slot, org.bukkit.inventory.ItemStack item) {
        checkOpen();
        var state = equipment;
        if (state == null) synchronized (this) {
            if (equipment == null) equipment = new EquipmentState();
            state = equipment;
        }
        if (state.set(slot, item)) owner.enqueue(this);
    }

    @Override
    public void close() {
        if (closed.compareAndSet(false, true)) {
            interactionHandler = null;
            requested.clear();
            owner.entityClosed(this);
            owner.enqueue(this);
        }
    }
}
