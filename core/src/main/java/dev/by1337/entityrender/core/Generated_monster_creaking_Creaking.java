package dev.by1337.entityrender.core;

class Generated_monster_creaking_Creaking extends Generated_monster_Monster implements dev.by1337.entityrender.api.entity.monster.creaking.VirtualCreaking {
    Generated_monster_creaking_Creaking(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isCanMove() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.creaking.Creaking.CAN_MOVE);
    }

    @Override
    public void setCanMove(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.creaking.Creaking.CAN_MOVE, value);
    }

    @Override
    public org.bukkit.util.BlockVector getHomePos() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.creaking.Creaking.HOME_POS).map(ValueConversions::position).orElse(null);
    }

    @Override
    public void setHomePos(org.bukkit.util.BlockVector value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.creaking.Creaking.HOME_POS, java.util.Optional.ofNullable(value).map(ValueConversions::position));
    }

    @Override
    public boolean isActive() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.creaking.Creaking.IS_ACTIVE);
    }

    @Override
    public void setActive(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.creaking.Creaking.IS_ACTIVE, value);
    }

    @Override
    public boolean isTearingDown() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.creaking.Creaking.IS_TEARING_DOWN);
    }

    @Override
    public void setTearingDown(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.creaking.Creaking.IS_TEARING_DOWN, value);
    }
}
