package dev.by1337.entityrender.api.entity.animal.dolphin;

/**
 * Generated from net.minecraft.world.entity.animal.dolphin.Dolphin. Values use Bukkit types; packed flag fields remain
 * explicit.
 */
public interface VirtualDolphin extends dev.by1337.entityrender.api.entity.animal.VirtualAgeableWaterCreature {
    /** NMS GOT_FISH; BOOLEAN. */
    boolean isGotFish();

    void setGotFish(boolean value);

    /** NMS MOISTNESS_LEVEL; INT. */
    int getMoistnessLevel();

    void setMoistnessLevel(int value);
}
