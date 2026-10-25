package dev.by1337.entityrender.core;

import dev.by1337.entityrender.api.VillagerAppearance;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.*;
import net.minecraft.resources.*;
import net.minecraft.world.entity.npc.VillagerData;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.CraftRegistry;
import org.bukkit.craftbukkit.block.data.CraftBlockData;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BlockVector;
import java.util.*;

final class ValueConversions {
    static <B extends org.bukkit.Keyed, M> Holder<M> holder(B value, ResourceKey<? extends Registry<M>> registry) {
        return CraftRegistry.bukkitToMinecraftHolder(value);
    }

    private ValueConversions() {
    }

    static ItemStack item(net.minecraft.world.item.ItemStack value) {
        return CraftItemStack.asBukkitCopy(value);
    }

    static net.minecraft.world.item.ItemStack item(ItemStack value) {
        return value == null ? net.minecraft.world.item.ItemStack.EMPTY : CraftItemStack.asNMSCopy(value);
    }

    static BlockData block(net.minecraft.world.level.block.state.BlockState value) {
        return CraftBlockData.createData(value);
    }

    static net.minecraft.world.level.block.state.BlockState block(BlockData value) {
        return ((CraftBlockData) Objects.requireNonNull(value)).getState();
    }

    static BlockVector position(BlockPos value) {
        return new BlockVector(value.getX(), value.getY(), value.getZ());
    }

    static BlockPos position(BlockVector value) {
        Objects.requireNonNull(value);
        value.checkFinite();
        return new BlockPos(value.getBlockX(), value.getBlockY(), value.getBlockZ());
    }

    static BlockFace direction(Direction value) {
        return BlockFace.valueOf(value.name());
    }

    static Direction direction(BlockFace value) {
        return switch (Objects.requireNonNull(value)) {
            case DOWN -> Direction.DOWN;
            case UP -> Direction.UP;
            case NORTH -> Direction.NORTH;
            case SOUTH -> Direction.SOUTH;
            case WEST -> Direction.WEST;
            case EAST -> Direction.EAST;
            default -> throw new IllegalArgumentException("Direction must be an axis-aligned face");
        };
    }

    static VillagerAppearance villager(VillagerData value) {
        return new VillagerAppearance(CraftRegistry.minecraftHolderToBukkit(value.type(), Registries.VILLAGER_TYPE), CraftRegistry.minecraftHolderToBukkit(value.profession(), Registries.VILLAGER_PROFESSION), value.level());
    }

    static VillagerData villager(VillagerAppearance value) {
        Objects.requireNonNull(value);
        return new VillagerData(holder(value.type(), Registries.VILLAGER_TYPE), holder(value.profession(), Registries.VILLAGER_PROFESSION), value.level());
    }

    static String compound(CompoundTag value) {
        return value.toString();
    }

    static CompoundTag compound(String value) {
        try {
            return TagParser.parseCompoundFully(Objects.requireNonNull(value));
        } catch (com.mojang.brigadier.exceptions.CommandSyntaxException e) {
            throw new IllegalArgumentException("Invalid SNBT", e);
        }
    }

    static <T> String encode(com.mojang.serialization.Codec<T> codec, T value, RegistryAccess registries) {
        return codec.encodeStart(RegistryOps.create(NbtOps.INSTANCE, registries), value).getOrThrow().toString();
    }

    static <T> T decode(com.mojang.serialization.Codec<T> codec, String value, RegistryAccess registries) {
        try {
            return codec.parse(RegistryOps.create(NbtOps.INSTANCE, registries), parseSnbt(value)).getOrThrow();
        } catch (com.mojang.brigadier.exceptions.CommandSyntaxException e) {
            throw new IllegalArgumentException("Invalid SNBT", e);
        }
    }

    private static Tag parseSnbt(String text) throws com.mojang.brigadier.exceptions.CommandSyntaxException {
        return TagParser.create(NbtOps.INSTANCE).parseFully(Objects.requireNonNull(text));
    }
}
