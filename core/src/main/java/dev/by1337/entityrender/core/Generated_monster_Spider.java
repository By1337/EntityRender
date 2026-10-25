package dev.by1337.entityrender.core;

class Generated_monster_Spider extends Generated_monster_Monster implements dev.by1337.entityrender.api.entity.monster.VirtualSpider {
    Generated_monster_Spider(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public byte getSpiderFlags() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Spider.DATA_FLAGS_ID);
    }

    @Override
    public void setSpiderFlags(byte value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Spider.DATA_FLAGS_ID, value);
    }
}
