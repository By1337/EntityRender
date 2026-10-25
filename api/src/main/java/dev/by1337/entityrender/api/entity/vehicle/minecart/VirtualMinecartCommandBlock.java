package dev.by1337.entityrender.api.entity.vehicle.minecart;

/**
 * Generated from net.minecraft.world.entity.vehicle.minecart.MinecartCommandBlock. Values use Bukkit types; packed flag
 * fields remain explicit.
 */
public interface VirtualMinecartCommandBlock extends dev.by1337.entityrender.api.entity.vehicle.minecart.VirtualAbstractMinecart {
    /** NMS DATA_ID_COMMAND_NAME; STRING. */
    String getCommandName();

    void setCommandName(String value);

    /** NMS DATA_ID_LAST_OUTPUT; COMPONENT. */
    net.kyori.adventure.text.Component getLastOutput();

    void setLastOutput(net.kyori.adventure.text.Component value);
}
