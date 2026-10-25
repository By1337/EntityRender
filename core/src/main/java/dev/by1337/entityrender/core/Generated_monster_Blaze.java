package dev.by1337.entityrender.core;

class Generated_monster_Blaze extends Generated_monster_Monster implements dev.by1337.entityrender.api.entity.monster.VirtualBlaze {
    Generated_monster_Blaze(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public byte getBlazeFlags() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Blaze.DATA_FLAGS_ID);
    }

    @Override
    public void setBlazeFlags(byte value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Blaze.DATA_FLAGS_ID, value);
    }
}
