package dev.by1337.entityrender.core;

class Generated_animal_chicken_Chicken extends Generated_animal_Animal implements dev.by1337.entityrender.api.entity.animal.chicken.VirtualChicken {
    Generated_animal_chicken_Chicken(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public org.bukkit.entity.Chicken.SoundVariant getSoundVariant() {
        return org.bukkit.craftbukkit.CraftRegistry.minecraftHolderToBukkit(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.chicken.Chicken.DATA_SOUND_VARIANT_ID), net.minecraft.core.registries.Registries.CHICKEN_SOUND_VARIANT);
    }

    @Override
    public void setSoundVariant(org.bukkit.entity.Chicken.SoundVariant value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.chicken.Chicken.DATA_SOUND_VARIANT_ID, ValueConversions.holder(java.util.Objects.requireNonNull(value), net.minecraft.core.registries.Registries.CHICKEN_SOUND_VARIANT));
    }

    @Override
    public org.bukkit.entity.Chicken.Variant getVariant() {
        return org.bukkit.craftbukkit.CraftRegistry.minecraftHolderToBukkit(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.chicken.Chicken.DATA_VARIANT_ID), net.minecraft.core.registries.Registries.CHICKEN_VARIANT);
    }

    @Override
    public void setVariant(org.bukkit.entity.Chicken.Variant value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.chicken.Chicken.DATA_VARIANT_ID, ValueConversions.holder(java.util.Objects.requireNonNull(value), net.minecraft.core.registries.Registries.CHICKEN_VARIANT));
    }
}
