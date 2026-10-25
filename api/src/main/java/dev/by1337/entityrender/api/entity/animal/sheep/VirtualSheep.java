package dev.by1337.entityrender.api.entity.animal.sheep;

/**
 * Generated from net.minecraft.world.entity.animal.sheep.Sheep. Values use Bukkit types; packed flag fields remain
 * explicit.
 */
public interface VirtualSheep extends dev.by1337.entityrender.api.entity.animal.VirtualAnimal {
    /** NMS DATA_WOOL_ID; BYTE. */
    byte getWool();

    void setWool(byte value);

    boolean isSheared();

    void setSheared(boolean value);

    org.bukkit.DyeColor getColor();

    void setColor(org.bukkit.DyeColor value);
}
