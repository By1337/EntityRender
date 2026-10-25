package dev.by1337.entityrender.api.entity.animal;

/**
 * Generated from net.minecraft.world.entity.animal.Cat. Values use Bukkit types; packed flag fields remain explicit.
 */
public interface VirtualCat extends dev.by1337.entityrender.api.entity.VirtualTamableAnimal {
    /** NMS DATA_COLLAR_COLOR; INT. */
    org.bukkit.DyeColor getCollarColor();

    void setCollarColor(org.bukkit.DyeColor value);

    /** NMS DATA_VARIANT_ID; CAT_VARIANT. */
    org.bukkit.entity.Cat.Type getVariant();

    void setVariant(org.bukkit.entity.Cat.Type value);

    /** NMS IS_LYING; BOOLEAN. */
    boolean isLying();

    void setLying(boolean value);

    /** NMS RELAX_STATE_ONE; BOOLEAN. */
    boolean isRelaxStateOne();

    void setRelaxStateOne(boolean value);
}
