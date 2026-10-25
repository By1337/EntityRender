package dev.by1337.entityrender.api.entity.animal;

/**
 * Generated from net.minecraft.world.entity.animal.Panda. Values use Bukkit types; packed flag fields remain explicit.
 */
public interface VirtualPanda extends dev.by1337.entityrender.api.entity.animal.VirtualAnimal {
    /** NMS DATA_ID_FLAGS; BYTE. */
    byte getPandaFlags();

    void setPandaFlags(byte value);

    /** NMS EAT_COUNTER; INT. */
    int getEatCounter();

    void setEatCounter(int value);

    /** NMS HIDDEN_GENE_ID; BYTE. */
    byte getHiddenGene();

    void setHiddenGene(byte value);

    /** NMS MAIN_GENE_ID; BYTE. */
    byte getMainGene();

    void setMainGene(byte value);

    /** NMS SNEEZE_COUNTER; INT. */
    int getSneezeCounter();

    void setSneezeCounter(int value);

    /** NMS UNHAPPY_COUNTER; INT. */
    int getUnhappyCounter();

    void setUnhappyCounter(int value);
}
