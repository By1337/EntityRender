package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_animal_nautilus_ZombieNautilus extends Generated_animal_nautilus_AbstractNautilus implements dev.by1337.entityrender.api.entity.animal.nautilus.VirtualZombieNautilus {
    Generated_animal_nautilus_ZombieNautilus(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public org.bukkit.entity.ZombieNautilus.Variant getVariant() {
        return org.bukkit.craftbukkit.CraftRegistry.minecraftHolderToBukkit(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.nautilus.ZombieNautilus.DATA_VARIANT_ID), net.minecraft.core.registries.Registries.ZOMBIE_NAUTILUS_VARIANT);
    }

    @Override
    public void setVariant(org.bukkit.entity.ZombieNautilus.Variant value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.nautilus.ZombieNautilus.DATA_VARIANT_ID, ValueConversions.holder(java.util.Objects.requireNonNull(value), net.minecraft.core.registries.Registries.ZOMBIE_NAUTILUS_VARIANT));
    }
}
