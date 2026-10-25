package dev.by1337.entityrender.api.entity.animal;

/**
 * Generated from net.minecraft.world.entity.animal.Pig. Values use Bukkit types; packed flag fields remain explicit.
 */
public interface VirtualPig extends dev.by1337.entityrender.api.entity.animal.VirtualAnimal {
    /** NMS DATA_BOOST_TIME; INT. */
    int getBoostTime();

    void setBoostTime(int value);

    /** NMS DATA_VARIANT_ID; PIG_VARIANT. */
    org.bukkit.NamespacedKey getVariant();

    void setVariant(org.bukkit.NamespacedKey value);
}
