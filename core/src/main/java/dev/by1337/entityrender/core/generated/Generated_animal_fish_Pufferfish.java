package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_animal_fish_Pufferfish extends Generated_animal_fish_AbstractFish implements dev.by1337.entityrender.api.entity.animal.fish.VirtualPufferfish {
    Generated_animal_fish_Pufferfish(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public int getPuffState() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.fish.Pufferfish.PUFF_STATE);
    }

    @Override
    public void setPuffState(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.fish.Pufferfish.PUFF_STATE, value);
    }
}
