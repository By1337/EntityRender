package dev.by1337.entityrender.api.entity.ambient;

/**
 * Generated from net.minecraft.world.entity.ambient.Bat. Values use Bukkit types; packed flag fields remain explicit.
 */
public interface VirtualBat extends dev.by1337.entityrender.api.entity.ambient.VirtualAmbientCreature {
    /** NMS DATA_ID_FLAGS; BYTE. */
    byte getBatFlags();

    void setBatFlags(byte value);

    boolean isHanging();

    void setHanging(boolean value);
}
