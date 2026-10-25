package dev.by1337.entityrender.api.entity.monster;

/**
 * Generated from net.minecraft.world.entity.monster.Creeper. Values use Bukkit types; packed flag fields remain
 * explicit.
 */
public interface VirtualCreeper extends dev.by1337.entityrender.api.entity.monster.VirtualMonster {
    /** NMS DATA_IS_IGNITED; BOOLEAN. */
    boolean isIgnited();

    void setIgnited(boolean value);

    /** NMS DATA_IS_POWERED; BOOLEAN. */
    boolean isPowered();

    void setPowered(boolean value);

    /** NMS DATA_SWELL_DIR; INT. */
    int getSwellDir();

    void setSwellDir(int value);
}
