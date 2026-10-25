package dev.by1337.entityrender.core;

import org.bukkit.*;
import org.bukkit.entity.Player;
import java.util.*;

/** Owned exclusively by the transport worker. Bukkit state is supplied as main-thread samples. */
final class PlayerDirectory {
    record Sample(Player player, UUID id, World world, double x, double y, double z, long epoch, Set<Long> initialChunks,
        java.util.function.Consumer<net.minecraft.network.protocol.Packet<?>> sender, java.util.concurrent.atomic.AtomicBoolean valid) {
        Sample(Player player, UUID id, World world, double x, double y, double z, long epoch, Set<Long> chunks) {
            this(player, id, world, x, y, z, epoch, chunks, null, new java.util.concurrent.atomic.AtomicBoolean(true));
        }
    }

    static final class View {
        Sample sample;
        final Set<Long> chunks;
        final boolean unrestricted;

        View(Sample sample) {
            this.sample = sample;
            unrestricted = sample.initialChunks() == null;
            chunks = unrestricted ? new HashSet<>() : new HashSet<>(sample.initialChunks());
        }

        boolean ready(PublishedEntity entity) {
            return sample.valid().get() && sample.world() == entity.world() && (unrestricted || chunks.contains(chunk(entity.position().x(), entity.position().z())));
        }
    }

    private final Map<UUID, View> players = new HashMap<>();
    private final Map<Player, View> routes = new IdentityHashMap<>();
    private final Map<World, ViewerIndex> indices = new IdentityHashMap<>();

    void replace(List<Sample> samples) {
        var present = new HashSet<UUID>();
        for (var sample : samples) {
            present.add(sample.id());
            var old = players.get(sample.id());
            if (old == null || old.sample.epoch() != sample.epoch()) players.put(sample.id(), new View(sample));
            else old.sample = sample;
        }
        players.keySet().retainAll(present);
        rebuild();
    }

    void ensure(Sample sample) {
        var old = players.get(sample.id());
        if (old == null || old.sample.epoch() != sample.epoch()) {
            players.put(sample.id(), new View(sample));
            rebuild();
        }
    }

    void remove(UUID id) {
        players.remove(id);
        rebuild();
    }

    void chunk(UUID id, long epoch, long chunk, boolean loaded) {
        var view = players.get(id);
        if (view == null || view.sample.epoch() != epoch) return;
        if (loaded) view.chunks.add(chunk);
        else view.chunks.remove(chunk);
    }

    void rebuild() {
        indices.clear();
        routes.clear();
        for (var view : players.values()) {
            routes.put(view.sample.player(), view);
            indices.computeIfAbsent(view.sample.world(), ignored -> new ViewerIndex()).add(view.sample);
        }
    }

    View route(Player player) {
        return routes.get(player);
    }

    View get(UUID id) {
        return players.get(id);
    }

    ViewerIndex index(World world) {
        return indices.get(world);
    }

    static long chunk(double x, double z) {
        return key(((int) Math.floor(x)) >> 4, ((int) Math.floor(z)) >> 4);
    }

    static long key(int x, int z) {
        return (x & 0xffffffffL) | (long) z << 32;
    }
}
