package dev.by1337.entityrender.core;

class Generated_animal_wolf_Wolf extends Generated_TamableAnimal implements dev.by1337.entityrender.api.entity.animal.wolf.VirtualWolf {
    Generated_animal_wolf_Wolf(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public org.bukkit.DyeColor getCollarColor() {
        return org.bukkit.DyeColor.valueOf(net.minecraft.world.item.DyeColor.byId(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.wolf.Wolf.DATA_COLLAR_COLOR)).name());
    }

    @Override
    public void setCollarColor(org.bukkit.DyeColor value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.wolf.Wolf.DATA_COLLAR_COLOR, net.minecraft.world.item.DyeColor.valueOf(java.util.Objects.requireNonNull(value).name()).getId());
    }

    @Override
    public boolean isInterested() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.wolf.Wolf.DATA_INTERESTED_ID);
    }

    @Override
    public void setInterested(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.wolf.Wolf.DATA_INTERESTED_ID, value);
    }

    @Override
    public int getRemainingAngerTime() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.wolf.Wolf.DATA_REMAINING_ANGER_TIME);
    }

    @Override
    public void setRemainingAngerTime(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.wolf.Wolf.DATA_REMAINING_ANGER_TIME, value);
    }

    @Override
    public org.bukkit.NamespacedKey getSoundVariant() {
        return org.bukkit.NamespacedKey.fromString(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.wolf.Wolf.DATA_SOUND_VARIANT_ID).unwrapKey().orElseThrow().location().toString());
    }

    @Override
    public void setSoundVariant(org.bukkit.NamespacedKey value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.wolf.Wolf.DATA_SOUND_VARIANT_ID, registries().lookupOrThrow(net.minecraft.core.registries.Registries.WOLF_SOUND_VARIANT).getOrThrow(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.WOLF_SOUND_VARIANT, net.minecraft.resources.ResourceLocation.parse(value.toString()))));
    }

    @Override
    public org.bukkit.entity.Wolf.Variant getVariant() {
        return org.bukkit.craftbukkit.CraftRegistry.minecraftHolderToBukkit(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.wolf.Wolf.DATA_VARIANT_ID), net.minecraft.core.registries.Registries.WOLF_VARIANT);
    }

    @Override
    public void setVariant(org.bukkit.entity.Wolf.Variant value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.wolf.Wolf.DATA_VARIANT_ID, org.bukkit.craftbukkit.CraftRegistry.bukkitToMinecraftHolder(java.util.Objects.requireNonNull(value), net.minecraft.core.registries.Registries.WOLF_VARIANT));
    }
}
