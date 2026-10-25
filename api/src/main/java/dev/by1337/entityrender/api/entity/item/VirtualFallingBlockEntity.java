package dev.by1337.entityrender.api.entity.item;

/**
 * Generated from net.minecraft.world.entity.item.FallingBlockEntity. Values use Bukkit types; packed flag fields remain
 * explicit.
 */
public interface VirtualFallingBlockEntity extends dev.by1337.entityrender.api.entity.VirtualEntity {
    /** NMS DATA_START_POS; BLOCK_POS. */
    org.bukkit.util.BlockVector getStartPos();

    void setStartPos(org.bukkit.util.BlockVector value);

    org.bukkit.block.data.BlockData getBlockData();

    void setBlockData(org.bukkit.block.data.BlockData value);
}
