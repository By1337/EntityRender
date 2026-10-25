package dev.by1337.entityrender.core;

class Generated_monster_Ghast extends Generated_Mob implements dev.by1337.entityrender.api.entity.monster.VirtualGhast {
    Generated_monster_Ghast(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isCharging() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Ghast.DATA_IS_CHARGING);
    }

    @Override
    public void setCharging(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Ghast.DATA_IS_CHARGING, value);
    }
}
