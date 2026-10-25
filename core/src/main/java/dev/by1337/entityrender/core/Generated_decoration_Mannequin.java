package dev.by1337.entityrender.core;

class Generated_decoration_Mannequin extends Generated_Avatar implements dev.by1337.entityrender.api.entity.decoration.VirtualMannequin {
    Generated_decoration_Mannequin(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public net.kyori.adventure.text.Component getDescription() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.decoration.Mannequin.DATA_DESCRIPTION).map(io.papermc.paper.adventure.PaperAdventure::asAdventure).orElse(null);
    }

    @Override
    public void setDescription(net.kyori.adventure.text.Component value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.decoration.Mannequin.DATA_DESCRIPTION, java.util.Optional.ofNullable(value).map(io.papermc.paper.adventure.PaperAdventure::asVanilla));
    }

    @Override
    public boolean isImmovable() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.decoration.Mannequin.DATA_IMMOVABLE);
    }

    @Override
    public void setImmovable(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.decoration.Mannequin.DATA_IMMOVABLE, value);
    }

    @Override
    public String getProfileSnbt() {
        return ValueConversions.encode(net.minecraft.world.item.component.ResolvableProfile.CODEC, get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.decoration.Mannequin.DATA_PROFILE), registries());
    }

    @Override
    public void setProfileSnbt(String value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.decoration.Mannequin.DATA_PROFILE, ValueConversions.decode(net.minecraft.world.item.component.ResolvableProfile.CODEC, value, registries()));
    }
}
