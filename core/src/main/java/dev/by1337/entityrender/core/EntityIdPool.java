package dev.by1337.entityrender.core;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

/** Main-thread reservation, lock-free allocation of contiguous IDs by arbitrary producers. */
final class EntityIdPool {
    private static final int BLOCK = 4096, LOW_WATER = 512;
    private final RenderService service;
    private final AtomicInteger counter;
    private final ConcurrentLinkedQueue<Range> available = new ConcurrentLinkedQueue<>();
    private final AtomicReference<Range> current = new AtomicReference<>();
    private final AtomicBoolean refillScheduled = new AtomicBoolean();

    EntityIdPool(RenderService service) {
        this.service = service;
        try {
            var field = net.minecraft.server.level.ServerLevel.class.getDeclaredField("ENTITY_COUNTER");
            field.setAccessible(true);
            counter = (AtomicInteger) field.get(null);
        } catch (ReflectiveOperationException error) {
            throw new IllegalStateException("Cannot access the version-specific NMS entity allocator", error);
        }
        current.set(reserve());
        available.add(reserve());
    }

    int take(int count) {
        if (count < 1 || count > BLOCK) throw new IllegalArgumentException("Invalid ID block size");
        while (true) {
            service.checkOpen();
            var range = current.get();
            int first = range.next.getAndAdd(count);
            if (first >= range.first && first <= range.last - count + 1) {
                if (range.last - first < LOW_WATER) refill();
                return first;
            }
            synchronized (this) {
                if (current.get() != range) continue;
                var next = available.poll();
                if (next != null) current.set(next);
            }
            if (current.get() == range) {
                // Never wait for main while holding a monitor: main may also allocate entities.
                Range fresh = service.onMain(this::reserve).join();
                available.add(fresh);
            }
        }
    }

    private void refill() {
        if (!available.isEmpty() || !refillScheduled.compareAndSet(false, true)) return;
        service.onMain(() -> {
            if (available.isEmpty()) available.add(reserve());
            return null;
        })
                .whenComplete((ignored, error) -> refillScheduled.set(false));
    }

    private Range reserve() {
        if (!org.bukkit.Bukkit.isPrimaryThread()) throw new IllegalStateException("Reserve IDs on main");
        while (true) {
            int before;
            do {
                before = counter.get();
                if (before < 0 || before > Integer.MAX_VALUE - BLOCK) throw new IllegalStateException("Positive entity ID space exhausted; refusing to wrap into live IDs");
            } while (!counter.compareAndSet(before, before + BLOCK));
            boolean collision = false;
            for (var world : org.bukkit.Bukkit.getWorlds()) {
                var chunks = ((org.bukkit.craftbukkit.CraftWorld) world).getHandle().getChunkSource();
                for (int offset = 1; offset <= BLOCK; offset++) if (chunks.hasEntityWithId(before + offset)) {
                    collision = true;
                    break;
                }
                if (collision) break;
            }
            if (!collision) return new Range(before + 1, before + BLOCK);
        }
    }

    private static final class Range {
        final AtomicInteger next;
        final int first, last;

        Range(int first, int last) {
            this.first = first;
            next = new AtomicInteger(first);
            this.last = last;
        }
    }
}
