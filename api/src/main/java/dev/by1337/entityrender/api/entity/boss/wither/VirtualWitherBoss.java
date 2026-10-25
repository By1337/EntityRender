package dev.by1337.entityrender.api.entity.boss.wither;

/**
 * Generated from net.minecraft.world.entity.boss.wither.WitherBoss. Values use Bukkit types; packed flag fields remain
 * explicit.
 */
public interface VirtualWitherBoss extends dev.by1337.entityrender.api.entity.monster.VirtualMonster {
    /** NMS DATA_ID_INV; INT. */
    int getInv();

    void setInv(int value);

    /** NMS DATA_TARGET_A; INT. */
    int getTargetA();

    void setTargetA(int value);

    /** NMS DATA_TARGET_B; INT. */
    int getTargetB();

    void setTargetB(int value);

    /** NMS DATA_TARGET_C; INT. */
    int getTargetC();

    void setTargetC(int value);
}
