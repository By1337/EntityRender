package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_animal_golem_IronGolem extends Generated_animal_golem_AbstractGolem implements dev.by1337.entityrender.api.entity.animal.golem.VirtualIronGolem {
    Generated_animal_golem_IronGolem(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public byte getIronGolemFlags() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.golem.IronGolem.DATA_FLAGS_ID);
    }

    @Override
    public void setIronGolemFlags(byte value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.golem.IronGolem.DATA_FLAGS_ID, value);
    }
}
