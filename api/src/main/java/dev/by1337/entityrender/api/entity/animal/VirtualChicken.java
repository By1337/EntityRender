package dev.by1337.entityrender.api.entity.animal;

/**
 * Generated from net.minecraft.world.entity.animal.Chicken. Values use Bukkit types; packed flag fields remain
 * explicit.
 */
public interface VirtualChicken extends dev.by1337.entityrender.api.entity.animal.VirtualAnimal {
    /** NMS DATA_VARIANT_ID; CHICKEN_VARIANT. */
    org.bukkit.entity.Chicken.Variant getVariant();

    void setVariant(org.bukkit.entity.Chicken.Variant value);
}
