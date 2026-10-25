package dev.by1337.entityrender.api.entity.animal;

/**
 * Generated from net.minecraft.world.entity.animal.Wolf. Values use Bukkit types; packed flag fields remain explicit.
 */
public interface VirtualWolf extends dev.by1337.entityrender.api.entity.VirtualTamableAnimal {
    /** NMS DATA_COLLAR_COLOR; INT. */
    org.bukkit.DyeColor getCollarColor();

    void setCollarColor(org.bukkit.DyeColor value);

    /** NMS DATA_INTERESTED_ID; BOOLEAN. */
    boolean isInterested();

    void setInterested(boolean value);

    /** NMS DATA_REMAINING_ANGER_TIME; INT. */
    int getRemainingAngerTime();

    void setRemainingAngerTime(int value);

    /** NMS DATA_VARIANT_ID; WOLF_VARIANT. */
    org.bukkit.entity.Wolf.Variant getVariant();

    void setVariant(org.bukkit.entity.Wolf.Variant value);
}
