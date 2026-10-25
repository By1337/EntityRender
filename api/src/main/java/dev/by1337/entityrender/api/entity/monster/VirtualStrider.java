package dev.by1337.entityrender.api.entity.monster;

/**
 * Generated from net.minecraft.world.entity.monster.Strider. Values use Bukkit types; packed flag fields remain
 * explicit.
 */
public interface VirtualStrider extends dev.by1337.entityrender.api.entity.animal.VirtualAnimal {
    /** NMS DATA_BOOST_TIME; INT. */
    int getBoostTime();

    void setBoostTime(int value);

    /** NMS DATA_SUFFOCATING; BOOLEAN. */
    boolean isSuffocating();

    void setSuffocating(boolean value);
}
