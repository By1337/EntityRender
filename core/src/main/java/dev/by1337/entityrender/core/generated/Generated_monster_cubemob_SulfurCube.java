package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_monster_cubemob_SulfurCube extends Generated_monster_cubemob_AbstractCubeMob implements dev.by1337.entityrender.api.entity.monster.cubemob.VirtualSulfurCube {
    Generated_monster_cubemob_SulfurCube(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isFromBucket() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.cubemob.SulfurCube.FROM_BUCKET);
    }

    @Override
    public void setFromBucket(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.cubemob.SulfurCube.FROM_BUCKET, value);
    }

    @Override
    public int getMaxFuse() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.cubemob.SulfurCube.MAX_FUSE);
    }

    @Override
    public void setMaxFuse(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.cubemob.SulfurCube.MAX_FUSE, value);
    }
}
