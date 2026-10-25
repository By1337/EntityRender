package dev.by1337.entityrender.api.entity.animal;

/**
 * Generated from net.minecraft.world.entity.animal.Turtle. Values use Bukkit types; packed flag fields remain explicit.
 */
public interface VirtualTurtle extends dev.by1337.entityrender.api.entity.animal.VirtualAnimal {
    /** NMS GOING_HOME; BOOLEAN. */
    boolean isGoingHome();

    void setGoingHome(boolean value);

    /** NMS HAS_EGG; BOOLEAN. */
    boolean isHasEgg();

    void setHasEgg(boolean value);

    /** NMS HOME_POS; BLOCK_POS. */
    org.bukkit.util.BlockVector getHomePos();

    void setHomePos(org.bukkit.util.BlockVector value);

    /** NMS LAYING_EGG; BOOLEAN. */
    boolean isLayingEgg();

    void setLayingEgg(boolean value);

    /** NMS TRAVELLING; BOOLEAN. */
    boolean isTravelling();

    void setTravelling(boolean value);

    /** NMS TRAVEL_POS; BLOCK_POS. */
    org.bukkit.util.BlockVector getTravelPos();

    void setTravelPos(org.bukkit.util.BlockVector value);
}
