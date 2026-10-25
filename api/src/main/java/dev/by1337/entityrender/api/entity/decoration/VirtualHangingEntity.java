package dev.by1337.entityrender.api.entity.decoration;

/**
 * Generated from net.minecraft.world.entity.decoration.HangingEntity. Values use Bukkit types; packed flag fields
 * remain explicit.
 */
public interface VirtualHangingEntity extends dev.by1337.entityrender.api.entity.decoration.VirtualBlockAttachedEntity {
    /** NMS DATA_DIRECTION; DIRECTION. */
    org.bukkit.block.BlockFace getDirection();

    void setDirection(org.bukkit.block.BlockFace value);
}
