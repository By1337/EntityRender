package dev.by1337.entityrender.core;

class Generated_ExperienceOrb extends BaseVirtualEntity implements dev.by1337.entityrender.api.entity.VirtualExperienceOrb {
    Generated_ExperienceOrb(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public int getExperienceValue() {
        return getSpawnData();
    }

    @Override
    public void setExperienceValue(int value) {
        if (value < 0 || value > 32767) throw new IllegalArgumentException("Experience value must be 0..32767");
        setSpawnData(value);
    }
}
