package dev.by1337.entityrender.core;

class Generated_item_ItemEntity extends BaseVirtualEntity implements dev.by1337.entityrender.api.entity.item.VirtualItemEntity {
    Generated_item_ItemEntity(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public org.bukkit.inventory.ItemStack getItem() {
        return ValueConversions.item(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.item.ItemEntity.DATA_ITEM));
    }

    @Override
    public void setItem(org.bukkit.inventory.ItemStack value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.item.ItemEntity.DATA_ITEM, ValueConversions.item(value));
    }
}
