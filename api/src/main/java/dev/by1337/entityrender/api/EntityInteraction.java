package dev.by1337.entityrender.api;

import dev.by1337.entityrender.api.entity.VirtualEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.util.Vector;
import java.util.Objects;

/** Delivered on the owning server thread; hand/hitPosition are null when absent in the packet. */
public record EntityInteraction(VirtualEntity entity, Player player, Type type, EquipmentSlot hand, Vector hitPosition, boolean secondaryAction) {
    public enum Type {
        ATTACK, INTERACT, INTERACT_AT
    }

    public EntityInteraction {
        Objects.requireNonNull(entity);
        Objects.requireNonNull(player);
        Objects.requireNonNull(type);
        hitPosition = hitPosition == null ? null : hitPosition.clone();
    }

    @Override
    public Vector hitPosition() {
        return hitPosition == null ? null : hitPosition.clone();
    }
}
