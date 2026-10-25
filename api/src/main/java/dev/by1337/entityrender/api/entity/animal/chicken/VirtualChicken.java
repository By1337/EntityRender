package dev.by1337.entityrender.api.entity.animal.chicken;

/**
 * Generated from net.minecraft.world.entity.animal.chicken.Chicken. Values use Bukkit types; packed flag fields remain
 * explicit.
 */
public interface VirtualChicken extends dev.by1337.entityrender.api.entity.animal.VirtualAnimal {
    /** NMS DATA_SOUND_VARIANT_ID; CHICKEN_SOUND_VARIANT. */
    org.bukkit.entity.Chicken.SoundVariant getSoundVariant();

    void setSoundVariant(org.bukkit.entity.Chicken.SoundVariant value);

    /** NMS DATA_VARIANT_ID; CHICKEN_VARIANT. */
    org.bukkit.entity.Chicken.Variant getVariant();

    void setVariant(org.bukkit.entity.Chicken.Variant value);
}
