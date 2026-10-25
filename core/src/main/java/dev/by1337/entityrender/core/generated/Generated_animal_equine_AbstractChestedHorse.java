package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_animal_equine_AbstractChestedHorse extends Generated_animal_equine_AbstractHorse implements dev.by1337.entityrender.api.entity.animal.equine.VirtualAbstractChestedHorse {
    Generated_animal_equine_AbstractChestedHorse(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isChest() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.equine.AbstractChestedHorse.DATA_ID_CHEST);
    }

    @Override
    public void setChest(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.equine.AbstractChestedHorse.DATA_ID_CHEST, value);
    }
}
