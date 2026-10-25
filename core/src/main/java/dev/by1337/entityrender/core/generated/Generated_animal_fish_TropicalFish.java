package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_animal_fish_TropicalFish extends Generated_animal_fish_AbstractSchoolingFish implements dev.by1337.entityrender.api.entity.animal.fish.VirtualTropicalFish {
    Generated_animal_fish_TropicalFish(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public int getTypeVariant() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.fish.TropicalFish.DATA_ID_TYPE_VARIANT);
    }

    @Override
    public void setTypeVariant(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.fish.TropicalFish.DATA_ID_TYPE_VARIANT, value);
    }
}
