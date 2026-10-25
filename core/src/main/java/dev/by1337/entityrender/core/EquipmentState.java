package dev.by1337.entityrender.core;

import com.mojang.datafixers.util.Pair;
import net.minecraft.network.protocol.game.ClientboundSetEquipmentPacket;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.*;
import java.util.*;

/** Allocated lazily only when equipment is used. */
final class EquipmentState {
    private static final net.minecraft.world.entity.EquipmentSlot[] SLOTS = net.minecraft.world.entity.EquipmentSlot.values();
    private final net.minecraft.world.item.ItemStack[] items = new net.minecraft.world.item.ItemStack[SLOTS.length];
    private int dirty;

    EquipmentState() {
        Arrays.fill(items, net.minecraft.world.item.ItemStack.EMPTY);
    }

    private static int index(EquipmentSlot slot) {
        String name = switch (Objects.requireNonNull(slot)) {
            case HAND -> "MAINHAND";
            case OFF_HAND -> "OFFHAND";
            default -> slot.name();
        };
        return net.minecraft.world.entity.EquipmentSlot.valueOf(name).ordinal();
    }

    ItemStack get(EquipmentSlot slot) {
        var value = items[index(slot)];
        return value.isEmpty() ? null : CraftItemStack.asBukkitCopy(value);
    }

    boolean set(EquipmentSlot slot, ItemStack item) {
        int index = index(slot);
        var value = item == null ? net.minecraft.world.item.ItemStack.EMPTY : CraftItemStack.asNMSCopy(item);
        if (net.minecraft.world.item.ItemStack.matches(items[index], value)) return false;
        items[index] = value;
        dirty |= 1 << index;
        return true;
    }

    ClientboundSetEquipmentPacket packet(int entity, boolean full) {
        var list = new ArrayList<Pair<net.minecraft.world.entity.EquipmentSlot, net.minecraft.world.item.ItemStack>>();
        for (int i = 0; i < items.length; i++) if (full ? !items[i].isEmpty() : (dirty & 1 << i) != 0) list.add(Pair.of(SLOTS[i], items[i].copy()));
        return list.isEmpty() ? null : new ClientboundSetEquipmentPacket(entity, List.copyOf(list));
    }

    void acknowledge() {
        dirty = 0;
    }
}
