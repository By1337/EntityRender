package dev.by1337.entityrender.api.entity.monster;

/**
 * Generated from net.minecraft.world.entity.monster.Guardian. Values use Bukkit types; packed flag fields remain
 * explicit.
 */
public interface VirtualGuardian extends dev.by1337.entityrender.api.entity.monster.VirtualMonster {
    /** NMS DATA_ID_ATTACK_TARGET; INT. */
    int getAttackTarget();

    void setAttackTarget(int value);

    /** NMS DATA_ID_MOVING; BOOLEAN. */
    boolean isMoving();

    void setMoving(boolean value);
}
