package dev.by1337.entityrender.core;

class Generated_animal_panda_Panda extends Generated_animal_Animal implements dev.by1337.entityrender.api.entity.animal.panda.VirtualPanda {
    Generated_animal_panda_Panda(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public byte getPandaFlags() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.panda.Panda.DATA_ID_FLAGS);
    }

    @Override
    public void setPandaFlags(byte value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.panda.Panda.DATA_ID_FLAGS, value);
    }

    @Override
    public int getEatCounter() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.panda.Panda.EAT_COUNTER);
    }

    @Override
    public void setEatCounter(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.panda.Panda.EAT_COUNTER, value);
    }

    @Override
    public byte getHiddenGene() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.panda.Panda.HIDDEN_GENE_ID);
    }

    @Override
    public void setHiddenGene(byte value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.panda.Panda.HIDDEN_GENE_ID, value);
    }

    @Override
    public byte getMainGene() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.panda.Panda.MAIN_GENE_ID);
    }

    @Override
    public void setMainGene(byte value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.panda.Panda.MAIN_GENE_ID, value);
    }

    @Override
    public int getSneezeCounter() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.panda.Panda.SNEEZE_COUNTER);
    }

    @Override
    public void setSneezeCounter(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.panda.Panda.SNEEZE_COUNTER, value);
    }

    @Override
    public int getUnhappyCounter() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.panda.Panda.UNHAPPY_COUNTER);
    }

    @Override
    public void setUnhappyCounter(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.panda.Panda.UNHAPPY_COUNTER, value);
    }
}
