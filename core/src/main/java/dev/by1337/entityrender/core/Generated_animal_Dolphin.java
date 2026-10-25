package dev.by1337.entityrender.core;

class Generated_animal_Dolphin extends Generated_animal_AgeableWaterCreature implements dev.by1337.entityrender.api.entity.animal.VirtualDolphin {
    Generated_animal_Dolphin(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isGotFish() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Dolphin.GOT_FISH);
    }

    @Override
    public void setGotFish(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Dolphin.GOT_FISH, value);
    }

    @Override
    public int getMoistnessLevel() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Dolphin.MOISTNESS_LEVEL);
    }

    @Override
    public void setMoistnessLevel(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Dolphin.MOISTNESS_LEVEL, value);
    }

    @Override
    public org.bukkit.util.BlockVector getTreasurePos() {
        return ValueConversions.position(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Dolphin.TREASURE_POS));
    }

    @Override
    public void setTreasurePos(org.bukkit.util.BlockVector value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Dolphin.TREASURE_POS, ValueConversions.position(value));
    }
}
