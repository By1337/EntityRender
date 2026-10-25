package dev.by1337.entityrender.api.entity.animal.axolotl;

/**
 * Generated from net.minecraft.world.entity.animal.axolotl.Axolotl. Values use Bukkit types; packed flag fields remain
 * explicit.
 */
public interface VirtualAxolotl extends dev.by1337.entityrender.api.entity.animal.VirtualAnimal {
    /** NMS DATA_PLAYING_DEAD; BOOLEAN. */
    boolean isPlayingDead();

    void setPlayingDead(boolean value);

    /** NMS DATA_VARIANT; INT. */
    int getVariant();

    void setVariant(int value);

    /** NMS FROM_BUCKET; BOOLEAN. */
    boolean isFromBucket();

    void setFromBucket(boolean value);
}
