package dev.by1337.entityrender.core;

class Generated_animal_happyghast_HappyGhast extends Generated_animal_Animal implements dev.by1337.entityrender.api.entity.animal.happyghast.VirtualHappyGhast {
    Generated_animal_happyghast_HappyGhast(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isLeashHolder() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.happyghast.HappyGhast.IS_LEASH_HOLDER);
    }

    @Override
    public void setLeashHolder(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.happyghast.HappyGhast.IS_LEASH_HOLDER, value);
    }

    @Override
    public boolean isStaysStill() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.happyghast.HappyGhast.STAYS_STILL);
    }

    @Override
    public void setStaysStill(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.happyghast.HappyGhast.STAYS_STILL, value);
    }
}
