package dev.by1337.entityrender.core;

class Generated_animal_turtle_Turtle extends Generated_animal_Animal implements dev.by1337.entityrender.api.entity.animal.turtle.VirtualTurtle {
    Generated_animal_turtle_Turtle(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isHasEgg() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.turtle.Turtle.HAS_EGG);
    }

    @Override
    public void setHasEgg(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.turtle.Turtle.HAS_EGG, value);
    }

    @Override
    public boolean isLayingEgg() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.turtle.Turtle.LAYING_EGG);
    }

    @Override
    public void setLayingEgg(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.turtle.Turtle.LAYING_EGG, value);
    }
}
