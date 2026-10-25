package dev.by1337.entityrender.api.entity.animal.sniffer;

/**
 * Generated from net.minecraft.world.entity.animal.sniffer.Sniffer. Values use Bukkit types; packed flag fields remain
 * explicit.
 */
public interface VirtualSniffer extends dev.by1337.entityrender.api.entity.animal.VirtualAnimal {
    /** NMS DATA_DROP_SEED_AT_TICK; INT. */
    int getDropSeedAtTick();

    void setDropSeedAtTick(int value);

    /** NMS DATA_STATE; SNIFFER_STATE. */
    org.bukkit.entity.Sniffer.State getState();

    void setState(org.bukkit.entity.Sniffer.State value);
}
