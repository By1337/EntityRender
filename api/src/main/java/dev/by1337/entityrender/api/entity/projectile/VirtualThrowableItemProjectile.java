package dev.by1337.entityrender.api.entity.projectile;

/**
 * Generated from net.minecraft.world.entity.projectile.ThrowableItemProjectile. Values use Bukkit types; packed flag
 * fields remain explicit.
 */
public interface VirtualThrowableItemProjectile extends dev.by1337.entityrender.api.entity.projectile.VirtualThrowableProjectile {
    /** NMS DATA_ITEM_STACK; ITEM_STACK. */
    org.bukkit.inventory.ItemStack getItemStack();

    void setItemStack(org.bukkit.inventory.ItemStack value);
}
