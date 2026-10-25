package dev.by1337.entityrender.core;

class Generated_npc_AbstractVillager extends Generated_AgeableMob implements dev.by1337.entityrender.api.entity.npc.VirtualAbstractVillager {
    Generated_npc_AbstractVillager(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public int getUnhappyCounter() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.npc.AbstractVillager.DATA_UNHAPPY_COUNTER);
    }

    @Override
    public void setUnhappyCounter(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.npc.AbstractVillager.DATA_UNHAPPY_COUNTER, value);
    }
}
