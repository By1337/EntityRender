package dev.by1337.entityrender.api.entity;

/**
 * Generated from net.minecraft.world.entity.OminousItemSpawner. Values use Bukkit types; packed flag fields remain
 * explicit.
 */
public interface VirtualOminousItemSpawner extends dev.by1337.entityrender.api.entity.VirtualEntity {
    /** NMS DATA_ITEM; ITEM_STACK. */
    org.bukkit.inventory.ItemStack getItem();

    void setItem(org.bukkit.inventory.ItemStack value);
}
