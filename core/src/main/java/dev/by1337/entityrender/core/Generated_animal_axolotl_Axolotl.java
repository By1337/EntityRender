package dev.by1337.entityrender.core;

class Generated_animal_axolotl_Axolotl extends Generated_animal_Animal implements dev.by1337.entityrender.api.entity.animal.axolotl.VirtualAxolotl {
    Generated_animal_axolotl_Axolotl(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isPlayingDead() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.axolotl.Axolotl.DATA_PLAYING_DEAD);
    }

    @Override
    public void setPlayingDead(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.axolotl.Axolotl.DATA_PLAYING_DEAD, value);
    }

    @Override
    public int getVariant() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.axolotl.Axolotl.DATA_VARIANT);
    }

    @Override
    public void setVariant(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.axolotl.Axolotl.DATA_VARIANT, value);
    }

    @Override
    public boolean isFromBucket() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.axolotl.Axolotl.FROM_BUCKET);
    }

    @Override
    public void setFromBucket(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.axolotl.Axolotl.FROM_BUCKET, value);
    }
}
