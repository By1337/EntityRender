package dev.by1337.entityrender.core;

class Generated_player_Player extends Generated_LivingEntity implements dev.by1337.entityrender.api.entity.player.VirtualPlayer {
    Generated_player_Player(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public float getPlayerAbsorption() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.player.Player.DATA_PLAYER_ABSORPTION_ID);
    }

    @Override
    public void setPlayerAbsorption(float value) {
        finite(value);
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.player.Player.DATA_PLAYER_ABSORPTION_ID, value);
    }

    @Override
    public byte getPlayerMainHand() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.player.Player.DATA_PLAYER_MAIN_HAND);
    }

    @Override
    public void setPlayerMainHand(byte value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.player.Player.DATA_PLAYER_MAIN_HAND, value);
    }

    @Override
    public byte getPlayerModeCustomisation() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.player.Player.DATA_PLAYER_MODE_CUSTOMISATION);
    }

    @Override
    public void setPlayerModeCustomisation(byte value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.player.Player.DATA_PLAYER_MODE_CUSTOMISATION, value);
    }

    @Override
    public int getScore() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.player.Player.DATA_SCORE_ID);
    }

    @Override
    public void setScore(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.player.Player.DATA_SCORE_ID, value);
    }

    @Override
    public String getShoulderLeftSnbt() {
        return ValueConversions.compound(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.player.Player.DATA_SHOULDER_LEFT));
    }

    @Override
    public void setShoulderLeftSnbt(String value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.player.Player.DATA_SHOULDER_LEFT, ValueConversions.compound(value));
    }

    @Override
    public String getShoulderRightSnbt() {
        return ValueConversions.compound(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.player.Player.DATA_SHOULDER_RIGHT));
    }

    @Override
    public void setShoulderRightSnbt(String value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.player.Player.DATA_SHOULDER_RIGHT, ValueConversions.compound(value));
    }
}
