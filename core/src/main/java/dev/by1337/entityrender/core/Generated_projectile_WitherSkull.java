package dev.by1337.entityrender.core;

class Generated_projectile_WitherSkull extends Generated_projectile_AbstractHurtingProjectile implements dev.by1337.entityrender.api.entity.projectile.VirtualWitherSkull {
    Generated_projectile_WitherSkull(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isDangerous() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.projectile.WitherSkull.DATA_DANGEROUS);
    }

    @Override
    public void setDangerous(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.projectile.WitherSkull.DATA_DANGEROUS, value);
    }
}
