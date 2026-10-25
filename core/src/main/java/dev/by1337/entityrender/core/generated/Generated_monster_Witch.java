package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_monster_Witch extends Generated_raid_Raider implements dev.by1337.entityrender.api.entity.monster.VirtualWitch {
    Generated_monster_Witch(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isUsingItem() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Witch.DATA_USING_ITEM);
    }

    @Override
    public void setUsingItem(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Witch.DATA_USING_ITEM, value);
    }
}
