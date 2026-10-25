package dev.by1337.entityrender.api.entity.animal.goat;

/**
 * Generated from net.minecraft.world.entity.animal.goat.Goat. Values use Bukkit types; packed flag fields remain
 * explicit.
 */
public interface VirtualGoat extends dev.by1337.entityrender.api.entity.animal.VirtualAnimal {
    /** NMS DATA_HAS_LEFT_HORN; BOOLEAN. */
    boolean isHasLeftHorn();

    void setHasLeftHorn(boolean value);

    /** NMS DATA_HAS_RIGHT_HORN; BOOLEAN. */
    boolean isHasRightHorn();

    void setHasRightHorn(boolean value);

    /** NMS DATA_IS_SCREAMING_GOAT; BOOLEAN. */
    boolean isScreamingGoat();

    void setScreamingGoat(boolean value);
}
