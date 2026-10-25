package dev.by1337.entityrender.core;

class Generated_AgeableMob extends Generated_PathfinderMob implements dev.by1337.entityrender.api.entity.VirtualAgeableMob {
    Generated_AgeableMob(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isBaby() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.AgeableMob.DATA_BABY_ID);
    }

    @Override
    public void setBaby(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.AgeableMob.DATA_BABY_ID, value);
    }
}
