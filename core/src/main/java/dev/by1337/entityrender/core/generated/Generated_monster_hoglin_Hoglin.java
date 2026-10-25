package dev.by1337.entityrender.core.generated;

import dev.by1337.entityrender.core.BaseVirtualEntity;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import dev.by1337.entityrender.core.ValueConversions;

class Generated_monster_hoglin_Hoglin extends Generated_animal_Animal implements dev.by1337.entityrender.api.entity.monster.hoglin.VirtualHoglin {
    Generated_monster_hoglin_Hoglin(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isImmuneToZombification() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.hoglin.Hoglin.DATA_IMMUNE_TO_ZOMBIFICATION);
    }

    @Override
    public void setImmuneToZombification(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.hoglin.Hoglin.DATA_IMMUNE_TO_ZOMBIFICATION, value);
    }
}
