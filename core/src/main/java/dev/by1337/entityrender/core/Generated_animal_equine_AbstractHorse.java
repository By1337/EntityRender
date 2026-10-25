package dev.by1337.entityrender.core;

class Generated_animal_equine_AbstractHorse extends Generated_animal_Animal implements dev.by1337.entityrender.api.entity.animal.equine.VirtualAbstractHorse {
    Generated_animal_equine_AbstractHorse(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public byte getAbstractHorseFlags() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.equine.AbstractHorse.DATA_ID_FLAGS);
    }

    @Override
    public void setAbstractHorseFlags(byte value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.equine.AbstractHorse.DATA_ID_FLAGS, value);
    }
}
