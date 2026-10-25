package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_animal_sniffer_Sniffer extends Generated_animal_Animal implements dev.by1337.entityrender.api.entity.animal.sniffer.VirtualSniffer {
    Generated_animal_sniffer_Sniffer(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public int getDropSeedAtTick() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.sniffer.Sniffer.DATA_DROP_SEED_AT_TICK);
    }

    @Override
    public void setDropSeedAtTick(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.sniffer.Sniffer.DATA_DROP_SEED_AT_TICK, value);
    }

    @Override
    public org.bukkit.entity.Sniffer.State getState() {
        return org.bukkit.entity.Sniffer.State.valueOf(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.sniffer.Sniffer.DATA_STATE).name());
    }

    @Override
    public void setState(org.bukkit.entity.Sniffer.State value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.sniffer.Sniffer.DATA_STATE, net.minecraft.world.entity.animal.sniffer.Sniffer.State.valueOf(value.name()));
    }
}
