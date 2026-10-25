package dev.by1337.entityrender.core;

class Generated_animal_Turtle extends Generated_animal_Animal implements dev.by1337.entityrender.api.entity.animal.VirtualTurtle {
    Generated_animal_Turtle(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {
        super(owner, id, location, type, nativeType, layout);
    }

    @Override
    public boolean isGoingHome() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Turtle.GOING_HOME);
    }

    @Override
    public void setGoingHome(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Turtle.GOING_HOME, value);
    }

    @Override
    public boolean isHasEgg() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Turtle.HAS_EGG);
    }

    @Override
    public void setHasEgg(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Turtle.HAS_EGG, value);
    }

    @Override
    public org.bukkit.util.BlockVector getHomePos() {
        return ValueConversions.position(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Turtle.HOME_POS));
    }

    @Override
    public void setHomePos(org.bukkit.util.BlockVector value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Turtle.HOME_POS, ValueConversions.position(value));
    }

    @Override
    public boolean isLayingEgg() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Turtle.LAYING_EGG);
    }

    @Override
    public void setLayingEgg(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Turtle.LAYING_EGG, value);
    }

    @Override
    public boolean isTravelling() {
        return get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Turtle.TRAVELLING);
    }

    @Override
    public void setTravelling(boolean value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Turtle.TRAVELLING, value);
    }

    @Override
    public org.bukkit.util.BlockVector getTravelPos() {
        return ValueConversions.position(get(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Turtle.TRAVEL_POS));
    }

    @Override
    public void setTravelPos(org.bukkit.util.BlockVector value) {
        set(dev.by1337.entityrender.core.metadata.GeneratedMetadata.animal.Turtle.TRAVEL_POS, ValueConversions.position(value));
    }
}
