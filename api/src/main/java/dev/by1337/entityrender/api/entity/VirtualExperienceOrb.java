package dev.by1337.entityrender.api.entity;

/**
 * Generated from net.minecraft.world.entity.ExperienceOrb. Values use Bukkit types; packed flag fields remain explicit.
 */
public interface VirtualExperienceOrb extends dev.by1337.entityrender.api.entity.VirtualEntity {
    /** NMS DATA_VALUE; INT. */
    int getValue();

    void setValue(int value);

    int getExperienceValue();

    void setExperienceValue(int value);
}
