package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_animal_goat_Goat extends Generated_animal_Animal implements dev.by1337.entityrender.api.entity.animal.goat.VirtualGoat {
    Generated_animal_goat_Goat(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isHasLeftHorn() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.goat.Goat.DATA_HAS_LEFT_HORN);
    }

    @Override
    public void setHasLeftHorn(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.goat.Goat.DATA_HAS_LEFT_HORN, value);
    }

    @Override
    public boolean isHasRightHorn() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.goat.Goat.DATA_HAS_RIGHT_HORN);
    }

    @Override
    public void setHasRightHorn(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.goat.Goat.DATA_HAS_RIGHT_HORN, value);
    }

    @Override
    public boolean isScreamingGoat() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.goat.Goat.DATA_IS_SCREAMING_GOAT);
    }

    @Override
    public void setScreamingGoat(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.goat.Goat.DATA_IS_SCREAMING_GOAT, value);
    }
}
