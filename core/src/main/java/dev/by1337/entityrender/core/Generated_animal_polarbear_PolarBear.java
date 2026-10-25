package dev.by1337.entityrender.core;

class Generated_animal_polarbear_PolarBear extends Generated_animal_Animal implements dev.by1337.entityrender.api.entity.animal.polarbear.VirtualPolarBear {
    Generated_animal_polarbear_PolarBear(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isStanding() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.polarbear.PolarBear.DATA_STANDING_ID);
    }

    @Override
    public void setStanding(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.polarbear.PolarBear.DATA_STANDING_ID, value);
    }
}
