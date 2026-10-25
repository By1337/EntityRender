package dev.by1337.entityrender.core;

class Generated_Avatar extends Generated_LivingEntity implements dev.by1337.entityrender.api.entity.VirtualAvatar {
    Generated_Avatar(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public byte getPlayerMainHand() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.Avatar.DATA_PLAYER_MAIN_HAND);
    }

    @Override
    public void setPlayerMainHand(byte value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.Avatar.DATA_PLAYER_MAIN_HAND, value);
    }

    @Override
    public byte getPlayerModeCustomisation() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.Avatar.DATA_PLAYER_MODE_CUSTOMISATION);
    }

    @Override
    public void setPlayerModeCustomisation(byte value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.Avatar.DATA_PLAYER_MODE_CUSTOMISATION, value);
    }
}
