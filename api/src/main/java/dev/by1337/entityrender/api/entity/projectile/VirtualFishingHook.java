package dev.by1337.entityrender.api.entity.projectile;

/**
 * Generated from net.minecraft.world.entity.projectile.FishingHook. Values use Bukkit types; packed flag fields remain
 * explicit.
 */
public interface VirtualFishingHook extends dev.by1337.entityrender.api.entity.projectile.VirtualProjectile {
    /** NMS DATA_BITING; BOOLEAN. */
    boolean isBiting();

    void setBiting(boolean value);

    /** NMS DATA_HOOKED_ENTITY; INT. */
    int getHookedEntity();

    void setHookedEntity(int value);
}
