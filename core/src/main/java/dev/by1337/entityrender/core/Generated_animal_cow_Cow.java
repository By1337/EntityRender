package dev.by1337.entityrender.core;

class Generated_animal_cow_Cow extends Generated_animal_cow_AbstractCow implements dev.by1337.entityrender.api.entity.animal.cow.VirtualCow {
    Generated_animal_cow_Cow(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public org.bukkit.entity.Cow.SoundVariant getSoundVariant() {
        return org.bukkit.craftbukkit.CraftRegistry.minecraftHolderToBukkit(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.cow.Cow.DATA_SOUND_VARIANT_ID), net.minecraft.core.registries.Registries.COW_SOUND_VARIANT);
    }

    @Override
    public void setSoundVariant(org.bukkit.entity.Cow.SoundVariant value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.cow.Cow.DATA_SOUND_VARIANT_ID, ValueConversions.holder(java.util.Objects.requireNonNull(value), net.minecraft.core.registries.Registries.COW_SOUND_VARIANT));
    }

    @Override
    public org.bukkit.entity.Cow.Variant getVariant() {
        return org.bukkit.craftbukkit.CraftRegistry.minecraftHolderToBukkit(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.cow.Cow.DATA_VARIANT_ID), net.minecraft.core.registries.Registries.COW_VARIANT);
    }

    @Override
    public void setVariant(org.bukkit.entity.Cow.Variant value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.cow.Cow.DATA_VARIANT_ID, ValueConversions.holder(java.util.Objects.requireNonNull(value), net.minecraft.core.registries.Registries.COW_VARIANT));
    }
}
