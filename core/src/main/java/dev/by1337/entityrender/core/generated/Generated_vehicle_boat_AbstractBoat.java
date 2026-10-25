package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_vehicle_boat_AbstractBoat extends Generated_vehicle_VehicleEntity implements dev.by1337.entityrender.api.entity.vehicle.boat.VirtualAbstractBoat {
    Generated_vehicle_boat_AbstractBoat(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public int getBubbleTime() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.vehicle.boat.AbstractBoat.DATA_ID_BUBBLE_TIME);
    }

    @Override
    public void setBubbleTime(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.vehicle.boat.AbstractBoat.DATA_ID_BUBBLE_TIME, value);
    }

    @Override
    public boolean isPaddleLeft() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.vehicle.boat.AbstractBoat.DATA_ID_PADDLE_LEFT);
    }

    @Override
    public void setPaddleLeft(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.vehicle.boat.AbstractBoat.DATA_ID_PADDLE_LEFT, value);
    }

    @Override
    public boolean isPaddleRight() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.vehicle.boat.AbstractBoat.DATA_ID_PADDLE_RIGHT);
    }

    @Override
    public void setPaddleRight(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.vehicle.boat.AbstractBoat.DATA_ID_PADDLE_RIGHT, value);
    }
}
