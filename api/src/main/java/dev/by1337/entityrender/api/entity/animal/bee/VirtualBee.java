package dev.by1337.entityrender.api.entity.animal.bee;

/**
 * Generated from net.minecraft.world.entity.animal.bee.Bee. Values use Bukkit types; packed flag fields remain
 * explicit.
 */
public interface VirtualBee extends dev.by1337.entityrender.api.entity.animal.VirtualAnimal {
    /** NMS DATA_ANGER_END_TIME; LONG. */
    long getAngerEndTime();

    void setAngerEndTime(long value);

    /** NMS DATA_FLAGS_ID; BYTE. */
    byte getBeeFlags();

    void setBeeFlags(byte value);
}
