package dev.by1337.entityrender.core;

class Generated_Mob extends Generated_LivingEntity implements dev.by1337.entityrender.api.entity.VirtualMob {
    Generated_Mob(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public byte getMobFlags() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.Mob.DATA_MOB_FLAGS_ID);
    }

    @Override
    public void setMobFlags(byte value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.Mob.DATA_MOB_FLAGS_ID, value);
    }

    @Override
    public boolean isNoAi() {
        return flag(dev.by1337.entityrender.core.metadata.GeneratedMetadata.Mob.DATA_MOB_FLAGS_ID, 1);
    }

    @Override
    public void setNoAi(boolean value) {
        flag(dev.by1337.entityrender.core.metadata.GeneratedMetadata.Mob.DATA_MOB_FLAGS_ID, 1, value);
    }

    @Override
    public boolean isLeftHanded() {
        return flag(dev.by1337.entityrender.core.metadata.GeneratedMetadata.Mob.DATA_MOB_FLAGS_ID, 2);
    }

    @Override
    public void setLeftHanded(boolean value) {
        flag(dev.by1337.entityrender.core.metadata.GeneratedMetadata.Mob.DATA_MOB_FLAGS_ID, 2, value);
    }

    @Override
    public boolean isAggressive() {
        return flag(dev.by1337.entityrender.core.metadata.GeneratedMetadata.Mob.DATA_MOB_FLAGS_ID, 4);
    }

    @Override
    public void setAggressive(boolean value) {
        flag(dev.by1337.entityrender.core.metadata.GeneratedMetadata.Mob.DATA_MOB_FLAGS_ID, 4, value);
    }
}
