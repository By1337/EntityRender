package dev.by1337.entityrender.api.entity.decoration;

/**
 * Generated from net.minecraft.world.entity.decoration.ItemFrame. Values use Bukkit types; packed flag fields remain
 * explicit.
 */
public interface VirtualItemFrame extends dev.by1337.entityrender.api.entity.decoration.VirtualHangingEntity {
    /** NMS DATA_ITEM; ITEM_STACK. */
    org.bukkit.inventory.ItemStack getItem();

    void setItem(org.bukkit.inventory.ItemStack value);

    /** NMS DATA_ROTATION; INT. */
    int getRotation();

    void setRotation(int value);

    org.bukkit.block.BlockFace getFacing();

    void setFacing(org.bukkit.block.BlockFace value);
}
