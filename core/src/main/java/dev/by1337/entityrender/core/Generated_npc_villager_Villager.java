package dev.by1337.entityrender.core;

class Generated_npc_villager_Villager extends Generated_npc_villager_AbstractVillager implements dev.by1337.entityrender.api.entity.npc.villager.VirtualVillager {
    Generated_npc_villager_Villager(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public dev.by1337.entityrender.api.VillagerAppearance getVillagerData() {
        return ValueConversions.villager(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.npc.villager.Villager.DATA_VILLAGER_DATA));
    }

    @Override
    public void setVillagerData(dev.by1337.entityrender.api.VillagerAppearance value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.npc.villager.Villager.DATA_VILLAGER_DATA, ValueConversions.villager(value));
    }

    @Override
    public boolean isVillagerDataFinalized() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.npc.villager.Villager.DATA_VILLAGER_DATA_FINALIZED);
    }

    @Override
    public void setVillagerDataFinalized(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.npc.villager.Villager.DATA_VILLAGER_DATA_FINALIZED, value);
    }
}
