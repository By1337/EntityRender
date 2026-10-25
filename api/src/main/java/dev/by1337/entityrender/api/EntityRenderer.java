package dev.by1337.entityrender.api;

import dev.by1337.entityrender.api.entity.*;
import org.bukkit.Location;

/** One owner for virtual entities. All operations are confined to its server thread. */
public interface EntityRenderer extends AutoCloseable {
    VirtualArmorStand createArmorStand(Location location);

    VirtualTextDisplay createTextDisplay(Location location);

    VirtualItemDisplay createItemDisplay(Location location);

    VirtualBlockDisplay createBlockDisplay(Location location);

    /** Sends accumulated changes once per entity. Also called by the optional automatic ticker. */
    void flush();

    int size();

    boolean isClosed();

    @Override
    void close();
}
