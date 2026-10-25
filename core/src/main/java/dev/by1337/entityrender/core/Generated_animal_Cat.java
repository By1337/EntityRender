package dev.by1337.entityrender.core;

class Generated_animal_Cat extends Generated_TamableAnimal implements dev.by1337.entityrender.api.entity.animal.VirtualCat {
    Generated_animal_Cat(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public org.bukkit.DyeColor getCollarColor() {
        return org.bukkit.DyeColor.valueOf(net.minecraft.world.item.DyeColor.byId(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Cat.DATA_COLLAR_COLOR)).name());
    }

    @Override
    public void setCollarColor(org.bukkit.DyeColor value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Cat.DATA_COLLAR_COLOR, net.minecraft.world.item.DyeColor.valueOf(java.util.Objects.requireNonNull(value).name()).getId());
    }

    @Override
    public org.bukkit.entity.Cat.Type getVariant() {
        return org.bukkit.craftbukkit.CraftRegistry.minecraftHolderToBukkit(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Cat.DATA_VARIANT_ID), net.minecraft.core.registries.Registries.CAT_VARIANT);
    }

    @Override
    public void setVariant(org.bukkit.entity.Cat.Type value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Cat.DATA_VARIANT_ID, org.bukkit.craftbukkit.CraftRegistry.bukkitToMinecraftHolder(java.util.Objects.requireNonNull(value), net.minecraft.core.registries.Registries.CAT_VARIANT));
    }

    @Override
    public boolean isLying() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Cat.IS_LYING);
    }

    @Override
    public void setLying(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Cat.IS_LYING, value);
    }

    @Override
    public boolean isRelaxStateOne() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Cat.RELAX_STATE_ONE);
    }

    @Override
    public void setRelaxStateOne(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Cat.RELAX_STATE_ONE, value);
    }
}
