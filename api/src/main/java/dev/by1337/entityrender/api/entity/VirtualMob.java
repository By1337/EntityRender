package dev.by1337.entityrender.api.entity;

/** Generated from net.minecraft.world.entity.Mob. Values use Bukkit types; packed flag fields remain explicit. */
public interface VirtualMob extends dev.by1337.entityrender.api.entity.VirtualLivingEntity {
    /** NMS DATA_MOB_FLAGS_ID; BYTE. */
    byte getMobFlags();

    void setMobFlags(byte value);

    boolean isNoAi();

    void setNoAi(boolean value);

    boolean isLeftHanded();

    void setLeftHanded(boolean value);

    boolean isAggressive();

    void setAggressive(boolean value);
}
