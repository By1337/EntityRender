package dev.by1337.entityrender.core;

class Generated_animal_allay_Allay extends Generated_PathfinderMob implements dev.by1337.entityrender.api.entity.animal.allay.VirtualAllay {
    Generated_animal_allay_Allay(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isCanDuplicate() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.allay.Allay.DATA_CAN_DUPLICATE);
    }

    @Override
    public void setCanDuplicate(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.allay.Allay.DATA_CAN_DUPLICATE, value);
    }

    @Override
    public boolean isDancing() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.allay.Allay.DATA_DANCING);
    }

    @Override
    public void setDancing(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.allay.Allay.DATA_DANCING, value);
    }
}
