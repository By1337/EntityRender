package dev.by1337.entityrender.api;

import dev.by1337.entityrender.api.entity.*;
import org.bukkit.Location;

/**
 * Concurrent editing context for virtual entities. Initialize through EntityRender on main;
 * creation, mutation, visibility requests and publication may run on arbitrary threads.
 */
public interface EntityRenderer extends AutoCloseable, GeneratedEntityFactories {
    ViewerTracker createTracker(Location center, double range);

    VirtualArmorStand createArmorStand(Location location);

    VirtualTextDisplay createTextDisplay(Location location);

    VirtualItemDisplay createItemDisplay(Location location);

    VirtualBlockDisplay createBlockDisplay(Location location);

    /**
     * Publishes accumulated changes and visibility requests to the shared transport worker.
     * Does not wait for packet delivery. Automatic renderers publish once per server tick.
     * Concurrent writers may be observed in adjacent publications; this is not a scene transaction.
     */
    void flush();

    int size();

    boolean isClosed();

    @Override
    void close();
}
