package dev.by1337.entityrender.core;

class Generated_decoration_ItemFrame extends Generated_decoration_HangingEntity implements dev.by1337.entityrender.api.entity.decoration.VirtualItemFrame {
    Generated_decoration_ItemFrame(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public org.bukkit.inventory.ItemStack getItem() {
        return ValueConversions.item(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.decoration.ItemFrame.DATA_ITEM));
    }

    @Override
    public void setItem(org.bukkit.inventory.ItemStack value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.decoration.ItemFrame.DATA_ITEM, ValueConversions.item(value));
    }

    @Override
    public int getRotation() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.decoration.ItemFrame.DATA_ROTATION);
    }

    @Override
    public void setRotation(int value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.decoration.ItemFrame.DATA_ROTATION, value);
    }

    @Override
    public org.bukkit.block.BlockFace getFacing() {
        return getDirection();
    }

    @Override
    public void setFacing(org.bukkit.block.BlockFace value) {
        setDirection(value);
        setSpawnData(ValueConversions.direction(value).get3DDataValue());
    }
}
