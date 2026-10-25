package dev.by1337.entityrender.api.entity.projectile;

/**
 * Generated from net.minecraft.world.entity.projectile.FireworkRocketEntity. Values use Bukkit types; packed flag
 * fields remain explicit.
 */
public interface VirtualFireworkRocketEntity extends dev.by1337.entityrender.api.entity.projectile.VirtualProjectile {
    /** NMS DATA_ATTACHED_TO_TARGET; OPTIONAL_UNSIGNED_INT. */
    java.util.OptionalInt getAttachedToTarget();

    void setAttachedToTarget(java.util.OptionalInt value);

    /** NMS DATA_ID_FIREWORKS_ITEM; ITEM_STACK. */
    org.bukkit.inventory.ItemStack getFireworksItem();

    void setFireworksItem(org.bukkit.inventory.ItemStack value);

    /** NMS DATA_SHOT_AT_ANGLE; BOOLEAN. */
    boolean isShotAtAngle();

    void setShotAtAngle(boolean value);
}
