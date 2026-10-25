package dev.by1337.entityrender.core;

class Generated_monster_Strider extends Generated_animal_Animal implements dev.by1337.entityrender.api.entity.monster.VirtualStrider {
    Generated_monster_Strider(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public int getBoostTime() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Strider.DATA_BOOST_TIME);
    }

    @Override
    public void setBoostTime(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Strider.DATA_BOOST_TIME, value);
    }

    @Override
    public boolean isSaddle() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Strider.DATA_SADDLE_ID);
    }

    @Override
    public void setSaddle(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Strider.DATA_SADDLE_ID, value);
    }

    @Override
    public boolean isSuffocating() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Strider.DATA_SUFFOCATING);
    }

    @Override
    public void setSuffocating(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Strider.DATA_SUFFOCATING, value);
    }
}
