package dev.by1337.entityrender.core;

class Generated_animal_sheep_Sheep extends Generated_animal_Animal implements dev.by1337.entityrender.api.entity.animal.sheep.VirtualSheep {
    Generated_animal_sheep_Sheep(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public byte getWool() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.sheep.Sheep.DATA_WOOL_ID);
    }

    @Override
    public void setWool(byte value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.sheep.Sheep.DATA_WOOL_ID, value);
    }

    @Override
    public boolean isSheared() {
        return flag(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.sheep.Sheep.DATA_WOOL_ID, 16);
    }

    @Override
    public void setSheared(boolean value) {
        flag(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.sheep.Sheep.DATA_WOOL_ID, 16, value);
    }

    @Override
    public org.bukkit.DyeColor getColor() {
        return org.bukkit.DyeColor.valueOf(net.minecraft.world.item.DyeColor.byId(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.sheep.Sheep.DATA_WOOL_ID) & 15).name());
    }

    @Override
    public void setColor(org.bukkit.DyeColor value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.sheep.Sheep.DATA_WOOL_ID, (byte) ((get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.sheep.Sheep.DATA_WOOL_ID) & ~15) | net.minecraft.world.item.DyeColor.valueOf(value.name()).getId()));
    }
}
