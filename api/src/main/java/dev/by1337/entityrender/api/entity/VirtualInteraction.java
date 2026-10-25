package dev.by1337.entityrender.api.entity;

/**
 * Generated from net.minecraft.world.entity.Interaction. Values use Bukkit types; packed flag fields remain explicit.
 */
public interface VirtualInteraction extends dev.by1337.entityrender.api.entity.VirtualEntity {
    /** NMS DATA_HEIGHT_ID; FLOAT. */
    float getHeight();

    void setHeight(float value);

    /** NMS DATA_RESPONSE_ID; BOOLEAN. */
    boolean isResponse();

    void setResponse(boolean value);

    /** NMS DATA_WIDTH_ID; FLOAT. */
    float getWidth();

    void setWidth(float value);
}
