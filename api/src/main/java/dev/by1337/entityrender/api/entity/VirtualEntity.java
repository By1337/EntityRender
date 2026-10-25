package dev.by1337.entityrender.api.entity;

import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;
import java.util.*;

/** Client-only entity: no AI, physics, world registration or server-side collision. */
public interface VirtualEntity extends AutoCloseable {
    int getId();

    UUID getUniqueId();

    EntityType getType();

    World getWorld();

    /** Returned mutable values are independent copies. Inputs are never retained or modified. */
    Location getLocation();

    Vector getPosition();

    void setPosition(Vector position);

    void setPosition(double x, double y, double z);

    /** Same-world teleport; use another entity to move between worlds. */
    void teleport(Location location);

    void setRotation(float yaw, float pitch);

    boolean isOnGround();

    void setOnGround(boolean onGround);

    Component getCustomName();

    /** null clears the custom name. */
    void setCustomName(Component name);

    boolean isCustomNameVisible();

    void setCustomNameVisible(boolean visible);

    boolean isInvisible();

    void setInvisible(boolean invisible);

    boolean isGlowing();

    void setGlowing(boolean glowing);

    boolean isSilent();

    void setSilent(boolean silent);

    boolean hasGravity();

    void setGravity(boolean gravity);

    Pose getPose();

    void setPose(Pose pose);

    /** Idempotent. A new viewer receives a full snapshot without consuming pending changes. */
    void show(Player player);

    void hide(Player player);

    Set<UUID> getViewers();

    boolean isClosed();

    /** Idempotent. Removes this entity from all viewers and its owning renderer. */
    @Override
    void close();
}
