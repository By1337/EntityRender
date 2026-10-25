package dev.by1337.entityrender.core;

import net.minecraft.network.protocol.Packet;
import org.bukkit.entity.Player;

@FunctionalInterface
interface PacketSink {
    void send(Player player, Packet<?> packet);
}
