package dev.by1337.entityrender.core;

class Generated_vehicle_MinecartCommandBlock extends Generated_vehicle_AbstractMinecart implements dev.by1337.entityrender.api.entity.vehicle.VirtualMinecartCommandBlock {
    Generated_vehicle_MinecartCommandBlock(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public String getCommandName() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.vehicle.MinecartCommandBlock.DATA_ID_COMMAND_NAME);
    }

    @Override
    public void setCommandName(String value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.vehicle.MinecartCommandBlock.DATA_ID_COMMAND_NAME, value);
    }

    @Override
    public net.kyori.adventure.text.Component getLastOutput() {
        return io.papermc.paper.adventure.PaperAdventure.asAdventure(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.vehicle.MinecartCommandBlock.DATA_ID_LAST_OUTPUT));
    }

    @Override
    public void setLastOutput(net.kyori.adventure.text.Component value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.vehicle.MinecartCommandBlock.DATA_ID_LAST_OUTPUT, io.papermc.paper.adventure.PaperAdventure.asVanilla(java.util.Objects.requireNonNull(value)));
    }
}
