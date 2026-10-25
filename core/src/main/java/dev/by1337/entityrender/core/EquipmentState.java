package dev.by1337.entityrender.core;

import com.mojang.datafixers.util.Pair;
import net.minecraft.world.item.ItemStack;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.EquipmentSlot;
import java.util.*;
import java.util.concurrent.atomic.*;

/** Inputs are detached once; owned native stacks are never modified after installation. */
final class EquipmentState {
    private static final net.minecraft.world.entity.EquipmentSlot[] SLOTS = net.minecraft.world.entity.EquipmentSlot.values();
    private final AtomicReferenceArray<ItemStack> items = new AtomicReferenceArray<>(SLOTS.length);
    private final AtomicInteger dirty = new AtomicInteger();
    private final AtomicInteger used = new AtomicInteger();

    EquipmentState() {
        for (int i = 0; i < items.length(); i++) items.set(i, ItemStack.EMPTY);
    }

    private static int index(EquipmentSlot slot) {
        String name = switch (Objects.requireNonNull(slot)) {
            case HAND -> "MAINHAND";
            case OFF_HAND -> "OFFHAND";
            default -> slot.name();
        };
        return net.minecraft.world.entity.EquipmentSlot.valueOf(name).ordinal();
    }

    org.bukkit.inventory.ItemStack get(EquipmentSlot slot) {
        var value = items.get(index(slot));
        return value.isEmpty() ? null : CraftItemStack.asBukkitCopy(value);
    }

    boolean set(EquipmentSlot slot, org.bukkit.inventory.ItemStack item) {
        int index = index(slot);
        var value = item == null ? ItemStack.EMPTY : CraftItemStack.asNMSCopy(item);
        var before = items.getAndSet(index, value);
        if (ItemStack.matches(before, value)) return false;
        used.getAndUpdate(mask -> mask | 1 << index);
        dirty.getAndUpdate(mask -> mask | 1 << index);
        return true;
    }

    boolean drain() {
        return dirty.getAndSet(0) != 0;
    }

    List<Pair<net.minecraft.world.entity.EquipmentSlot, ItemStack>> snapshot() {
        var result = new ArrayList<Pair<net.minecraft.world.entity.EquipmentSlot, ItemStack>>(items.length());
        int mask = used.get();
        for (int i = 0; i < items.length(); i++) if ((mask & 1 << i) != 0) result.add(Pair.of(SLOTS[i], items.get(i)));
        return List.copyOf(result);
    }
}
