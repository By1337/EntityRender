package dev.by1337.entityrender.api.entity.animal;

/**
 * Generated from net.minecraft.world.entity.animal.Bee. Values use Bukkit types; packed flag fields remain explicit.
 */
public interface VirtualBee extends dev.by1337.entityrender.api.entity.animal.VirtualAnimal {
    /** NMS DATA_FLAGS_ID; BYTE. */
    byte getBeeFlags();

    void setBeeFlags(byte value);

    /** NMS DATA_REMAINING_ANGER_TIME; INT. */
    int getRemainingAngerTime();

    void setRemainingAngerTime(int value);
}
