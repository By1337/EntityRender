package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_boss_enderdragon_EndCrystal extends BaseVirtualEntity implements dev.by1337.entityrender.api.entity.boss.enderdragon.VirtualEndCrystal {
    Generated_boss_enderdragon_EndCrystal(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public org.bukkit.util.BlockVector getBeamTarget() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.boss.enderdragon.EndCrystal.DATA_BEAM_TARGET).map(ValueConversions::position).orElse(null);
    }

    @Override
    public void setBeamTarget(org.bukkit.util.BlockVector value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.boss.enderdragon.EndCrystal.DATA_BEAM_TARGET, java.util.Optional.ofNullable(value).map(ValueConversions::position));
    }

    @Override
    public boolean isShowBottom() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.boss.enderdragon.EndCrystal.DATA_SHOW_BOTTOM);
    }

    @Override
    public void setShowBottom(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.boss.enderdragon.EndCrystal.DATA_SHOW_BOTTOM, value);
    }
}
