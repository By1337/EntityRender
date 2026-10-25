package dev.by1337.entityrender.core;

class Generated_animal_MushroomCow extends Generated_animal_Cow implements dev.by1337.entityrender.api.entity.animal.VirtualMushroomCow {
    Generated_animal_MushroomCow(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public String getVariant() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.MushroomCow.DATA_TYPE);
    }

    @Override
    public void setVariant(String value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.MushroomCow.DATA_TYPE, value);
    }
}
