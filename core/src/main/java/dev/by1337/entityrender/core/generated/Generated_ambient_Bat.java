package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_ambient_Bat extends Generated_ambient_AmbientCreature implements dev.by1337.entityrender.api.entity.ambient.VirtualBat {
    Generated_ambient_Bat(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public byte getBatFlags() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.ambient.Bat.DATA_ID_FLAGS);
    }

    @Override
    public void setBatFlags(byte value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.ambient.Bat.DATA_ID_FLAGS, value);
    }

    @Override
    public boolean isHanging() {
        return flag(dev.by1337.entityrender.core.metadata.GeneratedMetadata.ambient.Bat.DATA_ID_FLAGS, 1);
    }

    @Override
    public void setHanging(boolean value) {
        flag(dev.by1337.entityrender.core.metadata.GeneratedMetadata.ambient.Bat.DATA_ID_FLAGS, 1, value);
    }
}
