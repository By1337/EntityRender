package dev.by1337.entityrender.core;

class Generated_animal_golem_SnowGolem extends Generated_animal_golem_AbstractGolem implements dev.by1337.entityrender.api.entity.animal.golem.VirtualSnowGolem {
    Generated_animal_golem_SnowGolem(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public byte getPumpkin() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.golem.SnowGolem.DATA_PUMPKIN_ID);
    }

    @Override
    public void setPumpkin(byte value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.golem.SnowGolem.DATA_PUMPKIN_ID, value);
    }
}
