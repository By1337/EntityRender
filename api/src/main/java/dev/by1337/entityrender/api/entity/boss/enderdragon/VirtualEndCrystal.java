package dev.by1337.entityrender.api.entity.boss.enderdragon;

/**
 * Generated from net.minecraft.world.entity.boss.enderdragon.EndCrystal. Values use Bukkit types; packed flag fields
 * remain explicit.
 */
public interface VirtualEndCrystal extends dev.by1337.entityrender.api.entity.VirtualEntity {
    /** NMS DATA_BEAM_TARGET; OPTIONAL_BLOCK_POS. */
    org.bukkit.util.BlockVector getBeamTarget();

    void setBeamTarget(org.bukkit.util.BlockVector value);

    /** NMS DATA_SHOW_BOTTOM; BOOLEAN. */
    boolean isShowBottom();

    void setShowBottom(boolean value);
}
