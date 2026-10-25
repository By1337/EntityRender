package dev.by1337.entityrender.core;

class Generated_vehicle_AbstractMinecart extends Generated_vehicle_VehicleEntity implements dev.by1337.entityrender.api.entity.vehicle.VirtualAbstractMinecart {
    Generated_vehicle_AbstractMinecart(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isCustomDisplay() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.vehicle.AbstractMinecart.DATA_ID_CUSTOM_DISPLAY);
    }

    @Override
    public void setCustomDisplay(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.vehicle.AbstractMinecart.DATA_ID_CUSTOM_DISPLAY, value);
    }

    @Override
    public int getDisplayBlock() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.vehicle.AbstractMinecart.DATA_ID_DISPLAY_BLOCK);
    }

    @Override
    public void setDisplayBlock(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.vehicle.AbstractMinecart.DATA_ID_DISPLAY_BLOCK, value);
    }

    @Override
    public int getDisplayOffset() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.vehicle.AbstractMinecart.DATA_ID_DISPLAY_OFFSET);
    }

    @Override
    public void setDisplayOffset(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.vehicle.AbstractMinecart.DATA_ID_DISPLAY_OFFSET, value);
    }
}
