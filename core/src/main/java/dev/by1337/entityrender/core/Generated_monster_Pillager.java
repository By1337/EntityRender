package dev.by1337.entityrender.core;

class Generated_monster_Pillager extends Generated_monster_AbstractIllager implements dev.by1337.entityrender.api.entity.monster.VirtualPillager {
    Generated_monster_Pillager(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isChargingCrossbow() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Pillager.IS_CHARGING_CROSSBOW);
    }

    @Override
    public void setChargingCrossbow(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Pillager.IS_CHARGING_CROSSBOW, value);
    }
}
