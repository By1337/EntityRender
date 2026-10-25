package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_monster_Vex extends Generated_monster_Monster implements dev.by1337.entityrender.api.entity.monster.VirtualVex {
    Generated_monster_Vex(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public byte getVexFlags() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Vex.DATA_FLAGS_ID);
    }

    @Override
    public void setVexFlags(byte value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Vex.DATA_FLAGS_ID, value);
    }
}
