package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_monster_zombie_ZombieVillager extends Generated_monster_zombie_Zombie implements dev.by1337.entityrender.api.entity.monster.zombie.VirtualZombieVillager {
    Generated_monster_zombie_ZombieVillager(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isConverting() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.zombie.ZombieVillager.DATA_CONVERTING_ID);
    }

    @Override
    public void setConverting(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.zombie.ZombieVillager.DATA_CONVERTING_ID, value);
    }

    @Override
    public dev.by1337.entityrender.api.VillagerAppearance getVillagerData() {
        return ValueConversions.villager(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.zombie.ZombieVillager.DATA_VILLAGER_DATA));
    }

    @Override
    public void setVillagerData(dev.by1337.entityrender.api.VillagerAppearance value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.zombie.ZombieVillager.DATA_VILLAGER_DATA, ValueConversions.villager(value));
    }

    @Override
    public boolean isVillagerDataFinalized() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.zombie.ZombieVillager.DATA_VILLAGER_DATA_FINALIZED);
    }

    @Override
    public void setVillagerDataFinalized(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.zombie.ZombieVillager.DATA_VILLAGER_DATA_FINALIZED, value);
    }
}
