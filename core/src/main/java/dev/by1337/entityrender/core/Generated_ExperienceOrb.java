package dev.by1337.entityrender.core;

class Generated_ExperienceOrb extends BaseVirtualEntity implements dev.by1337.entityrender.api.entity.VirtualExperienceOrb {
    Generated_ExperienceOrb(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public int getValue() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.ExperienceOrb.DATA_VALUE);
    }

    @Override
    public void setValue(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.ExperienceOrb.DATA_VALUE, value);
    }
}
