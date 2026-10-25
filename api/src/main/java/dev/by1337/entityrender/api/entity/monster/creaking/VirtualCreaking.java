package dev.by1337.entityrender.api.entity.monster.creaking;

/**
 * Generated from net.minecraft.world.entity.monster.creaking.Creaking. Values use Bukkit types; packed flag fields
 * remain explicit.
 */
public interface VirtualCreaking extends dev.by1337.entityrender.api.entity.monster.VirtualMonster {
    /** NMS CAN_MOVE; BOOLEAN. */
    boolean isCanMove();

    void setCanMove(boolean value);

    /** NMS HOME_POS; OPTIONAL_BLOCK_POS. */
    org.bukkit.util.BlockVector getHomePos();

    void setHomePos(org.bukkit.util.BlockVector value);

    /** NMS IS_ACTIVE; BOOLEAN. */
    boolean isActive();

    void setActive(boolean value);

    /** NMS IS_TEARING_DOWN; BOOLEAN. */
    boolean isTearingDown();

    void setTearingDown(boolean value);
}
