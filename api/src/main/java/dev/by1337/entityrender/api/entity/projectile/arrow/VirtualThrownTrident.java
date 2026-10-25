package dev.by1337.entityrender.api.entity.projectile.arrow;

/**
 * Generated from net.minecraft.world.entity.projectile.arrow.ThrownTrident. Values use Bukkit types; packed flag fields
 * remain explicit.
 */
public interface VirtualThrownTrident extends dev.by1337.entityrender.api.entity.projectile.arrow.VirtualAbstractArrow {
    /** NMS ID_FOIL; BOOLEAN. */
    boolean isFoil();

    void setFoil(boolean value);

    /** NMS ID_LOYALTY; BYTE. */
    byte getLoyalty();

    void setLoyalty(byte value);
}
