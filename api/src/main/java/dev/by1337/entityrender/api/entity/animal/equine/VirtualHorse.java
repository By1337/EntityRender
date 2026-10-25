package dev.by1337.entityrender.api.entity.animal.equine;

/**
 * Generated from net.minecraft.world.entity.animal.equine.Horse. Values use Bukkit types; packed flag fields remain
 * explicit.
 */
public interface VirtualHorse extends dev.by1337.entityrender.api.entity.animal.equine.VirtualAbstractHorse {
    /** NMS DATA_ID_TYPE_VARIANT; INT. */
    int getTypeVariant();

    void setTypeVariant(int value);

    org.bukkit.entity.Horse.Color getColor();

    void setColor(org.bukkit.entity.Horse.Color value);

    org.bukkit.entity.Horse.Style getStyle();

    void setStyle(org.bukkit.entity.Horse.Style value);
}
