package dev.by1337.entityrender.core;

class Generated_animal_feline_Ocelot extends Generated_animal_Animal implements dev.by1337.entityrender.api.entity.animal.feline.VirtualOcelot {
    Generated_animal_feline_Ocelot(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isTrusting() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.feline.Ocelot.DATA_TRUSTING);
    }

    @Override
    public void setTrusting(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.feline.Ocelot.DATA_TRUSTING, value);
    }
}
