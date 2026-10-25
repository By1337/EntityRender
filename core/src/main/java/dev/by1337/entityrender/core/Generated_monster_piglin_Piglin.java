package dev.by1337.entityrender.core;

class Generated_monster_piglin_Piglin extends Generated_monster_piglin_AbstractPiglin implements dev.by1337.entityrender.api.entity.monster.piglin.VirtualPiglin {
    Generated_monster_piglin_Piglin(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isBaby() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.piglin.Piglin.DATA_BABY_ID);
    }

    @Override
    public void setBaby(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.piglin.Piglin.DATA_BABY_ID, value);
    }

    @Override
    public boolean isChargingCrossbow() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.piglin.Piglin.DATA_IS_CHARGING_CROSSBOW);
    }

    @Override
    public void setChargingCrossbow(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.piglin.Piglin.DATA_IS_CHARGING_CROSSBOW, value);
    }

    @Override
    public boolean isDancing() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.piglin.Piglin.DATA_IS_DANCING);
    }

    @Override
    public void setDancing(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.piglin.Piglin.DATA_IS_DANCING, value);
    }
}
