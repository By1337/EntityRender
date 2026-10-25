package dev.by1337.entityrender.core;

class Generated_animal_Fox extends Generated_animal_Animal implements dev.by1337.entityrender.api.entity.animal.VirtualFox {
    Generated_animal_Fox(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public byte getFoxFlags() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Fox.DATA_FLAGS_ID);
    }

    @Override
    public void setFoxFlags(byte value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Fox.DATA_FLAGS_ID, value);
    }

    @Override
    public java.util.UUID getTrustedId0() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Fox.DATA_TRUSTED_ID_0).orElse(null);
    }

    @Override
    public void setTrustedId0(java.util.UUID value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Fox.DATA_TRUSTED_ID_0, java.util.Optional.ofNullable(value));
    }

    @Override
    public java.util.UUID getTrustedId1() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Fox.DATA_TRUSTED_ID_1).orElse(null);
    }

    @Override
    public void setTrustedId1(java.util.UUID value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Fox.DATA_TRUSTED_ID_1, java.util.Optional.ofNullable(value));
    }

    @Override
    public int getVariant() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Fox.DATA_TYPE_ID);
    }

    @Override
    public void setVariant(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Fox.DATA_TYPE_ID, value);
    }
}
