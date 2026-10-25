package dev.by1337.entityrender.api.entity.vehicle.minecart;

/**
 * Generated from net.minecraft.world.entity.vehicle.minecart.AbstractMinecart. Values use Bukkit types; packed flag
 * fields remain explicit.
 */
public interface VirtualAbstractMinecart extends dev.by1337.entityrender.api.entity.vehicle.VirtualVehicleEntity {
    /** NMS DATA_ID_CUSTOM_DISPLAY_BLOCK; OPTIONAL_BLOCK_STATE. */
    org.bukkit.block.data.BlockData getCustomDisplayBlock();

    void setCustomDisplayBlock(org.bukkit.block.data.BlockData value);

    /** NMS DATA_ID_DISPLAY_OFFSET; INT. */
    int getDisplayOffset();

    void setDisplayOffset(int value);
}
