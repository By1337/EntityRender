package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_LivingEntity extends BaseVirtualEntity implements dev.by1337.entityrender.api.entity.VirtualLivingEntity {
    Generated_LivingEntity(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public int getArrowCount() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.LivingEntity.DATA_ARROW_COUNT_ID);
    }

    @Override
    public void setArrowCount(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.LivingEntity.DATA_ARROW_COUNT_ID, value);
    }

    @Override
    public boolean isEffectAmbience() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.LivingEntity.DATA_EFFECT_AMBIENCE_ID);
    }

    @Override
    public void setEffectAmbience(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.LivingEntity.DATA_EFFECT_AMBIENCE_ID, value);
    }

    @Override
    public java.util.List<String> getEffectParticlesSnbt() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.LivingEntity.DATA_EFFECT_PARTICLES).stream().map(value -> ValueConversions.encode(net.minecraft.core.particles.ParticleTypes.CODEC, value, registries())).toList();
    }

    @Override
    public void setEffectParticlesSnbt(java.util.List<String> value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.LivingEntity.DATA_EFFECT_PARTICLES, value.stream().map(element -> ValueConversions.decode(net.minecraft.core.particles.ParticleTypes.CODEC, element, registries())).toList());
    }

    @Override
    public float getHealth() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.LivingEntity.DATA_HEALTH_ID);
    }

    @Override
    public void setHealth(float value) {
        finite(value);
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.LivingEntity.DATA_HEALTH_ID, value);
    }

    @Override
    public byte getLivingEntityFlags() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.LivingEntity.DATA_LIVING_ENTITY_FLAGS);
    }

    @Override
    public void setLivingEntityFlags(byte value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.LivingEntity.DATA_LIVING_ENTITY_FLAGS, value);
    }

    @Override
    public int getStingerCount() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.LivingEntity.DATA_STINGER_COUNT_ID);
    }

    @Override
    public void setStingerCount(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.LivingEntity.DATA_STINGER_COUNT_ID, value);
    }

    @Override
    public org.bukkit.util.BlockVector getSleepingPos() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.LivingEntity.SLEEPING_POS_ID).map(ValueConversions::position).orElse(null);
    }

    @Override
    public void setSleepingPos(org.bukkit.util.BlockVector value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.LivingEntity.SLEEPING_POS_ID, java.util.Optional.ofNullable(value).map(ValueConversions::position));
    }
}
