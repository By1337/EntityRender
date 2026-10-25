package dev.by1337.entityrender.core;

class Generated_monster_cubemob_AbstractCubeMob extends Generated_AgeableMob implements dev.by1337.entityrender.api.entity.monster.cubemob.VirtualAbstractCubeMob {
    Generated_monster_cubemob_AbstractCubeMob(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public int getSize() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.cubemob.AbstractCubeMob.ID_SIZE);
    }

    @Override
    public void setSize(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.cubemob.AbstractCubeMob.ID_SIZE, value);
    }
}
