package dev.by1337.entityrender.core;

import dev.by1337.entityrender.api.ViewerTracker;
import dev.by1337.entityrender.api.entity.VirtualEntity;
import org.bukkit.*;
import org.bukkit.entity.Player;
import java.util.*;

final class SceneTracker implements ViewerTracker {
    private final PaperEntityRenderer owner;
    private final Map<Integer, BaseVirtualEntity> members = new HashMap<>();
    private Map<UUID, Player> seen = new HashMap<>(), next = new HashMap<>();
    private Location center;
    private double range;
    private boolean closed;

    SceneTracker(PaperEntityRenderer owner, Location center, double range) {
        this.owner = owner;
        BaseVirtualEntity.validateLocation(center);
        this.center = center.clone();
        setRange(range);
    }

    private void checkOpen() {
        owner.checkOpen();
        if (closed) throw new IllegalStateException("Tracker is closed");
    }

    @Override
    public void add(VirtualEntity entity) {
        checkOpen();
        if (!(entity instanceof BaseVirtualEntity base) || base.owner != owner || !center.getWorld().equals(entity.getWorld()) || entity.isClosed()) throw new IllegalArgumentException("Tracker requires an open entity from the same renderer and world");
        owner.attachTracker(base, this);
        members.putIfAbsent(entity.getId(), base);
    }

    @Override
    public void remove(VirtualEntity entity) {
        checkOpen();
        var base = members.remove(entity.getId());
        if (base == null) return;
        owner.detachTracker(base, this);
        for (var player : seen.values()) base.hide(player);
    }

    void detachClosedEntity(int id) {
        members.remove(id);
    }

    void forgetViewer(UUID id) {
        seen.remove(id);
        next.remove(id);
    }

    World world() {
        return center.getWorld();
    }

    void update(ViewerIndex index) {
        next.clear();
        double squared = range * range;
        for (var player : index.nearby(center, range)) {
            double x = player.x() - center.getX(), y = player.y() - center.getY(), z = player.z() - center.getZ();
            double distance = x * x + y * y + z * z;
            boolean inside = Double.isInfinite(distance) || Double.isInfinite(squared) ? Math.hypot(Math.hypot(x, z), y) <= range : distance <= squared;
            if (inside) next.put(player.id(), player.player());
        }
        for (var old : seen.entrySet()) if (!next.containsKey(old.getKey())) for (var entity : members.values()) entity.hide(old.getValue());
        for (var current : next.entrySet()) for (var entity : members.values()) if (!entity.hasViewer(current.getKey())) entity.show(current.getValue());
        var previous = seen;
        seen = next;
        next = previous;
    }

    @Override
    public Location getCenter() {
        checkOpen();
        return center.clone();
    }

    @Override
    public void setCenter(Location value) {
        checkOpen();
        BaseVirtualEntity.validateLocation(value);
        if (!center.getWorld().equals(value.getWorld())) throw new IllegalArgumentException("Tracker center must remain in its world");
        center = value.clone();
    }

    @Override
    public double getRange() {
        checkOpen();
        return range;
    }

    @Override
    public void setRange(double value) {
        checkOpen();
        if (!Double.isFinite(value) || value < 0) throw new IllegalArgumentException("Range must be finite and nonnegative");
        range = value;
    }

    @Override
    public int size() {
        checkOpen();
        return members.size();
    }

    @Override
    public void close() {
        owner.checkThread();
        if (closed) return;
        closed = true;
        RuntimeException failure = null;
        for (var entity : members.values()) {
            owner.detachTracker(entity, this);
            if (!owner.isClosed()) for (var player : seen.values()) try {
                entity.hide(player);
            } catch (RuntimeException e) {
                if (failure == null) failure = e;
                else failure.addSuppressed(e);
            }
        }
        members.clear();
        seen.clear();
        next.clear();
        owner.forgetTracker(this);
        if (failure != null) throw failure;
    }
}
