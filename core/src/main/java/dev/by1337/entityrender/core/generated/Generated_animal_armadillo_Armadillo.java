package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_animal_armadillo_Armadillo extends Generated_animal_Animal implements dev.by1337.entityrender.api.entity.animal.armadillo.VirtualArmadillo {
    Generated_animal_armadillo_Armadillo(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public org.bukkit.entity.Armadillo.State getArmadilloState() {
        return org.bukkit.entity.Armadillo.State.valueOf(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.armadillo.Armadillo.ARMADILLO_STATE).name());
    }

    @Override
    public void setArmadilloState(org.bukkit.entity.Armadillo.State value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.armadillo.Armadillo.ARMADILLO_STATE, net.minecraft.world.entity.animal.armadillo.Armadillo.ArmadilloState.valueOf(java.util.Objects.requireNonNull(value).name()));
    }
}
