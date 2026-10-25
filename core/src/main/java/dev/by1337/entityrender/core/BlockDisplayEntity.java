package dev.by1337.entityrender.core;

import dev.by1337.entityrender.api.entity.VirtualBlockDisplay;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.block.data.CraftBlockData;
import org.bukkit.entity.EntityType;
import java.util.Objects;
import dev.by1337.entityrender.core.metadata.GeneratedMetadata;

final class BlockDisplayEntity extends AbstractDisplayEntity implements VirtualBlockDisplay {
    BlockDisplayEntity(PaperEntityRenderer owner, int id, Location location) {
        super(owner, id, location, EntityType.BLOCK_DISPLAY, net.minecraft.world.entity.EntityType.BLOCK_DISPLAY, GeneratedMetadata.Display.BlockDisplay.LAYOUT);
    }

    @Override
    public BlockData getBlockData() {
        return CraftBlockData.createData(get(GeneratedMetadata.Display.BlockDisplay.DATA_BLOCK_STATE_ID));
    }

    @Override
    public void setBlockData(BlockData block) {
        checkOpen();
        set(GeneratedMetadata.Display.BlockDisplay.DATA_BLOCK_STATE_ID, ((CraftBlockData) Objects.requireNonNull(block)).getState());
    }
}
