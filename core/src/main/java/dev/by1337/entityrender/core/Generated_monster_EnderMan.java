package dev.by1337.entityrender.core;

class Generated_monster_EnderMan extends Generated_monster_Monster implements dev.by1337.entityrender.api.entity.monster.VirtualEnderMan {
    Generated_monster_EnderMan(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public org.bukkit.block.data.BlockData getCarryState() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.EnderMan.DATA_CARRY_STATE).map(ValueConversions::block).orElse(null);
    }

    @Override
    public void setCarryState(org.bukkit.block.data.BlockData value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.EnderMan.DATA_CARRY_STATE, java.util.Optional.ofNullable(value).map(ValueConversions::block));
    }

    @Override
    public boolean isCreepy() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.EnderMan.DATA_CREEPY);
    }

    @Override
    public void setCreepy(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.EnderMan.DATA_CREEPY, value);
    }

    @Override
    public boolean isStaredAt() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.EnderMan.DATA_STARED_AT);
    }

    @Override
    public void setStaredAt(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.EnderMan.DATA_STARED_AT, value);
    }
}
