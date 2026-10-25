package dev.by1337.entityrender.core;

class Generated_animal_equine_Llama extends Generated_animal_equine_AbstractChestedHorse implements dev.by1337.entityrender.api.entity.animal.equine.VirtualLlama {
    Generated_animal_equine_Llama(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public int getStrength() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.equine.Llama.DATA_STRENGTH_ID);
    }

    @Override
    public void setStrength(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.equine.Llama.DATA_STRENGTH_ID, value);
    }

    @Override
    public int getVariant() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.equine.Llama.DATA_VARIANT_ID);
    }

    @Override
    public void setVariant(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.equine.Llama.DATA_VARIANT_ID, value);
    }
}
