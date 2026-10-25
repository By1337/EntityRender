package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_decoration_HangingEntity extends Generated_decoration_BlockAttachedEntity implements dev.by1337.entityrender.api.entity.decoration.VirtualHangingEntity {
    Generated_decoration_HangingEntity(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public org.bukkit.block.BlockFace getDirection() {
        return ValueConversions.direction(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.decoration.HangingEntity.DATA_DIRECTION));
    }

    @Override
    public void setDirection(org.bukkit.block.BlockFace value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.decoration.HangingEntity.DATA_DIRECTION, ValueConversions.direction(value));
    }
}
