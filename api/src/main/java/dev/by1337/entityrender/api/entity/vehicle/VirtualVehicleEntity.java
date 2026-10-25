package dev.by1337.entityrender.api.entity.vehicle;

/**
 * Generated from net.minecraft.world.entity.vehicle.VehicleEntity. Values use Bukkit types; packed flag fields remain
 * explicit.
 */
public interface VirtualVehicleEntity extends dev.by1337.entityrender.api.entity.VirtualEntity {
    /** NMS DATA_ID_DAMAGE; FLOAT. */
    float getDamage();

    void setDamage(float value);

    /** NMS DATA_ID_HURT; INT. */
    int getHurt();

    void setHurt(int value);

    /** NMS DATA_ID_HURTDIR; INT. */
    int getHurtdir();

    void setHurtdir(int value);
}
