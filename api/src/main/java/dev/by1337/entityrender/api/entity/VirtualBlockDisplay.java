package dev.by1337.entityrender.api.entity;

import org.bukkit.block.data.BlockData;

public interface VirtualBlockDisplay extends VirtualDisplay {
    BlockData getBlockData();

    void setBlockData(BlockData block);
}
