package dev.by1337.entityrender.core;

class Generated_animal_Bee extends Generated_animal_Animal implements dev.by1337.entityrender.api.entity.animal.VirtualBee {
    Generated_animal_Bee(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public byte getBeeFlags() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Bee.DATA_FLAGS_ID);
    }

    @Override
    public void setBeeFlags(byte value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Bee.DATA_FLAGS_ID, value);
    }

    @Override
    public int getRemainingAngerTime() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Bee.DATA_REMAINING_ANGER_TIME);
    }

    @Override
    public void setRemainingAngerTime(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Bee.DATA_REMAINING_ANGER_TIME, value);
    }
}
