package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_npc_villager_AbstractVillager extends Generated_AgeableMob implements dev.by1337.entityrender.api.entity.npc.villager.VirtualAbstractVillager {
    Generated_npc_villager_AbstractVillager(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public int getUnhappyCounter() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.npc.villager.AbstractVillager.DATA_UNHAPPY_COUNTER);
    }

    @Override
    public void setUnhappyCounter(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.npc.villager.AbstractVillager.DATA_UNHAPPY_COUNTER, value);
    }
}
