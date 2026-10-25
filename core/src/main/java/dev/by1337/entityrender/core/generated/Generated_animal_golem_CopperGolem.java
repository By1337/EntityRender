package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_animal_golem_CopperGolem extends Generated_animal_golem_AbstractGolem implements dev.by1337.entityrender.api.entity.animal.golem.VirtualCopperGolem {
    Generated_animal_golem_CopperGolem(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public org.bukkit.entity.CopperGolem.State getCopperGolemState() {
        return org.bukkit.entity.CopperGolem.State.valueOf(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.golem.CopperGolem.COPPER_GOLEM_STATE).name());
    }

    @Override
    public void setCopperGolemState(org.bukkit.entity.CopperGolem.State value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.golem.CopperGolem.COPPER_GOLEM_STATE, net.minecraft.world.entity.animal.golem.CopperGolemState.valueOf(java.util.Objects.requireNonNull(value).name()));
    }

    @Override
    public io.papermc.paper.world.WeatheringCopperState getWeatherState() {
        return io.papermc.paper.world.WeatheringCopperState.valueOf(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.golem.CopperGolem.DATA_WEATHER_STATE).name());
    }

    @Override
    public void setWeatherState(io.papermc.paper.world.WeatheringCopperState value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.golem.CopperGolem.DATA_WEATHER_STATE, net.minecraft.world.level.block.WeatheringCopper.WeatherState.valueOf(java.util.Objects.requireNonNull(value).name()));
    }
}
