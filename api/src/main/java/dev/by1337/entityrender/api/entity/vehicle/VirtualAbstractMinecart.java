package dev.by1337.entityrender.api.entity.vehicle;

/**
 * Generated from net.minecraft.world.entity.vehicle.AbstractMinecart. Values use Bukkit types; packed flag fields
 * remain explicit.
 */
public interface VirtualAbstractMinecart extends dev.by1337.entityrender.api.entity.vehicle.VirtualVehicleEntity {
    /** NMS DATA_ID_CUSTOM_DISPLAY; BOOLEAN. */
    boolean isCustomDisplay();

    void setCustomDisplay(boolean value);

    /** NMS DATA_ID_DISPLAY_BLOCK; INT. */
    int getDisplayBlock();

    void setDisplayBlock(int value);

    /** NMS DATA_ID_DISPLAY_OFFSET; INT. */
    int getDisplayOffset();

    void setDisplayOffset(int value);
}
