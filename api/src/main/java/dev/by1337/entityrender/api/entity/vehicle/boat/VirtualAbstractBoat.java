package dev.by1337.entityrender.api.entity.vehicle.boat;

/**
 * Generated from net.minecraft.world.entity.vehicle.boat.AbstractBoat. Values use Bukkit types; packed flag fields
 * remain explicit.
 */
public interface VirtualAbstractBoat extends dev.by1337.entityrender.api.entity.vehicle.VirtualVehicleEntity {
    /** NMS DATA_ID_BUBBLE_TIME; INT. */
    int getBubbleTime();

    void setBubbleTime(int value);

    /** NMS DATA_ID_PADDLE_LEFT; BOOLEAN. */
    boolean isPaddleLeft();

    void setPaddleLeft(boolean value);

    /** NMS DATA_ID_PADDLE_RIGHT; BOOLEAN. */
    boolean isPaddleRight();

    void setPaddleRight(boolean value);
}
