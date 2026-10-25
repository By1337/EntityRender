package dev.by1337.entityrender.core;

class Generated_player_Player extends Generated_Avatar implements dev.by1337.entityrender.api.entity.player.VirtualPlayer {
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
    public int getScore() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.player.Player.DATA_SCORE_ID);
    }

    @Override
    public void setScore(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.player.Player.DATA_SCORE_ID, value);
    }

    @Override
    public java.util.OptionalInt getShoulderParrotLeft() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.player.Player.DATA_SHOULDER_PARROT_LEFT);
    }

    @Override
    public void setShoulderParrotLeft(java.util.OptionalInt value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.player.Player.DATA_SHOULDER_PARROT_LEFT, value);
    }

    @Override
    public java.util.OptionalInt getShoulderParrotRight() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.player.Player.DATA_SHOULDER_PARROT_RIGHT);
    }

    @Override
    public void setShoulderParrotRight(java.util.OptionalInt value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.player.Player.DATA_SHOULDER_PARROT_RIGHT, value);
    }
}
