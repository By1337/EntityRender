package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_projectile_FishingHook extends Generated_projectile_Projectile implements dev.by1337.entityrender.api.entity.projectile.VirtualFishingHook {
    Generated_projectile_FishingHook(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isBiting() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.projectile.FishingHook.DATA_BITING);
    }

    @Override
    public void setBiting(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.projectile.FishingHook.DATA_BITING, value);
    }

    @Override
    public int getHookedEntity() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.projectile.FishingHook.DATA_HOOKED_ENTITY);
    }

    @Override
    public void setHookedEntity(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.projectile.FishingHook.DATA_HOOKED_ENTITY, value);
    }
}
