package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_vehicle_minecart_AbstractMinecart extends Generated_vehicle_VehicleEntity implements dev.by1337.entityrender.api.entity.vehicle.minecart.VirtualAbstractMinecart {
    Generated_vehicle_minecart_AbstractMinecart(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public org.bukkit.block.data.BlockData getCustomDisplayBlock() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.vehicle.minecart.AbstractMinecart.DATA_ID_CUSTOM_DISPLAY_BLOCK).map(ValueConversions::block).orElse(null);
    }

    @Override
    public void setCustomDisplayBlock(org.bukkit.block.data.BlockData value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.vehicle.minecart.AbstractMinecart.DATA_ID_CUSTOM_DISPLAY_BLOCK, java.util.Optional.ofNullable(value).map(ValueConversions::block));
    }

    @Override
    public int getDisplayOffset() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.vehicle.minecart.AbstractMinecart.DATA_ID_DISPLAY_OFFSET);
    }

    @Override
    public void setDisplayOffset(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.vehicle.minecart.AbstractMinecart.DATA_ID_DISPLAY_OFFSET, value);
    }
}
