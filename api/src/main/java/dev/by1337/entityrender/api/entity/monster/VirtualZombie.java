package dev.by1337.entityrender.api.entity.monster;

/**
 * Generated from net.minecraft.world.entity.monster.Zombie. Values use Bukkit types; packed flag fields remain
 * explicit.
 */
public interface VirtualZombie extends dev.by1337.entityrender.api.entity.monster.VirtualMonster {
    /** NMS DATA_BABY_ID; BOOLEAN. */
    boolean isBaby();

    void setBaby(boolean value);

    /** NMS DATA_DROWNED_CONVERSION_ID; BOOLEAN. */
    boolean isDrownedConversion();

    void setDrownedConversion(boolean value);

    /** NMS DATA_SPECIAL_TYPE_ID; INT. */
    int getSpecialType();

    void setSpecialType(int value);
}
