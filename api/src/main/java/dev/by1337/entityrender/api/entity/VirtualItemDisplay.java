package dev.by1337.entityrender.api.entity;

import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;

public interface VirtualItemDisplay extends VirtualDisplay {
    /** Independent copy, or null for empty. */
    ItemStack getItemStack();

    /** null clears the display. */
    void setItemStack(ItemStack item);

    ItemDisplay.ItemDisplayTransform getItemDisplayTransform();

    void setItemDisplayTransform(ItemDisplay.ItemDisplayTransform transform);
}
