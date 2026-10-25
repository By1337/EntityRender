package dev.by1337.entityrender.core;

class Generated_animal_horse_AbstractChestedHorse extends Generated_animal_horse_AbstractHorse implements dev.by1337.entityrender.api.entity.animal.horse.VirtualAbstractChestedHorse {
    Generated_animal_horse_AbstractChestedHorse(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isChest() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.horse.AbstractChestedHorse.DATA_ID_CHEST);
    }

    @Override
    public void setChest(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.horse.AbstractChestedHorse.DATA_ID_CHEST, value);
    }
}
