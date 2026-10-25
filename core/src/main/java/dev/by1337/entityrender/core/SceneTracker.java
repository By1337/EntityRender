package dev.by1337.entityrender.core;

import dev.by1337.entityrender.api.ViewerTracker;
import dev.by1337.entityrender.api.entity.VirtualEntity;
import org.bukkit.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

final class SceneTracker implements ViewerTracker {
    record Settings(Location center, double range) {
    }

    record Snapshot(Settings settings, Set<Integer> members) {
    }

    private final PaperEntityRenderer owner;
    private final Map<Integer, BaseVirtualEntity> members = new ConcurrentHashMap<>();
    private final AtomicReference<Settings> settings;
    private final AtomicBoolean closed = new AtomicBoolean();

    SceneTracker(PaperEntityRenderer owner, Location center, double range) {
        this.owner = owner;
        BaseVirtualEntity.validateLocation(center);
        validateRange(range);
        settings = new AtomicReference<>(new Settings(center.clone(), range));
    }

    private void checkOpen() {
        owner.checkOpen();
        if (closed.get()) throw new IllegalStateException("Tracker is closed");
    }

    @Override
    public synchronized void add(VirtualEntity entity) {
        checkOpen();
        if (!(entity instanceof BaseVirtualEntity base) || base.owner != owner || getCenter().getWorld() != entity.getWorld() || entity.isClosed()) throw new IllegalArgumentException("Tracker requires an open entity from the same renderer and world");
        owner.attachTracker(base, this);
        members.put(entity.getId(), base);
        base.clearRequested();
        // Close racing registration must not leave a closed member behind.
        if (base.isClosed()) {
            members.remove(base.getId());
            owner.detachTracker(base, this);
        }
    }

    @Override
    public synchronized void remove(VirtualEntity entity) {
        checkOpen();
        var base = members.remove(entity.getId());
        if (base != null) {
            owner.detachTracker(base, this);
            base.clearRequested();
        }
    }

    synchronized void detachClosedEntity(int id) {
        members.remove(id);
    }

    Snapshot snapshot() {
        return new Snapshot(settings.get(), Set.copyOf(members.keySet()));
    }

    @Override
    public Location getCenter() {
        checkOpen();
        return settings.get().center().clone();
    }

    @Override
    public void setCenter(Location center) {
        checkOpen();
        BaseVirtualEntity.validateLocation(center);
        settings.updateAndGet(old -> {
            if (old.center().getWorld() != center.getWorld()) throw new IllegalArgumentException("Tracker must stay in its world");
            return new Settings(center.clone(), old.range());
        });
    }

    @Override
    public double getRange() {
        checkOpen();
        return settings.get().range();
    }

    @Override
    public void setRange(double value) {
        checkOpen();
        validateRange(value);
        settings.updateAndGet(old -> new Settings(old.center(), value));
    }

    private static void validateRange(double value) {
        if (!Double.isFinite(value) || value < 0) throw new IllegalArgumentException("Range must be finite and nonnegative");
    }

    @Override
    public int size() {
        return members.size();
    }

    @Override
    public synchronized void close() {
        if (!closed.compareAndSet(false, true)) return;
        for (var base : members.values()) {
            owner.detachTracker(base, this);
            base.clearRequested();
        }
        members.clear();
        owner.forgetTracker(this);
    }
}
