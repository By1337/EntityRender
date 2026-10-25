package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_vehicle_minecart_MinecartFurnace extends Generated_vehicle_minecart_AbstractMinecart implements dev.by1337.entityrender.api.entity.vehicle.minecart.VirtualMinecartFurnace {
    Generated_vehicle_minecart_MinecartFurnace(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isFuel() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.vehicle.minecart.MinecartFurnace.DATA_ID_FUEL);
    }

    @Override
    public void setFuel(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.vehicle.minecart.MinecartFurnace.DATA_ID_FUEL, value);
    }
}
