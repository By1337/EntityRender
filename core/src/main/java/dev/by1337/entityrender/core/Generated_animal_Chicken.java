package dev.by1337.entityrender.core;

class Generated_animal_Chicken extends Generated_animal_Animal implements dev.by1337.entityrender.api.entity.animal.VirtualChicken {
    Generated_animal_Chicken(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public org.bukkit.NamespacedKey getVariant() {
        return org.bukkit.NamespacedKey.fromString(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Chicken.DATA_VARIANT_ID).unwrapKey().orElseThrow().location().toString());
    }

    @Override
    public void setVariant(org.bukkit.NamespacedKey value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Chicken.DATA_VARIANT_ID, registries().lookupOrThrow(net.minecraft.core.registries.Registries.CHICKEN_VARIANT).getOrThrow(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.CHICKEN_VARIANT, net.minecraft.resources.ResourceLocation.parse(value.toString()))));
    }
}
