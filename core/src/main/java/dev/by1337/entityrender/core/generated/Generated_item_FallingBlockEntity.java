package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_item_FallingBlockEntity extends BaseVirtualEntity implements dev.by1337.entityrender.api.entity.item.VirtualFallingBlockEntity {
    Generated_item_FallingBlockEntity(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public org.bukkit.util.BlockVector getStartPos() {
        return ValueConversions.position(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.item.FallingBlockEntity.DATA_START_POS));
    }

    @Override
    public void setStartPos(org.bukkit.util.BlockVector value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.item.FallingBlockEntity.DATA_START_POS, ValueConversions.position(value));
    }

    @Override
    public org.bukkit.block.data.BlockData getBlockData() {
        return ValueConversions.block(net.minecraft.world.level.block.Block.stateById(getSpawnData()));
    }

    @Override
    public void setBlockData(org.bukkit.block.data.BlockData value) {
        setSpawnData(net.minecraft.world.level.block.Block.getId(ValueConversions.block(value)));
    }
}
