package dev.by1337.entityrender.core;

class Generated_animal_frog_Frog extends Generated_animal_Animal implements dev.by1337.entityrender.api.entity.animal.frog.VirtualFrog {
    Generated_animal_frog_Frog(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public java.util.OptionalInt getTongueTarget() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.frog.Frog.DATA_TONGUE_TARGET_ID);
    }

    @Override
    public void setTongueTarget(java.util.OptionalInt value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.frog.Frog.DATA_TONGUE_TARGET_ID, value);
    }

    @Override
    public org.bukkit.entity.Frog.Variant getVariant() {
        return org.bukkit.craftbukkit.CraftRegistry.minecraftHolderToBukkit(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.frog.Frog.DATA_VARIANT_ID), net.minecraft.core.registries.Registries.FROG_VARIANT);
    }

    @Override
    public void setVariant(org.bukkit.entity.Frog.Variant value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.frog.Frog.DATA_VARIANT_ID, ValueConversions.holder(java.util.Objects.requireNonNull(value), net.minecraft.core.registries.Registries.FROG_VARIANT));
    }
}
