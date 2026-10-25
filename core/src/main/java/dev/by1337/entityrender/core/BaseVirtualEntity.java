package dev.by1337.entityrender.core;

import dev.by1337.entityrender.api.entity.VirtualEntity;
import dev.by1337.entityrender.core.metadata.*;
import io.papermc.paper.adventure.PaperAdventure;
import net.kyori.adventure.text.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.*;
import net.minecraft.world.entity.PositionPath;
import net.minecraft.world.phys.Vec3;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;
import java.util.*;
import dev.by1337.entityrender.core.metadata.GeneratedMetadata;

abstract class BaseVirtualEntity implements VirtualEntity {
    protected final PaperEntityRenderer owner;
    protected final MetadataState metadata;
    private final int id;
    private final UUID uuid = UUID.randomUUID();
    private final World world;
    private final EntityType type;
    private final net.minecraft.world.entity.EntityType<?> nmsType;
    private int spawnData;
    private boolean respawnDirty;
    private boolean headDirty;
    private final boolean living;
    private final Map<UUID, Player> viewers = new HashMap<>();
    private double x, y, z;
    private float yaw, pitch;
    private boolean onGround, positionDirty, closed;
    boolean queued;
    private EquipmentState equipment;
    private java.util.function.Consumer<dev.by1337.entityrender.api.EntityInteraction> interactionHandler;

    BaseVirtualEntity(PaperEntityRenderer owner, int id, Location location, EntityType type,
        net.minecraft.world.entity.EntityType<?> nmsType, MetadataLayout layout) {
        validateLocation(location);
        this.owner = owner;
        this.id = id;
        this.type = type;
        living = type.getEntityClass() != null && LivingEntity.class.isAssignableFrom(type.getEntityClass());
        this.nmsType = nmsType;
        world = location.getWorld();
        x = location.getX();
        y = location.getY();
        z = location.getZ();
        yaw = location.getYaw();
        pitch = Math.clamp(location.getPitch(), -90f, 90f);
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
        if (closed) throw new IllegalStateException("Entity is closed");
    }

    protected final net.minecraft.core.RegistryAccess registries() {
        return ((org.bukkit.craftbukkit.CraftWorld) getWorld()).getHandle().registryAccess();
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
        respawnDirty = true;
        owner.enqueue(this);
    }

    protected <T> T get(MetadataKey<T> key) {
        checkOpen();
        return metadata.get(key);
    }

    @Override
    public final void setInteractionHandler(java.util.function.Consumer<dev.by1337.entityrender.api.EntityInteraction> handler) {
        checkOpen();
        interactionHandler = handler;
    }

    final void interact(Player player, dev.by1337.entityrender.api.EntityInteraction.Type type, org.bukkit.inventory.EquipmentSlot hand, Vector hit, boolean secondary) {
        if (closed || !viewers.containsKey(player.getUniqueId()) || !world.equals(player.getWorld()) || interactionHandler == null) return;
        interactionHandler.accept(new dev.by1337.entityrender.api.EntityInteraction(this, player, type, hand, hit, secondary));
    }

    protected <T> void set(MetadataKey<T> key, T value) {
        checkOpen();
        if (metadata.set(key, value)) owner.enqueue(this);
    }

    protected boolean flag(MetadataKey<Byte> key, int mask) {
        return (get(key) & mask) != 0;
    }

    protected void flag(MetadataKey<Byte> key, int mask, boolean enabled) {
        byte current = get(key);
        set(key, (byte) (enabled ? current | mask : current & ~mask));
    }

    @Override
    public int getId() {
        owner.checkThread();
        return id;
    }

    @Override
    public UUID getUniqueId() {
        owner.checkThread();
        return uuid;
    }

    @Override
    public EntityType getType() {
        owner.checkThread();
        return type;
    }

    @Override
    public World getWorld() {
        owner.checkThread();
        return world;
    }

    @Override
    public Location getLocation() {
        checkOpen();
        return new Location(world, x, y, z, yaw, pitch);
    }

    @Override
    public Vector getPosition() {
        checkOpen();
        return new Vector(x, y, z);
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
        if (this.x == x && this.y == y && this.z == z) return;
        this.x = x;
        this.y = y;
        this.z = z;
        positionChanged();
    }

    @Override
    public void teleport(Location location) {
        checkOpen();
        validateLocation(location);
        if (!world.equals(location.getWorld())) throw new IllegalArgumentException("Virtual entity teleports must stay in the same world");
        setPosition(location.getX(), location.getY(), location.getZ());
        setRotation(location.getYaw(), location.getPitch());
    }

    @Override
    public void setRotation(float yaw, float pitch) {
        checkOpen();
        finite(yaw);
        finite(pitch);
        pitch = Math.clamp(pitch, -90f, 90f);
        if (this.yaw == yaw && this.pitch == pitch) return;
        if (this.yaw != yaw && living) headDirty = true;
        this.yaw = yaw;
        this.pitch = pitch;
        positionChanged();
    }

    private void positionChanged() {
        positionDirty = true;
        owner.enqueue(this);
    }

    @Override
    public boolean isOnGround() {
        checkOpen();
        return onGround;
    }

    @Override
    public void setOnGround(boolean value) {
        checkOpen();
        if (onGround != value) {
            onGround = value;
            positionChanged();
        }
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
        if (!player.isOnline()) throw new IllegalArgumentException("Viewer is offline");
        if (!world.equals(player.getWorld())) throw new IllegalArgumentException("Viewer must be in the entity's world");
        if (viewers.containsKey(player.getUniqueId())) return;
        try {
            owner.send(player, owner.spawnPackets.create(type, nmsType, id, uuid, x, y, z, pitch, yaw, spawnData));
            owner.send(player, new ClientboundSetEntityDataPacket(id, metadata.fullSnapshot()));
            sendAdditionalSnapshot(player);
            viewers.put(player.getUniqueId(), player);
        } catch (RuntimeException failure) {
            try {
                owner.send(player, new ClientboundRemoveEntitiesPacket(id));
            } catch (RuntimeException cleanup) {
                failure.addSuppressed(cleanup);
            }
            throw failure;
        }
    }

    @Override
    public void hide(Player player) {
        checkOpen();
        Objects.requireNonNull(player);
        if (viewers.containsKey(player.getUniqueId())) {
            owner.send(player, new ClientboundRemoveEntitiesPacket(id));
            viewers.remove(player.getUniqueId());
        }
    }

    void forgetViewer(UUID player) {
        viewers.remove(player);
    }

    final boolean hasViewer(UUID player) {
        return viewers.containsKey(player);
    }

    @Override
    public Set<UUID> getViewers() {
        owner.checkThread();
        return Set.copyOf(viewers.keySet());
    }

    @Override
    public boolean isClosed() {
        owner.checkThread();
        return closed;
    }

    void flushChanges() {
        checkOpen();
        if (!viewers.isEmpty()) {
            if (respawnDirty) {
                broadcast(new ClientboundRemoveEntitiesPacket(id));
                var spawn = owner.spawnPackets.create(type, nmsType, id, uuid, x, y, z, pitch, yaw, spawnData);
                var full = new ClientboundSetEntityDataPacket(id, metadata.fullSnapshot());
                for (var player : viewers.values()) {
                    owner.send(player, spawn);
                    owner.send(player, full);
                    sendAdditionalSnapshot(player);
                }
                metadata.acknowledge();
                headDirty = false;
                positionDirty = false;
                respawnDirty = false;
                acknowledgeAdditional();
                return;
            }
            if (positionDirty) broadcast(new ClientboundEntityPositionSyncPacket(id, PositionPath.of(new Vec3(x, y, z)), yaw, pitch, onGround));
            if (headDirty) broadcast(owner.spawnPackets.head(id, yaw));
            if (metadata.isDirty()) broadcast(new ClientboundSetEntityDataPacket(id, metadata.dirtySnapshot()));
            flushAdditional();
        }
        metadata.acknowledge();
        headDirty = false;
        positionDirty = false;
        respawnDirty = false;
        acknowledgeAdditional();
    }

    protected void sendAdditionalSnapshot(Player player) {
        if (equipment != null) {
            var packet = equipment.packet(id, true);
            if (packet != null) owner.send(player, packet);
        }
    }

    protected void flushAdditional() {
        if (equipment != null) {
            var packet = equipment.packet(id, false);
            if (packet != null) broadcast(packet);
        }
    }

    protected void acknowledgeAdditional() {
        if (equipment != null) equipment.acknowledge();
    }

    public org.bukkit.inventory.ItemStack getEquipment(org.bukkit.inventory.EquipmentSlot slot) {
        checkOpen();
        Objects.requireNonNull(slot);
        return equipment == null ? null : equipment.get(slot);
    }

    public void setEquipment(org.bukkit.inventory.EquipmentSlot slot, org.bukkit.inventory.ItemStack item) {
        checkOpen();
        if (equipment == null) equipment = new EquipmentState();
        if (equipment.set(slot, item)) owner.enqueue(this);
    }

    protected final void broadcast(Packet<?> packet) {
        RuntimeException failure = null;
        for (Player player : viewers.values()) {
            try {
                owner.send(player, packet);
            } catch (RuntimeException e) {
                if (failure == null) failure = e;
                else failure.addSuppressed(e);
            }
        }
        if (failure != null) throw failure;
    }

    @Override
    public void close() {
        owner.checkThread();
        if (closed) return;
        try {
            if (!viewers.isEmpty()) broadcast(new ClientboundRemoveEntitiesPacket(id));
        } finally {
            closed = true;
            interactionHandler = null;
            viewers.clear();
            owner.forget(this);
        }
    }
}
