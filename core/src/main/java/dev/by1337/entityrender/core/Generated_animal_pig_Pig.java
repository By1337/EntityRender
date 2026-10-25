package dev.by1337.entityrender.core;

class Generated_animal_pig_Pig extends Generated_animal_Animal implements dev.by1337.entityrender.api.entity.animal.pig.VirtualPig {
    Generated_animal_pig_Pig(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public int getBoostTime() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.pig.Pig.DATA_BOOST_TIME);
    }

    @Override
    public void setBoostTime(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.pig.Pig.DATA_BOOST_TIME, value);
    }

    @Override
    public org.bukkit.entity.Pig.SoundVariant getSoundVariant() {
        return org.bukkit.craftbukkit.CraftRegistry.minecraftHolderToBukkit(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.pig.Pig.DATA_SOUND_VARIANT_ID), net.minecraft.core.registries.Registries.PIG_SOUND_VARIANT);
    }

    @Override
    public void setSoundVariant(org.bukkit.entity.Pig.SoundVariant value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.pig.Pig.DATA_SOUND_VARIANT_ID, ValueConversions.holder(java.util.Objects.requireNonNull(value), net.minecraft.core.registries.Registries.PIG_SOUND_VARIANT));
    }

    @Override
    public org.bukkit.entity.Pig.Variant getVariant() {
        return org.bukkit.craftbukkit.CraftRegistry.minecraftHolderToBukkit(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.pig.Pig.DATA_VARIANT_ID), net.minecraft.core.registries.Registries.PIG_VARIANT);
    }

    @Override
    public void setVariant(org.bukkit.entity.Pig.Variant value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.pig.Pig.DATA_VARIANT_ID, ValueConversions.holder(java.util.Objects.requireNonNull(value), net.minecraft.core.registries.Registries.PIG_VARIANT));
    }
}
