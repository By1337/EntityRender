package dev.by1337.entityrender.core;

class Generated_animal_coppergolem_CopperGolem extends Generated_animal_AbstractGolem implements dev.by1337.entityrender.api.entity.animal.coppergolem.VirtualCopperGolem {
    Generated_animal_coppergolem_CopperGolem(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public String getCopperGolemState() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.coppergolem.CopperGolem.COPPER_GOLEM_STATE).name();
    }

    @Override
    public void setCopperGolemState(String value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.coppergolem.CopperGolem.COPPER_GOLEM_STATE, net.minecraft.world.entity.animal.coppergolem.CopperGolemState.valueOf(java.util.Objects.requireNonNull(value)));
    }

    @Override
    public io.papermc.paper.world.WeatheringCopperState getWeatherState() {
        return io.papermc.paper.world.WeatheringCopperState.valueOf(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.coppergolem.CopperGolem.DATA_WEATHER_STATE).name());
    }

    @Override
    public void setWeatherState(io.papermc.paper.world.WeatheringCopperState value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.coppergolem.CopperGolem.DATA_WEATHER_STATE, net.minecraft.world.level.block.WeatheringCopper.WeatherState.valueOf(java.util.Objects.requireNonNull(value).name()));
    }
}
