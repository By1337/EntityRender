package dev.by1337.entityrender.core;

class Generated_monster_Guardian extends Generated_monster_Monster implements dev.by1337.entityrender.api.entity.monster.VirtualGuardian {
    Generated_monster_Guardian(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public int getAttackTarget() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Guardian.DATA_ID_ATTACK_TARGET);
    }

    @Override
    public void setAttackTarget(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Guardian.DATA_ID_ATTACK_TARGET, value);
    }

    @Override
    public boolean isMoving() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Guardian.DATA_ID_MOVING);
    }

    @Override
    public void setMoving(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Guardian.DATA_ID_MOVING, value);
    }
}
