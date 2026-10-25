package dev.by1337.entityrender.core;

class Generated_projectile_Arrow extends Generated_projectile_AbstractArrow implements dev.by1337.entityrender.api.entity.projectile.VirtualArrow {
    Generated_projectile_Arrow(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public int getEffectColor() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.projectile.Arrow.ID_EFFECT_COLOR);
    }

    @Override
    public void setEffectColor(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.projectile.Arrow.ID_EFFECT_COLOR, value);
    }
}
