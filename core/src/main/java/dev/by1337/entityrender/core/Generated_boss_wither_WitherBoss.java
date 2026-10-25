package dev.by1337.entityrender.core;

class Generated_boss_wither_WitherBoss extends Generated_monster_Monster implements dev.by1337.entityrender.api.entity.boss.wither.VirtualWitherBoss {
    Generated_boss_wither_WitherBoss(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public int getInv() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.boss.wither.WitherBoss.DATA_ID_INV);
    }

    @Override
    public void setInv(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.boss.wither.WitherBoss.DATA_ID_INV, value);
    }

    @Override
    public int getTargetA() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.boss.wither.WitherBoss.DATA_TARGET_A);
    }

    @Override
    public void setTargetA(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.boss.wither.WitherBoss.DATA_TARGET_A, value);
    }

    @Override
    public int getTargetB() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.boss.wither.WitherBoss.DATA_TARGET_B);
    }

    @Override
    public void setTargetB(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.boss.wither.WitherBoss.DATA_TARGET_B, value);
    }

    @Override
    public int getTargetC() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.boss.wither.WitherBoss.DATA_TARGET_C);
    }

    @Override
    public void setTargetC(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.boss.wither.WitherBoss.DATA_TARGET_C, value);
    }
}
