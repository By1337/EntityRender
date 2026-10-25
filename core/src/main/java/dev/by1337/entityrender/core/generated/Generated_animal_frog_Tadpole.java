package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_animal_frog_Tadpole extends Generated_animal_fish_AbstractFish implements dev.by1337.entityrender.api.entity.animal.frog.VirtualTadpole {
    Generated_animal_frog_Tadpole(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isAgeLocked() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.frog.Tadpole.AGE_LOCKED);
    }

    @Override
    public void setAgeLocked(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.frog.Tadpole.AGE_LOCKED, value);
    }
}
