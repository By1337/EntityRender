package dev.by1337.entityrender.core;

class Generated_raid_Raider extends Generated_monster_PatrollingMonster implements dev.by1337.entityrender.api.entity.raid.VirtualRaider {
    Generated_raid_Raider(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isCelebrating() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.raid.Raider.IS_CELEBRATING);
    }

    @Override
    public void setCelebrating(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.raid.Raider.IS_CELEBRATING, value);
    }
}
