package dev.by1337.entityrender.api.entity.player;

/**
 * Generated from net.minecraft.world.entity.player.Player. Values use Bukkit types; packed flag fields remain explicit.
 */
public interface VirtualPlayer extends dev.by1337.entityrender.api.entity.VirtualAvatar {
    /** NMS DATA_PLAYER_ABSORPTION_ID; FLOAT. */
    float getPlayerAbsorption();

    void setPlayerAbsorption(float value);

    /** NMS DATA_SCORE_ID; INT. */
    int getScore();

    void setScore(int value);

    /** NMS DATA_SHOULDER_PARROT_LEFT; OPTIONAL_UNSIGNED_INT. */
    java.util.OptionalInt getShoulderParrotLeft();

    void setShoulderParrotLeft(java.util.OptionalInt value);

    /** NMS DATA_SHOULDER_PARROT_RIGHT; OPTIONAL_UNSIGNED_INT. */
    java.util.OptionalInt getShoulderParrotRight();

    void setShoulderParrotRight(java.util.OptionalInt value);
}
