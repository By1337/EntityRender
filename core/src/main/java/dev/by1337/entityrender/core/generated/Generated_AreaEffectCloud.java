package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_AreaEffectCloud extends BaseVirtualEntity implements dev.by1337.entityrender.api.entity.VirtualAreaEffectCloud {
    Generated_AreaEffectCloud(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public String getParticleSnbt() {
        return ValueConversions.encode(net.minecraft.core.particles.ParticleTypes.CODEC, get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.AreaEffectCloud.DATA_PARTICLE), registries());
    }

    @Override
    public void setParticleSnbt(String value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.AreaEffectCloud.DATA_PARTICLE, ValueConversions.decode(net.minecraft.core.particles.ParticleTypes.CODEC, value, registries()));
    }

    @Override
    public float getRadius() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.AreaEffectCloud.DATA_RADIUS);
    }

    @Override
    public void setRadius(float value) {
        finite(value);
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.AreaEffectCloud.DATA_RADIUS, value);
    }

    @Override
    public boolean isWaiting() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.AreaEffectCloud.DATA_WAITING);
    }

    @Override
    public void setWaiting(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.AreaEffectCloud.DATA_WAITING, value);
    }
}
