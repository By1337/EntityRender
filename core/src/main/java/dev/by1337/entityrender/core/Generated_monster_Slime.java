package dev.by1337.entityrender.core;

class Generated_monster_Slime extends Generated_Mob implements dev.by1337.entityrender.api.entity.monster.VirtualSlime {
    Generated_monster_Slime(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public int getSize() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Slime.ID_SIZE);
    }

    @Override
    public void setSize(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Slime.ID_SIZE, value);
    }
}
