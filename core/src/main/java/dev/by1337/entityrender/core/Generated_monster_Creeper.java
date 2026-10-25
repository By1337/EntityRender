package dev.by1337.entityrender.core;

class Generated_monster_Creeper extends Generated_monster_Monster implements dev.by1337.entityrender.api.entity.monster.VirtualCreeper {
    Generated_monster_Creeper(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isIgnited() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Creeper.DATA_IS_IGNITED);
    }

    @Override
    public void setIgnited(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Creeper.DATA_IS_IGNITED, value);
    }

    @Override
    public boolean isPowered() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Creeper.DATA_IS_POWERED);
    }

    @Override
    public void setPowered(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Creeper.DATA_IS_POWERED, value);
    }

    @Override
    public int getSwellDir() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Creeper.DATA_SWELL_DIR);
    }

    @Override
    public void setSwellDir(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Creeper.DATA_SWELL_DIR, value);
    }
}
