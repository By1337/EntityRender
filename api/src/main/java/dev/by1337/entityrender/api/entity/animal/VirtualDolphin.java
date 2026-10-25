package dev.by1337.entityrender.api.entity.animal;

/**
 * Generated from net.minecraft.world.entity.animal.Dolphin. Values use Bukkit types; packed flag fields remain
 * explicit.
 */
public interface VirtualDolphin extends dev.by1337.entityrender.api.entity.animal.VirtualAgeableWaterCreature {
    /** NMS GOT_FISH; BOOLEAN. */
    boolean isGotFish();

    void setGotFish(boolean value);

    /** NMS MOISTNESS_LEVEL; INT. */
    int getMoistnessLevel();

    void setMoistnessLevel(int value);

    /** NMS TREASURE_POS; BLOCK_POS. */
    org.bukkit.util.BlockVector getTreasurePos();

    void setTreasurePos(org.bukkit.util.BlockVector value);
}
