package dev.by1337.entityrender.api.entity.monster;

/**
 * Generated from net.minecraft.world.entity.monster.Shulker. Values use Bukkit types; packed flag fields remain
 * explicit.
 */
public interface VirtualShulker extends dev.by1337.entityrender.api.entity.animal.VirtualAbstractGolem {
    /** NMS DATA_ATTACH_FACE_ID; DIRECTION. */
    org.bukkit.block.BlockFace getAttachFace();

    void setAttachFace(org.bukkit.block.BlockFace value);

    /** NMS DATA_COLOR_ID; BYTE. */
    org.bukkit.DyeColor getColor();

    void setColor(org.bukkit.DyeColor value);

    /** NMS DATA_PEEK_ID; BYTE. */
    byte getPeek();

    void setPeek(byte value);
}
