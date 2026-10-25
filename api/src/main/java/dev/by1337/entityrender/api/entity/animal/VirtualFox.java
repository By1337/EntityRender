package dev.by1337.entityrender.api.entity.animal;

/**
 * Generated from net.minecraft.world.entity.animal.Fox. Values use Bukkit types; packed flag fields remain explicit.
 */
public interface VirtualFox extends dev.by1337.entityrender.api.entity.animal.VirtualAnimal {
    /** NMS DATA_FLAGS_ID; BYTE. */
    byte getFoxFlags();

    void setFoxFlags(byte value);

    /** NMS DATA_TRUSTED_ID_0; OPTIONAL_UUID. */
    java.util.UUID getTrustedId0();

    void setTrustedId0(java.util.UUID value);

    /** NMS DATA_TRUSTED_ID_1; OPTIONAL_UUID. */
    java.util.UUID getTrustedId1();

    void setTrustedId1(java.util.UUID value);

    /** NMS DATA_TYPE_ID; INT. */
    int getVariant();

    void setVariant(int value);
}
