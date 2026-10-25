package dev.by1337.entityrender.api.entity.decoration.painting;

/**
 * Generated from net.minecraft.world.entity.decoration.painting.Painting. Values use Bukkit types; packed flag fields
 * remain explicit.
 */
public interface VirtualPainting extends dev.by1337.entityrender.api.entity.decoration.VirtualHangingEntity {
    /** NMS DATA_PAINTING_VARIANT_ID; PAINTING_VARIANT. */
    org.bukkit.Art getPaintingVariant();

    void setPaintingVariant(org.bukkit.Art value);

    org.bukkit.block.BlockFace getFacing();

    void setFacing(org.bukkit.block.BlockFace value);
}
