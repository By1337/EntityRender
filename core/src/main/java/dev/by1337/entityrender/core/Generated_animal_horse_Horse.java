package dev.by1337.entityrender.core;

class Generated_animal_horse_Horse extends Generated_animal_horse_AbstractHorse implements dev.by1337.entityrender.api.entity.animal.horse.VirtualHorse {
    Generated_animal_horse_Horse(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public int getTypeVariant() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.horse.Horse.DATA_ID_TYPE_VARIANT);
    }

    @Override
    public void setTypeVariant(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.horse.Horse.DATA_ID_TYPE_VARIANT, value);
    }

    @Override
    public org.bukkit.entity.Horse.Color getColor() {
        return org.bukkit.entity.Horse.Color.valueOf(net.minecraft.world.entity.animal.horse.Variant.byId(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.horse.Horse.DATA_ID_TYPE_VARIANT) & 255).name());
    }

    @Override
    public void setColor(org.bukkit.entity.Horse.Color value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.horse.Horse.DATA_ID_TYPE_VARIANT, (get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.horse.Horse.DATA_ID_TYPE_VARIANT) & ~255) | net.minecraft.world.entity.animal.horse.Variant.valueOf(value.name()).getId());
    }

    @Override
    public org.bukkit.entity.Horse.Style getStyle() {
        return org.bukkit.entity.Horse.Style.valueOf(net.minecraft.world.entity.animal.horse.Markings.byId((get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.horse.Horse.DATA_ID_TYPE_VARIANT) >> 8) & 255).name().replace("WHITE_FIELD", "WHITEFIELD"));
    }

    @Override
    public void setStyle(org.bukkit.entity.Horse.Style value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.horse.Horse.DATA_ID_TYPE_VARIANT, (get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.horse.Horse.DATA_ID_TYPE_VARIANT) & ~65280) | net.minecraft.world.entity.animal.horse.Markings.valueOf(value.name().replace("WHITEFIELD", "WHITE_FIELD")).getId() << 8);
    }
}
