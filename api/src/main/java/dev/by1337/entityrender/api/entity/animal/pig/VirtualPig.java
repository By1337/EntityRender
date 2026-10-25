package dev.by1337.entityrender.api.entity.animal.pig;

/**
 * Generated from net.minecraft.world.entity.animal.pig.Pig. Values use Bukkit types; packed flag fields remain
 * explicit.
 */
public interface VirtualPig extends dev.by1337.entityrender.api.entity.animal.VirtualAnimal {
    /** NMS DATA_BOOST_TIME; INT. */
    int getBoostTime();

    void setBoostTime(int value);

    /** NMS DATA_SOUND_VARIANT_ID; PIG_SOUND_VARIANT. */
    org.bukkit.entity.Pig.SoundVariant getSoundVariant();

    void setSoundVariant(org.bukkit.entity.Pig.SoundVariant value);

    /** NMS DATA_VARIANT_ID; PIG_VARIANT. */
    org.bukkit.entity.Pig.Variant getVariant();

    void setVariant(org.bukkit.entity.Pig.Variant value);
}
