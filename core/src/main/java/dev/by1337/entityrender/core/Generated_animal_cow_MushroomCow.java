package dev.by1337.entityrender.core;

class Generated_animal_cow_MushroomCow extends Generated_animal_cow_AbstractCow implements dev.by1337.entityrender.api.entity.animal.cow.VirtualMushroomCow {
    Generated_animal_cow_MushroomCow(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public int getVariant() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.cow.MushroomCow.DATA_TYPE);
    }

    @Override
    public void setVariant(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.cow.MushroomCow.DATA_TYPE, value);
    }
}
