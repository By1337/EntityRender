package dev.by1337.entityrender.api.entity.projectile;

/**
 * Generated from net.minecraft.world.entity.projectile.ThrownTrident. Values use Bukkit types; packed flag fields
 * remain explicit.
 */
public interface VirtualThrownTrident extends dev.by1337.entityrender.api.entity.projectile.VirtualAbstractArrow {
    /** NMS ID_FOIL; BOOLEAN. */
    boolean isFoil();

    void setFoil(boolean value);

    /** NMS ID_LOYALTY; BYTE. */
    byte getLoyalty();

    void setLoyalty(byte value);
}
