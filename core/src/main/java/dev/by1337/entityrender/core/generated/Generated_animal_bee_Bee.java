package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_animal_bee_Bee extends Generated_animal_Animal implements dev.by1337.entityrender.api.entity.animal.bee.VirtualBee {
    Generated_animal_bee_Bee(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public long getAngerEndTime() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.bee.Bee.DATA_ANGER_END_TIME);
    }

    @Override
    public void setAngerEndTime(long value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.bee.Bee.DATA_ANGER_END_TIME, value);
    }

    @Override
    public byte getBeeFlags() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.bee.Bee.DATA_FLAGS_ID);
    }

    @Override
    public void setBeeFlags(byte value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.bee.Bee.DATA_FLAGS_ID, value);
    }
}
