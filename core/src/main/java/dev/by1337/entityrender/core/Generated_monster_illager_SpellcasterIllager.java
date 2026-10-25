package dev.by1337.entityrender.core;

class Generated_monster_illager_SpellcasterIllager extends Generated_monster_illager_AbstractIllager implements dev.by1337.entityrender.api.entity.monster.illager.VirtualSpellcasterIllager {
    Generated_monster_illager_SpellcasterIllager(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public byte getSpellCasting() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.illager.SpellcasterIllager.DATA_SPELL_CASTING_ID);
    }

    @Override
    public void setSpellCasting(byte value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.illager.SpellcasterIllager.DATA_SPELL_CASTING_ID, value);
    }
}
