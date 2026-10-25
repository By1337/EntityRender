package dev.by1337.entityrender.api.entity;

/**
 * Generated from net.minecraft.world.entity.LivingEntity. Values use Bukkit types; packed flag fields remain explicit.
 */
public interface VirtualLivingEntity extends dev.by1337.entityrender.api.entity.VirtualEntity {
    /** NMS DATA_ARROW_COUNT_ID; INT. */
    int getArrowCount();

    void setArrowCount(int value);

    /** NMS DATA_EFFECT_AMBIENCE_ID; BOOLEAN. */
    boolean isEffectAmbience();

    void setEffectAmbience(boolean value);

    /** NMS DATA_EFFECT_PARTICLES; PARTICLES. */
    java.util.List<String> getEffectParticlesSnbt();

    void setEffectParticlesSnbt(java.util.List<String> value);

    /** NMS DATA_HEALTH_ID; FLOAT. */
    float getHealth();

    void setHealth(float value);

    /** NMS DATA_LIVING_ENTITY_FLAGS; BYTE. */
    byte getLivingEntityFlags();

    void setLivingEntityFlags(byte value);

    /** NMS DATA_STINGER_COUNT_ID; INT. */
    int getStingerCount();

    void setStingerCount(int value);

    /** NMS SLEEPING_POS_ID; OPTIONAL_BLOCK_POS. */
    org.bukkit.util.BlockVector getSleepingPos();

    void setSleepingPos(org.bukkit.util.BlockVector value);

    org.bukkit.inventory.ItemStack getEquipment(org.bukkit.inventory.EquipmentSlot slot);

    void setEquipment(org.bukkit.inventory.EquipmentSlot slot, org.bukkit.inventory.ItemStack item);
}
