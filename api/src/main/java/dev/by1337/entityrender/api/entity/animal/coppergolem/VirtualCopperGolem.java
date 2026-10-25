package dev.by1337.entityrender.api.entity.animal.coppergolem;

/**
 * Generated from net.minecraft.world.entity.animal.coppergolem.CopperGolem. Values use Bukkit types; packed flag fields
 * remain explicit.
 */
public interface VirtualCopperGolem extends dev.by1337.entityrender.api.entity.animal.VirtualAbstractGolem {
    /** NMS COPPER_GOLEM_STATE; COPPER_GOLEM_STATE. */
    String getCopperGolemState();

    void setCopperGolemState(String value);

    /** NMS DATA_WEATHER_STATE; WEATHERING_COPPER_STATE. */
    io.papermc.paper.world.WeatheringCopperState getWeatherState();

    void setWeatherState(io.papermc.paper.world.WeatheringCopperState value);
}
