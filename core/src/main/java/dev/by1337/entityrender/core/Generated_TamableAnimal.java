package dev.by1337.entityrender.core;

class Generated_TamableAnimal extends Generated_animal_Animal implements dev.by1337.entityrender.api.entity.VirtualTamableAnimal {
    Generated_TamableAnimal(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public byte getTamableAnimalFlags() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.TamableAnimal.DATA_FLAGS_ID);
    }

    @Override
    public void setTamableAnimalFlags(byte value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.TamableAnimal.DATA_FLAGS_ID, value);
    }

    @Override
    public java.util.UUID getOwneruuid() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.TamableAnimal.DATA_OWNERUUID_ID).orElse(null);
    }

    @Override
    public void setOwneruuid(java.util.UUID value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.TamableAnimal.DATA_OWNERUUID_ID, java.util.Optional.ofNullable(value));
    }

    @Override
    public boolean isSitting() {
        return flag(dev.by1337.entityrender.core.metadata.GeneratedMetadata.TamableAnimal.DATA_FLAGS_ID, 1);
    }

    @Override
    public void setSitting(boolean value) {
        flag(dev.by1337.entityrender.core.metadata.GeneratedMetadata.TamableAnimal.DATA_FLAGS_ID, 1, value);
    }

    @Override
    public boolean isTamed() {
        return flag(dev.by1337.entityrender.core.metadata.GeneratedMetadata.TamableAnimal.DATA_FLAGS_ID, 4);
    }

    @Override
    public void setTamed(boolean value) {
        flag(dev.by1337.entityrender.core.metadata.GeneratedMetadata.TamableAnimal.DATA_FLAGS_ID, 4, value);
    }
}
