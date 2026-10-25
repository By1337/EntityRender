package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_decoration_Cushion extends Generated_decoration_BlockAttachedEntity implements dev.by1337.entityrender.api.entity.decoration.VirtualCushion {
    Generated_decoration_Cushion(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public org.bukkit.DyeColor getColor() {
        return org.bukkit.DyeColor.valueOf(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.decoration.Cushion.DATA_COLOR).name());
    }

    @Override
    public void setColor(org.bukkit.DyeColor value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.decoration.Cushion.DATA_COLOR, net.minecraft.world.item.DyeColor.valueOf(value.name()));
    }
}
