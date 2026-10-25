package dev.by1337.entityrender.core;

class Generated_animal_squid_GlowSquid extends Generated_animal_squid_Squid implements dev.by1337.entityrender.api.entity.animal.squid.VirtualGlowSquid {
    Generated_animal_squid_GlowSquid(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public int getDarkTicksRemaining() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.squid.GlowSquid.DATA_DARK_TICKS_REMAINING);
    }

    @Override
    public void setDarkTicksRemaining(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.squid.GlowSquid.DATA_DARK_TICKS_REMAINING, value);
    }
}
