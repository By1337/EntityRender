package dev.by1337.entityrender.api.entity.monster;

/**
 * Generated from net.minecraft.world.entity.monster.Enderman. Values use Bukkit types; packed flag fields remain
 * explicit.
 */
public interface VirtualEnderman extends dev.by1337.entityrender.api.entity.monster.VirtualMonster {
    /** NMS DATA_CARRY_STATE; OPTIONAL_BLOCK_STATE. */
    org.bukkit.block.data.BlockData getCarryState();

    void setCarryState(org.bukkit.block.data.BlockData value);

    /** NMS DATA_CREEPY; BOOLEAN. */
    boolean isCreepy();

    void setCreepy(boolean value);

    /** NMS DATA_STARED_AT; BOOLEAN. */
    boolean isStaredAt();

    void setStaredAt(boolean value);
}
