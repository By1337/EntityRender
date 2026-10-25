package dev.by1337.entityrender.api.entity;

/**
 * Generated from net.minecraft.world.entity.TamableAnimal. Values use Bukkit types; packed flag fields remain explicit.
 */
public interface VirtualTamableAnimal extends dev.by1337.entityrender.api.entity.animal.VirtualAnimal {
    /** NMS DATA_FLAGS_ID; BYTE. */
    byte getTamableAnimalFlags();

    void setTamableAnimalFlags(byte value);

    /** NMS DATA_OWNERUUID_ID; OPTIONAL_UUID. */
    java.util.UUID getOwneruuid();

    void setOwneruuid(java.util.UUID value);

    boolean isSitting();

    void setSitting(boolean value);

    boolean isTamed();

    void setTamed(boolean value);
}
