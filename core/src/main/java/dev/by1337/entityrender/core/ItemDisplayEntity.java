package dev.by1337.entityrender.core;

import dev.by1337.entityrender.api.entity.VirtualItemDisplay;
import net.minecraft.world.item.ItemDisplayContext;
import org.bukkit.Location;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import java.util.Objects;
import dev.by1337.entityrender.core.metadata.GeneratedMetadata;

final class ItemDisplayEntity extends AbstractDisplayEntity implements VirtualItemDisplay {
    ItemDisplayEntity(PaperEntityRenderer owner, int id, Location location) {
        super(owner, id, location, EntityType.ITEM_DISPLAY, net.minecraft.world.entity.EntityType.ITEM_DISPLAY, GeneratedMetadata.Display.ItemDisplay.LAYOUT);
    }

    @Override
    public ItemStack getItemStack() {
        var item = get(GeneratedMetadata.Display.ItemDisplay.DATA_ITEM_STACK_ID);
        return item.isEmpty() ? null : CraftItemStack.asBukkitCopy(item);
    }

    @Override
    public void setItemStack(ItemStack item) {
        checkOpen();
        var snapshot = item == null ? net.minecraft.world.item.ItemStack.EMPTY : CraftItemStack.asNMSCopy(item);
        if (!net.minecraft.world.item.ItemStack.matches(get(GeneratedMetadata.Display.ItemDisplay.DATA_ITEM_STACK_ID), snapshot)) set(GeneratedMetadata.Display.ItemDisplay.DATA_ITEM_STACK_ID, snapshot);
    }

    private static ItemDisplayContext context(ItemDisplay.ItemDisplayTransform transform) {
        return switch (Objects.requireNonNull(transform)) {
            case NONE -> ItemDisplayContext.NONE;
            case THIRDPERSON_LEFTHAND -> ItemDisplayContext.THIRD_PERSON_LEFT_HAND;
            case THIRDPERSON_RIGHTHAND -> ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
            case FIRSTPERSON_LEFTHAND -> ItemDisplayContext.FIRST_PERSON_LEFT_HAND;
            case FIRSTPERSON_RIGHTHAND -> ItemDisplayContext.FIRST_PERSON_RIGHT_HAND;
            case HEAD -> ItemDisplayContext.HEAD;
            case GUI -> ItemDisplayContext.GUI;
            case GROUND -> ItemDisplayContext.GROUND;
            case FIXED -> ItemDisplayContext.FIXED;
        };
    }

    @Override
    public void setItemDisplayTransform(ItemDisplay.ItemDisplayTransform transform) {
        set(GeneratedMetadata.Display.ItemDisplay.DATA_ITEM_DISPLAY_ID, context(transform).getId());
    }

    @Override
    public ItemDisplay.ItemDisplayTransform getItemDisplayTransform() {
        byte id = get(GeneratedMetadata.Display.ItemDisplay.DATA_ITEM_DISPLAY_ID);
        for (var transform : ItemDisplay.ItemDisplayTransform.values()) if (context(transform).getId() == id) return transform;
        throw new IllegalStateException("Item display context has no Bukkit equivalent: " + id);
    }
}
