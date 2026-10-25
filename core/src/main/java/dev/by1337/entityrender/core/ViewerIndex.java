package dev.by1337.entityrender.core;

import org.bukkit.Location;
import java.util.*;

/** Shared per-world spatial index, built once for one player sample, never per renderer. */
final class ViewerIndex {
    private final List<PlayerDirectory.Sample> all = new ArrayList<>();
    private final Map<Long, List<PlayerDirectory.Sample>> cells = new HashMap<>();

    void add(PlayerDirectory.Sample sample) {
        all.add(sample);
        cells.computeIfAbsent(cell(sample.x(), sample.z()), ignored -> new ArrayList<>()).add(sample);
    }

    List<PlayerDirectory.Sample> nearby(Location center, double range) {
        if (range > 128 || Math.abs(center.getX()) > 30000000 || Math.abs(center.getZ()) > 30000000) return all;
        int lowX = (int) Math.floor((center.getX() - range) / 32), highX = (int) Math.floor((center.getX() + range) / 32);
        int lowZ = (int) Math.floor((center.getZ() - range) / 32), highZ = (int) Math.floor((center.getZ() + range) / 32);
        if ((long) (highX - lowX + 1) * (highZ - lowZ + 1) >= all.size()) return all;
        var result = new ArrayList<PlayerDirectory.Sample>();
        for (int x = lowX; x <= highX; x++) for (int z = lowZ; z <= highZ; z++) result.addAll(cells.getOrDefault(key(x, z), List.of()));
        return result;
    }

    private static long cell(double x, double z) {
        return key((int) Math.floor(x / 32), (int) Math.floor(z / 32));
    }

    private static long key(int x, int z) {
        return (long) x << 32 ^ (z & 0xffffffffL);
    }
}
