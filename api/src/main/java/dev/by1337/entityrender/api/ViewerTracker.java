package dev.by1337.entityrender.api;

import dev.by1337.entityrender.api.entity.VirtualEntity;
import org.bukkit.Location;

/** One scene/group shares a visibility calculation. An entity belongs to at most one tracker. */
public interface ViewerTracker extends AutoCloseable {
    void add(VirtualEntity entity);

    void remove(VirtualEntity entity);

    Location getCenter();

    void setCenter(Location center);

    double getRange();

    void setRange(double range);

    int size();

    @Override
    void close();
}
