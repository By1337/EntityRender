package dev.by1337.entityrender.core;

class Generated_decoration_Painting extends Generated_decoration_HangingEntity implements dev.by1337.entityrender.api.entity.decoration.VirtualPainting {
    Generated_decoration_Painting(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public org.bukkit.Art getPaintingVariant() {
        return org.bukkit.craftbukkit.CraftRegistry.minecraftHolderToBukkit(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.decoration.Painting.DATA_PAINTING_VARIANT_ID), net.minecraft.core.registries.Registries.PAINTING_VARIANT);
    }

    @Override
    public void setPaintingVariant(org.bukkit.Art value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.decoration.Painting.DATA_PAINTING_VARIANT_ID, org.bukkit.craftbukkit.CraftRegistry.bukkitToMinecraftHolder(java.util.Objects.requireNonNull(value), net.minecraft.core.registries.Registries.PAINTING_VARIANT));
    }

    @Override
    public org.bukkit.block.BlockFace getFacing() {
        return ValueConversions.direction(net.minecraft.core.Direction.from3DDataValue(getSpawnData()));
    }

    @Override
    public void setFacing(org.bukkit.block.BlockFace value) {
        if (value == org.bukkit.block.BlockFace.UP || value == org.bukkit.block.BlockFace.DOWN) throw new IllegalArgumentException("Painting requires a horizontal face");
        setSpawnData(ValueConversions.direction(value).get3DDataValue());
    }
}
