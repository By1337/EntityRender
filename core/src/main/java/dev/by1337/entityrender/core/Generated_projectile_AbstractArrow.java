package dev.by1337.entityrender.core;

class Generated_projectile_AbstractArrow extends Generated_projectile_Projectile implements dev.by1337.entityrender.api.entity.projectile.VirtualAbstractArrow {
    Generated_projectile_AbstractArrow(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public byte getAbstractArrowFlags() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.projectile.AbstractArrow.ID_FLAGS);
    }

    @Override
    public void setAbstractArrowFlags(byte value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.projectile.AbstractArrow.ID_FLAGS, value);
    }

    @Override
    public boolean isInGround() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.projectile.AbstractArrow.IN_GROUND);
    }

    @Override
    public void setInGround(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.projectile.AbstractArrow.IN_GROUND, value);
    }

    @Override
    public byte getPierceLevel() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.projectile.AbstractArrow.PIERCE_LEVEL);
    }

    @Override
    public void setPierceLevel(byte value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.projectile.AbstractArrow.PIERCE_LEVEL, value);
    }
}
