package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_projectile_FireworkRocketEntity extends Generated_projectile_Projectile implements dev.by1337.entityrender.api.entity.projectile.VirtualFireworkRocketEntity {
    Generated_projectile_FireworkRocketEntity(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public java.util.OptionalInt getAttachedToTarget() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.projectile.FireworkRocketEntity.DATA_ATTACHED_TO_TARGET);
    }

    @Override
    public void setAttachedToTarget(java.util.OptionalInt value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.projectile.FireworkRocketEntity.DATA_ATTACHED_TO_TARGET, value);
    }

    @Override
    public org.bukkit.inventory.ItemStack getFireworksItem() {
        return ValueConversions.item(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.projectile.FireworkRocketEntity.DATA_ID_FIREWORKS_ITEM));
    }

    @Override
    public void setFireworksItem(org.bukkit.inventory.ItemStack value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.projectile.FireworkRocketEntity.DATA_ID_FIREWORKS_ITEM, ValueConversions.item(value));
    }

    @Override
    public boolean isShotAtAngle() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.projectile.FireworkRocketEntity.DATA_SHOT_AT_ANGLE);
    }

    @Override
    public void setShotAtAngle(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.projectile.FireworkRocketEntity.DATA_SHOT_AT_ANGLE, value);
    }
}
