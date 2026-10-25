package dev.by1337.entityrender.api.entity.monster.zombie;

/**
 * Generated from net.minecraft.world.entity.monster.zombie.ZombieVillager. Values use Bukkit types; packed flag fields
 * remain explicit.
 */
public interface VirtualZombieVillager extends dev.by1337.entityrender.api.entity.monster.zombie.VirtualZombie {
    /** NMS DATA_CONVERTING_ID; BOOLEAN. */
    boolean isConverting();

    void setConverting(boolean value);

    /** NMS DATA_VILLAGER_DATA; VILLAGER_DATA. */
    dev.by1337.entityrender.api.VillagerAppearance getVillagerData();

    void setVillagerData(dev.by1337.entityrender.api.VillagerAppearance value);
}
