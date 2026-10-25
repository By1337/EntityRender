package dev.by1337.entityrender.api.entity.decoration;

/**
 * Generated from net.minecraft.world.entity.decoration.Mannequin. Values use Bukkit types; packed flag fields remain
 * explicit.
 */
public interface VirtualMannequin extends dev.by1337.entityrender.api.entity.VirtualAvatar {
    /** NMS DATA_DESCRIPTION; OPTIONAL_COMPONENT. */
    net.kyori.adventure.text.Component getDescription();

    void setDescription(net.kyori.adventure.text.Component value);

    /** NMS DATA_IMMOVABLE; BOOLEAN. */
    boolean isImmovable();

    void setImmovable(boolean value);

    /** NMS DATA_PROFILE; RESOLVABLE_PROFILE. */
    String getProfileSnbt();

    void setProfileSnbt(String value);
}
