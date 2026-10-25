package dev.by1337.entityrender.core;

class Generated_Interaction extends BaseVirtualEntity implements dev.by1337.entityrender.api.entity.VirtualInteraction {
    Generated_Interaction(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public float getHeight() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.Interaction.DATA_HEIGHT_ID);
    }

    @Override
    public void setHeight(float value) {
        finite(value);
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.Interaction.DATA_HEIGHT_ID, value);
    }

    @Override
    public boolean isResponse() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.Interaction.DATA_RESPONSE_ID);
    }

    @Override
    public void setResponse(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.Interaction.DATA_RESPONSE_ID, value);
    }

    @Override
    public float getWidth() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.Interaction.DATA_WIDTH_ID);
    }

    @Override
    public void setWidth(float value) {
        finite(value);
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.Interaction.DATA_WIDTH_ID, value);
    }
}
