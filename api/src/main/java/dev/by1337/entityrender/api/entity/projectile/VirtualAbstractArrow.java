package dev.by1337.entityrender.api.entity.projectile;

/**
 * Generated from net.minecraft.world.entity.projectile.AbstractArrow. Values use Bukkit types; packed flag fields
 * remain explicit.
 */
public interface VirtualAbstractArrow extends dev.by1337.entityrender.api.entity.projectile.VirtualProjectile {
    /** NMS ID_FLAGS; BYTE. */
    byte getAbstractArrowFlags();

    void setAbstractArrowFlags(byte value);

    /** NMS IN_GROUND; BOOLEAN. */
    boolean isInGround();

    void setInGround(boolean value);

    /** NMS PIERCE_LEVEL; BYTE. */
    byte getPierceLevel();

    void setPierceLevel(byte value);
}
