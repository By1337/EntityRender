package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_monster_zombie_Zombie extends Generated_monster_Monster implements dev.by1337.entityrender.api.entity.monster.zombie.VirtualZombie {
    Generated_monster_zombie_Zombie(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isBaby() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.zombie.Zombie.DATA_BABY_ID);
    }

    @Override
    public void setBaby(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.zombie.Zombie.DATA_BABY_ID, value);
    }

    @Override
    public boolean isDrownedConversion() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.zombie.Zombie.DATA_DROWNED_CONVERSION_ID);
    }

    @Override
    public void setDrownedConversion(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.zombie.Zombie.DATA_DROWNED_CONVERSION_ID, value);
    }

    @Override
    public int getSpecialType() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.zombie.Zombie.DATA_SPECIAL_TYPE_ID);
    }

    @Override
    public void setSpecialType(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.zombie.Zombie.DATA_SPECIAL_TYPE_ID, value);
    }
}
