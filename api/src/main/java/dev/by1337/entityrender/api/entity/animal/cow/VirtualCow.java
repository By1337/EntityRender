package dev.by1337.entityrender.api.entity.animal.cow;

/**
 * Generated from net.minecraft.world.entity.animal.cow.Cow. Values use Bukkit types; packed flag fields remain
 * explicit.
 */
public interface VirtualCow extends dev.by1337.entityrender.api.entity.animal.cow.VirtualAbstractCow {
    /** NMS DATA_SOUND_VARIANT_ID; COW_SOUND_VARIANT. */
    org.bukkit.entity.Cow.SoundVariant getSoundVariant();

    void setSoundVariant(org.bukkit.entity.Cow.SoundVariant value);

    /** NMS DATA_VARIANT_ID; COW_VARIANT. */
    org.bukkit.entity.Cow.Variant getVariant();

    void setVariant(org.bukkit.entity.Cow.Variant value);
}
