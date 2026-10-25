package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_animal_camel_Camel extends Generated_animal_equine_AbstractHorse implements dev.by1337.entityrender.api.entity.animal.camel.VirtualCamel {
    Generated_animal_camel_Camel(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isDash() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.camel.Camel.DASH);
    }

    @Override
    public void setDash(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.camel.Camel.DASH, value);
    }

    @Override
    public long getLastPoseChangeTick() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.camel.Camel.LAST_POSE_CHANGE_TICK);
    }

    @Override
    public void setLastPoseChangeTick(long value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.camel.Camel.LAST_POSE_CHANGE_TICK, value);
    }
}
