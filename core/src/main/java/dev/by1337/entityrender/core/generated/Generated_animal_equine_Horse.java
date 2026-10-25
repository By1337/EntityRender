package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_animal_equine_Horse extends Generated_animal_equine_AbstractHorse implements dev.by1337.entityrender.api.entity.animal.equine.VirtualHorse {
    Generated_animal_equine_Horse(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public int getTypeVariant() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.equine.Horse.DATA_ID_TYPE_VARIANT);
    }

    @Override
    public void setTypeVariant(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.equine.Horse.DATA_ID_TYPE_VARIANT, value);
    }

    @Override
    public org.bukkit.entity.Horse.Color getColor() {
        return org.bukkit.entity.Horse.Color.valueOf(net.minecraft.world.entity.animal.equine.Variant.byId(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.equine.Horse.DATA_ID_TYPE_VARIANT) & 255).name());
    }

    @Override
    public void setColor(org.bukkit.entity.Horse.Color value) {
        update(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.equine.Horse.DATA_ID_TYPE_VARIANT, current -> (current & ~255) | net.minecraft.world.entity.animal.equine.Variant.valueOf(value.name()).getId());
    }

    @Override
    public org.bukkit.entity.Horse.Style getStyle() {
        return org.bukkit.entity.Horse.Style.valueOf(net.minecraft.world.entity.animal.equine.Markings.byId((get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.equine.Horse.DATA_ID_TYPE_VARIANT) >> 8) & 255).name().replace("WHITE_FIELD", "WHITEFIELD"));
    }

    @Override
    public void setStyle(org.bukkit.entity.Horse.Style value) {
        update(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.equine.Horse.DATA_ID_TYPE_VARIANT, current -> (current & ~65280) | net.minecraft.world.entity.animal.equine.Markings.valueOf(value.name().replace("WHITEFIELD", "WHITE_FIELD")).getId() << 8);
    }
}
