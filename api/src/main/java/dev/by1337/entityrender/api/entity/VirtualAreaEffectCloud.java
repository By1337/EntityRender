package dev.by1337.entityrender.api.entity;

/**
 * Generated from net.minecraft.world.entity.AreaEffectCloud. Values use Bukkit types; packed flag fields remain
 * explicit.
 */
public interface VirtualAreaEffectCloud extends dev.by1337.entityrender.api.entity.VirtualEntity {
    /** NMS DATA_PARTICLE; PARTICLE. */
    String getParticleSnbt();

    void setParticleSnbt(String value);

    /** NMS DATA_RADIUS; FLOAT. */
    float getRadius();

    void setRadius(float value);

    /** NMS DATA_WAITING; BOOLEAN. */
    boolean isWaiting();

    void setWaiting(boolean value);
}
