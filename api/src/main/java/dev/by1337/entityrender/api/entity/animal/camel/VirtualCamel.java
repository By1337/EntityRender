package dev.by1337.entityrender.api.entity.animal.camel;

/**
 * Generated from net.minecraft.world.entity.animal.camel.Camel. Values use Bukkit types; packed flag fields remain
 * explicit.
 */
public interface VirtualCamel extends dev.by1337.entityrender.api.entity.animal.horse.VirtualAbstractHorse {
    /** NMS DASH; BOOLEAN. */
    boolean isDash();

    void setDash(boolean value);

    /** NMS LAST_POSE_CHANGE_TICK; LONG. */
    long getLastPoseChangeTick();

    void setLastPoseChangeTick(long value);
}
