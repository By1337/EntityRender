package dev.by1337.entityrender.core;

class Generated_animal_dolphin_Dolphin extends Generated_animal_AgeableWaterCreature implements dev.by1337.entityrender.api.entity.animal.dolphin.VirtualDolphin {
    Generated_animal_dolphin_Dolphin(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isGotFish() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.dolphin.Dolphin.GOT_FISH);
    }

    @Override
    public void setGotFish(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.dolphin.Dolphin.GOT_FISH, value);
    }

    @Override
    public int getMoistnessLevel() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.dolphin.Dolphin.MOISTNESS_LEVEL);
    }

    @Override
    public void setMoistnessLevel(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.dolphin.Dolphin.MOISTNESS_LEVEL, value);
    }
}
