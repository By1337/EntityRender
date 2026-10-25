package dev.by1337.entityrender.api.entity.item;

/**
 * Generated from net.minecraft.world.entity.item.PrimedTnt. Values use Bukkit types; packed flag fields remain
 * explicit.
 */
public interface VirtualPrimedTnt extends dev.by1337.entityrender.api.entity.VirtualEntity {
    /** NMS DATA_BLOCK_STATE_ID; BLOCK_STATE. */
    org.bukkit.block.data.BlockData getBlockState();

    void setBlockState(org.bukkit.block.data.BlockData value);

    /** NMS DATA_FUSE_ID; INT. */
    int getFuse();

    void setFuse(int value);
}
