package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_monster_skeleton_Bogged extends Generated_monster_skeleton_AbstractSkeleton implements dev.by1337.entityrender.api.entity.monster.skeleton.VirtualBogged {
    Generated_monster_skeleton_Bogged(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isSheared() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.skeleton.Bogged.DATA_SHEARED);
    }

    @Override
    public void setSheared(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.skeleton.Bogged.DATA_SHEARED, value);
    }
}
