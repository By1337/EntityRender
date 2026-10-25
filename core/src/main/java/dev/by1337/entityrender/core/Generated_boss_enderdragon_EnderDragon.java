package dev.by1337.entityrender.core;

class Generated_boss_enderdragon_EnderDragon extends Generated_Mob implements dev.by1337.entityrender.api.entity.boss.enderdragon.VirtualEnderDragon {
    Generated_boss_enderdragon_EnderDragon(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public int getPhase() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.boss.enderdragon.EnderDragon.DATA_PHASE);
    }

    @Override
    public void setPhase(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.boss.enderdragon.EnderDragon.DATA_PHASE, value);
    }
}
