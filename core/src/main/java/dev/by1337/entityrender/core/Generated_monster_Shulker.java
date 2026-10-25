package dev.by1337.entityrender.core;

class Generated_monster_Shulker extends Generated_animal_AbstractGolem implements dev.by1337.entityrender.api.entity.monster.VirtualShulker {
    Generated_monster_Shulker(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public org.bukkit.block.BlockFace getAttachFace() {
        return ValueConversions.direction(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Shulker.DATA_ATTACH_FACE_ID));
    }

    @Override
    public void setAttachFace(org.bukkit.block.BlockFace value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Shulker.DATA_ATTACH_FACE_ID, ValueConversions.direction(value));
    }

    @Override
    public org.bukkit.DyeColor getColor() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Shulker.DATA_COLOR_ID) == 16 ? null : org.bukkit.DyeColor.valueOf(net.minecraft.world.item.DyeColor.byId(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Shulker.DATA_COLOR_ID)).name());
    }

    @Override
    public void setColor(org.bukkit.DyeColor value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Shulker.DATA_COLOR_ID, (byte) (value == null ? 16 : net.minecraft.world.item.DyeColor.valueOf(value.name()).getId()));
    }

    @Override
    public byte getPeek() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Shulker.DATA_PEEK_ID);
    }

    @Override
    public void setPeek(byte value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.monster.Shulker.DATA_PEEK_ID, value);
    }
}
