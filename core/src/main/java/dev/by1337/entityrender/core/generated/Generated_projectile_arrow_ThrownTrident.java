package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_projectile_arrow_ThrownTrident extends Generated_projectile_arrow_AbstractArrow implements dev.by1337.entityrender.api.entity.projectile.arrow.VirtualThrownTrident {
    Generated_projectile_arrow_ThrownTrident(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isFoil() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.projectile.arrow.ThrownTrident.ID_FOIL);
    }

    @Override
    public void setFoil(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.projectile.arrow.ThrownTrident.ID_FOIL, value);
    }

    @Override
    public byte getLoyalty() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.projectile.arrow.ThrownTrident.ID_LOYALTY);
    }

    @Override
    public void setLoyalty(byte value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.projectile.arrow.ThrownTrident.ID_LOYALTY, value);
    }
}
