package dev.by1337.entityrender.core;

class Generated_animal_Parrot extends Generated_animal_ShoulderRidingEntity implements dev.by1337.entityrender.api.entity.animal.VirtualParrot {
    Generated_animal_Parrot(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public int getVariant() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Parrot.DATA_VARIANT_ID);
    }

    @Override
    public void setVariant(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Parrot.DATA_VARIANT_ID, value);
    }
}
