package dev.by1337.entityrender.core;

class Generated_vehicle_VehicleEntity extends BaseVirtualEntity implements dev.by1337.entityrender.api.entity.vehicle.VirtualVehicleEntity {
    Generated_vehicle_VehicleEntity(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public float getDamage() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.vehicle.VehicleEntity.DATA_ID_DAMAGE);
    }

    @Override
    public void setDamage(float value) {
        finite(value);
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.vehicle.VehicleEntity.DATA_ID_DAMAGE, value);
    }

    @Override
    public int getHurt() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.vehicle.VehicleEntity.DATA_ID_HURT);
    }

    @Override
    public void setHurt(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.vehicle.VehicleEntity.DATA_ID_HURT, value);
    }

    @Override
    public int getHurtdir() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.vehicle.VehicleEntity.DATA_ID_HURTDIR);
    }

    @Override
    public void setHurtdir(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.vehicle.VehicleEntity.DATA_ID_HURTDIR, value);
    }
}
