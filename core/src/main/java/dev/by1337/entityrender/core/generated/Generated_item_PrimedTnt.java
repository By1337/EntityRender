package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_item_PrimedTnt extends BaseVirtualEntity implements dev.by1337.entityrender.api.entity.item.VirtualPrimedTnt {
    Generated_item_PrimedTnt(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public org.bukkit.block.data.BlockData getBlockState() {
        return ValueConversions.block(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.item.PrimedTnt.DATA_BLOCK_STATE_ID));
    }

    @Override
    public void setBlockState(org.bukkit.block.data.BlockData value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.item.PrimedTnt.DATA_BLOCK_STATE_ID, ValueConversions.block(value));
    }

    @Override
    public int getFuse() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.item.PrimedTnt.DATA_FUSE_ID);
    }

    @Override
    public void setFuse(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.item.PrimedTnt.DATA_FUSE_ID, value);
    }
}
