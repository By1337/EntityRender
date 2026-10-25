package dev.by1337.entityrender.api.entity.animal.golem;

/**
 * Generated from net.minecraft.world.entity.animal.golem.CopperGolem. Values use Bukkit types; packed flag fields
 * remain explicit.
 */
public interface VirtualCopperGolem extends dev.by1337.entityrender.api.entity.animal.golem.VirtualAbstractGolem {
    /** NMS COPPER_GOLEM_STATE; COPPER_GOLEM_STATE. */
    String getCopperGolemState();

    void setCopperGolemState(String value);

    /** NMS DATA_WEATHER_STATE; WEATHERING_COPPER_STATE. */
    io.papermc.paper.world.WeatheringCopperState getWeatherState();

    void setWeatherState(io.papermc.paper.world.WeatheringCopperState value);
}
