package dev.by1337.entityrender.core;

class Generated_monster_piglin_AbstractPiglin extends Generated_monster_Monster implements dev.by1337.entityrender.api.entity.monster.piglin.VirtualAbstractPiglin {
    Generated_monster_piglin_AbstractPiglin(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isImmuneToZombification() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.piglin.AbstractPiglin.DATA_IMMUNE_TO_ZOMBIFICATION);
    }

    @Override
    public void setImmuneToZombification(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.piglin.AbstractPiglin.DATA_IMMUNE_TO_ZOMBIFICATION, value);
    }
}
