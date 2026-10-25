package dev.by1337.entityrender.api.entity.animal;

/**
 * Generated from net.minecraft.world.entity.animal.Cow. Values use Bukkit types; packed flag fields remain explicit.
 */
public interface VirtualCow extends dev.by1337.entityrender.api.entity.animal.VirtualAbstractCow {
    /** NMS DATA_VARIANT_ID; COW_VARIANT. */
    org.bukkit.NamespacedKey getVariant();

    void setVariant(org.bukkit.NamespacedKey value);
}
