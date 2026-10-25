package dev.by1337.entityrender.api.entity;

/** Generated from net.minecraft.world.entity.Avatar. Values use Bukkit types; packed flag fields remain explicit. */
public interface VirtualAvatar extends dev.by1337.entityrender.api.entity.VirtualLivingEntity {
    /** NMS DATA_PLAYER_MAIN_HAND; HUMANOID_ARM. */
    org.bukkit.inventory.MainHand getPlayerMainHand();

    void setPlayerMainHand(org.bukkit.inventory.MainHand value);

    /** NMS DATA_PLAYER_MODE_CUSTOMISATION; BYTE. */
    byte getPlayerModeCustomisation();

    void setPlayerModeCustomisation(byte value);
}
