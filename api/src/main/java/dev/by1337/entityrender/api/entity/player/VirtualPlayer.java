package dev.by1337.entityrender.api.entity.player;

/**
 * Generated from net.minecraft.world.entity.player.Player. Values use Bukkit types; packed flag fields remain explicit.
 */
public interface VirtualPlayer extends dev.by1337.entityrender.api.entity.VirtualLivingEntity {
    /** NMS DATA_PLAYER_ABSORPTION_ID; FLOAT. */
    float getPlayerAbsorption();

    void setPlayerAbsorption(float value);

    /** NMS DATA_PLAYER_MAIN_HAND; BYTE. */
    byte getPlayerMainHand();

    void setPlayerMainHand(byte value);

    /** NMS DATA_PLAYER_MODE_CUSTOMISATION; BYTE. */
    byte getPlayerModeCustomisation();

    void setPlayerModeCustomisation(byte value);

    /** NMS DATA_SCORE_ID; INT. */
    int getScore();

    void setScore(int value);

    /** NMS DATA_SHOULDER_LEFT; COMPOUND_TAG. */
    String getShoulderLeftSnbt();

    void setShoulderLeftSnbt(String value);

    /** NMS DATA_SHOULDER_RIGHT; COMPOUND_TAG. */
    String getShoulderRightSnbt();

    void setShoulderRightSnbt(String value);
}
