package dev.by1337.entityrender.api.entity.npc;

/**
 * Generated from net.minecraft.world.entity.npc.Villager. Values use Bukkit types; packed flag fields remain explicit.
 */
public interface VirtualVillager extends dev.by1337.entityrender.api.entity.npc.VirtualAbstractVillager {
    /** NMS DATA_VILLAGER_DATA; VILLAGER_DATA. */
    dev.by1337.entityrender.api.VillagerAppearance getVillagerData();

    void setVillagerData(dev.by1337.entityrender.api.VillagerAppearance value);
}
