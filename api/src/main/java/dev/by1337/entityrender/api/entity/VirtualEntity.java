package dev.by1337.entityrender.api.entity;

import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;
import java.util.*;

/**
 * Client-only entity: no AI, physics, world registration or server-side collision.
 * Methods may be called asynchronously. Editing does not imply network delivery: publish
 * through the owning renderer. Concurrent changes to different fields need not form one frame.
 */
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

    /**
     * Requests visibility for this player. Applied after publication, while the player's session,
     * world and received chunks allow it. The intent survives chunk reloads and respawn.
     */
    void show(Player player);

    /** Removes the manual visibility intent; applied on publication. */
    void hide(Player player);

    /** Queues a one-shot client effect for the next publication; late viewers do not replay it. */
    void playEffect(org.bukkit.EntityEffect effect);

    /**
     * Queues a one-shot hurt animation for the next publication on every supported version.
     * The damage direction yaw is in degrees and must be finite. Does not change health,
     * velocity or play a hurt sound. The client decides how this entity type renders it.
     * Late viewers do not replay the animation.
     */
    void hurtAnimation(float yaw);

    /** Queues a hurt animation with the default damage direction (zero degrees). */
    default void hurtAnimation() {
        hurtAnimation(0.0F);
    }

    /** Recreates the client representation using the current complete state. */
    void respawn();

    /** Replaces the interaction handler; null clears it. Invoked on the owning server thread. */
    void setInteractionHandler(java.util.function.Consumer<dev.by1337.entityrender.api.EntityInteraction> handler);

    /** Immutable snapshot of transport-admitted viewers, which can lag visibility requests. */
    Set<UUID> getViewers();

    boolean isClosed();

    /** Idempotent. Closes local state; client removal is delivered at the next publication. */
    @Override
    void close();
}
