package dev.by1337.entityrender.core;

class Generated_monster_ZombieVillager extends Generated_monster_Zombie implements dev.by1337.entityrender.api.entity.monster.VirtualZombieVillager {
    Generated_monster_ZombieVillager(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isConverting() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.ZombieVillager.DATA_CONVERTING_ID);
    }

    @Override
    public void setConverting(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.ZombieVillager.DATA_CONVERTING_ID, value);
    }

    @Override
    public dev.by1337.entityrender.api.VillagerAppearance getVillagerData() {
        return ValueConversions.villager(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.ZombieVillager.DATA_VILLAGER_DATA));
    }

    @Override
    public void setVillagerData(dev.by1337.entityrender.api.VillagerAppearance value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.ZombieVillager.DATA_VILLAGER_DATA, ValueConversions.villager(value));
    }
}
