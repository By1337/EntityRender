package dev.by1337.entityrender.core;

class Generated_animal_AbstractFish extends Generated_animal_WaterAnimal implements dev.by1337.entityrender.api.entity.animal.VirtualAbstractFish {
    Generated_animal_AbstractFish(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isFromBucket() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.AbstractFish.FROM_BUCKET);
    }

    @Override
    public void setFromBucket(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.AbstractFish.FROM_BUCKET, value);
    }
}
