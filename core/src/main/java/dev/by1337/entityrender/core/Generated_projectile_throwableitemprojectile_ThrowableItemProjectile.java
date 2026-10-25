package dev.by1337.entityrender.core;

class Generated_projectile_throwableitemprojectile_ThrowableItemProjectile extends Generated_projectile_ThrowableProjectile implements dev.by1337.entityrender.api.entity.projectile.throwableitemprojectile.VirtualThrowableItemProjectile {
    Generated_projectile_throwableitemprojectile_ThrowableItemProjectile(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public org.bukkit.inventory.ItemStack getItemStack() {
        return ValueConversions.item(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.projectile.throwableitemprojectile.ThrowableItemProjectile.DATA_ITEM_STACK));
    }

    @Override
    public void setItemStack(org.bukkit.inventory.ItemStack value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.projectile.throwableitemprojectile.ThrowableItemProjectile.DATA_ITEM_STACK, ValueConversions.item(value));
    }
}
