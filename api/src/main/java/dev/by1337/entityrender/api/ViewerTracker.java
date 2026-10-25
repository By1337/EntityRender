package dev.by1337.entityrender.api;

import dev.by1337.entityrender.api.entity.VirtualEntity;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import java.util.Iterator;
import java.util.Set;

/**
 * One scene/group shares a visibility calculation. Methods may run asynchronously;
 * membership and settings are applied when the owning renderer publishes.
 * An entity belongs to at most one tracker. Closing a tracker does not close its entities.
 */
public interface ViewerTracker extends AutoCloseable, Iterable<Player> {
    void add(VirtualEntity entity);

    void remove(VirtualEntity entity);

    Location getCenter();

    void setCenter(Location center);

    double getRange();

    void setRange(double range);

    int size();

    /**
     * Last computed immutable snapshot of players in this scene's world and radius.
     * Reused until audience membership or player sessions change.
     * Available even without member entities; initially empty and empty after close.
     * May be read asynchronously. It can lag player movement/session changes by a tick;
     * individual entities additionally require their chunk to be sent to the player.
     * Iteration does not make arbitrary Bukkit operations safe on the calling thread.
     */
    Set<Player> getViewers();

    /** Iterates one stable snapshot; removal through the iterator is unsupported. */
    @Override
    default Iterator<Player> iterator() {
        return getViewers().iterator();
    }

    @Override
    void close();
}
