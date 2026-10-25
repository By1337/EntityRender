package dev.by1337.entityrender.core;

class Generated_animal_nautilus_AbstractNautilus extends Generated_TamableAnimal implements dev.by1337.entityrender.api.entity.animal.nautilus.VirtualAbstractNautilus {
    Generated_animal_nautilus_AbstractNautilus(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isDash() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.nautilus.AbstractNautilus.DASH);
    }

    @Override
    public void setDash(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.nautilus.AbstractNautilus.DASH, value);
    }
}
