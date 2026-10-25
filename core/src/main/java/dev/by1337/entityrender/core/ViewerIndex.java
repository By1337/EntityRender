package dev.by1337.entityrender.core;

import org.bukkit.*;
import org.bukkit.entity.Player;
import java.util.*;
import java.util.function.Function;

/** Positions are sampled once per world/tick, shared by every scene. */
final class ViewerIndex {
    record Sample(Player player, UUID id, double x, double y, double z) {
    }

    private final List<Sample> all = new ArrayList<>();
    private final Map<Long, List<Sample>> cells = new HashMap<>();

    ViewerIndex(World world, Function<World, List<Player>> players) {
        for (Player player : players.apply(world)) {
            var location = player.getLocation();
            var sample = new Sample(player, player.getUniqueId(), location.getX(), location.getY(), location.getZ());
            all.add(sample);
            cells.computeIfAbsent(cell(location.getX(), location.getZ()), ignored -> new ArrayList<>()).add(sample);
        }
    }

    List<Sample> nearby(Location center, double range) {
        if (range > 128 || Math.abs(center.getX()) > 30000000 || Math.abs(center.getZ()) > 30000000) return all;
        int lowX = (int) Math.floor((center.getX() - range) / 32), highX = (int) Math.floor((center.getX() + range) / 32);
        int lowZ = (int) Math.floor((center.getZ() - range) / 32), highZ = (int) Math.floor((center.getZ() + range) / 32);
        if ((long) (highX - lowX + 1) * (highZ - lowZ + 1) >= all.size()) return all;
        var result = new ArrayList<Sample>();
        for (int x = lowX; x <= highX; x++) for (int z = lowZ; z <= highZ; z++) result.addAll(cells.getOrDefault(key(x, z), List.of()));
        return result;
    }

    private static long cell(double x, double z) {
        return key((int) Math.floor(x / 32), (int) Math.floor(z / 32));
    }

    private static long key(int x, int z) {
        return ((long) x << 32) ^ (z & 0xffffffffL);
    }
}
