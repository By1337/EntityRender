package dev.by1337.entityrender.api.entity;

/** Generated from net.minecraft.world.entity.Avatar. Values use Bukkit types; packed flag fields remain explicit. */
public interface VirtualAvatar extends dev.by1337.entityrender.api.entity.VirtualLivingEntity {
    /** NMS DATA_PLAYER_MAIN_HAND; BYTE. */
    byte getPlayerMainHand();

    void setPlayerMainHand(byte value);

    /** NMS DATA_PLAYER_MODE_CUSTOMISATION; BYTE. */
    byte getPlayerModeCustomisation();

    void setPlayerModeCustomisation(byte value);
}
