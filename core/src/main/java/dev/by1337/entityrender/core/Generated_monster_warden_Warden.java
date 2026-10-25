package dev.by1337.entityrender.core;

class Generated_monster_warden_Warden extends Generated_monster_Monster implements dev.by1337.entityrender.api.entity.monster.warden.VirtualWarden {
    Generated_monster_warden_Warden(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public int getClientAngerLevel() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.warden.Warden.CLIENT_ANGER_LEVEL);
    }

    @Override
    public void setClientAngerLevel(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.warden.Warden.CLIENT_ANGER_LEVEL, value);
    }
}
