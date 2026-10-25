package dev.by1337.entityrender.api.entity.animal.frog;

/**
 * Generated from net.minecraft.world.entity.animal.frog.Frog. Values use Bukkit types; packed flag fields remain
 * explicit.
 */
public interface VirtualFrog extends dev.by1337.entityrender.api.entity.animal.VirtualAnimal {
    /** NMS DATA_TONGUE_TARGET_ID; OPTIONAL_UNSIGNED_INT. */
    java.util.OptionalInt getTongueTarget();

    void setTongueTarget(java.util.OptionalInt value);

    /** NMS DATA_VARIANT_ID; FROG_VARIANT. */
    org.bukkit.entity.Frog.Variant getVariant();

    void setVariant(org.bukkit.entity.Frog.Variant value);
}
