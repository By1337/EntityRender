package dev.by1337.entityrender.core;

import com.mojang.datafixers.util.Pair;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.item.ItemStack;
import org.bukkit.World;
import org.bukkit.entity.Player;
import java.util.*;

/** Immutable transport input; no consumer reads an entity's working metadata or coordinates. */
record PublishedEntity(BaseVirtualEntity handle, int id, UUID uuid, World world,
    org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType,
    Position position, int spawnData, long respawn, boolean headChanged,
    List<SynchedEntityData.DataValue<?>> metadata, List<SynchedEntityData.DataValue<?>> changed,
    List<Pair<net.minecraft.world.entity.EquipmentSlot, ItemStack>> equipment, boolean equipmentChanged,
    Map<UUID, Player> requested, List<Signal> signals, boolean closed) {
    sealed interface Signal {
        record Effect(byte data) implements Signal {
        }

        record Swing(byte data) implements Signal {
        }

        record Hurt(float yaw) implements Signal {
        }
    }

    record Position(double x, double y, double z, float yaw, float pitch, boolean onGround) {
    }
}
