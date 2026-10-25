package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_animal_wolf_Wolf extends Generated_TamableAnimal implements dev.by1337.entityrender.api.entity.animal.wolf.VirtualWolf {
    Generated_animal_wolf_Wolf(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public long getAngerEndTime() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.wolf.Wolf.DATA_ANGER_END_TIME);
    }

    @Override
    public void setAngerEndTime(long value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.wolf.Wolf.DATA_ANGER_END_TIME, value);
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
    public org.bukkit.entity.Wolf.SoundVariant getSoundVariant() {
        return org.bukkit.craftbukkit.CraftRegistry.minecraftHolderToBukkit(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.wolf.Wolf.DATA_SOUND_VARIANT_ID), net.minecraft.core.registries.Registries.WOLF_SOUND_VARIANT);
    }

    @Override
    public void setSoundVariant(org.bukkit.entity.Wolf.SoundVariant value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.wolf.Wolf.DATA_SOUND_VARIANT_ID, ValueConversions.holder(java.util.Objects.requireNonNull(value), net.minecraft.core.registries.Registries.WOLF_SOUND_VARIANT));
    }

    @Override
    public org.bukkit.entity.Wolf.Variant getVariant() {
        return org.bukkit.craftbukkit.CraftRegistry.minecraftHolderToBukkit(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.wolf.Wolf.DATA_VARIANT_ID), net.minecraft.core.registries.Registries.WOLF_VARIANT);
    }

    @Override
    public void setVariant(org.bukkit.entity.Wolf.Variant value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.wolf.Wolf.DATA_VARIANT_ID, ValueConversions.holder(java.util.Objects.requireNonNull(value), net.minecraft.core.registries.Registries.WOLF_VARIANT));
    }
}
