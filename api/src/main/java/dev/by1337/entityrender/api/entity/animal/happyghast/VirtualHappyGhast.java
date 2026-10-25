package dev.by1337.entityrender.api.entity.animal.happyghast;

/**
 * Generated from net.minecraft.world.entity.animal.happyghast.HappyGhast. Values use Bukkit types; packed flag fields
 * remain explicit.
 */
public interface VirtualHappyGhast extends dev.by1337.entityrender.api.entity.animal.VirtualAnimal {
    /** NMS IS_LEASH_HOLDER; BOOLEAN. */
    boolean isLeashHolder();

    void setLeashHolder(boolean value);

    /** NMS STAYS_STILL; BOOLEAN. */
    boolean isStaysStill();

    void setStaysStill(boolean value);
}
