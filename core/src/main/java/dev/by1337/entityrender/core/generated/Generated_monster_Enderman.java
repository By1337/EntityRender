package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_monster_Enderman extends Generated_monster_Monster implements dev.by1337.entityrender.api.entity.monster.VirtualEnderman {
    Generated_monster_Enderman(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public org.bukkit.block.data.BlockData getCarryState() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Enderman.DATA_CARRY_STATE).map(ValueConversions::block).orElse(null);
    }

    @Override
    public void setCarryState(org.bukkit.block.data.BlockData value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Enderman.DATA_CARRY_STATE, java.util.Optional.ofNullable(value).map(ValueConversions::block));
    }

    @Override
    public boolean isCreepy() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Enderman.DATA_CREEPY);
    }

    @Override
    public void setCreepy(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Enderman.DATA_CREEPY, value);
    }

    @Override
    public boolean isStaredAt() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Enderman.DATA_STARED_AT);
    }

    @Override
    public void setStaredAt(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Enderman.DATA_STARED_AT, value);
    }
}
