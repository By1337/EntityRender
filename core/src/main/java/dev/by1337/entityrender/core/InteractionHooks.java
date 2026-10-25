package dev.by1337.entityrender.core;

import dev.by1337.entityrender.api.EntityInteraction;
import io.netty.channel.*;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import net.minecraft.network.protocol.game.ServerboundAttackPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.Vec3;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.util.Vector;
import java.util.*;
import java.util.concurrent.Executor;

final class InteractionHooks implements AutoCloseable {
    private final PaperEntityRenderer owner;
    private final Executor serverThread;
    private final String name = "entityrender-" + UUID.randomUUID();
    private final Map<UUID, Channel> channels = new HashMap<>();
    private volatile boolean closed;

    InteractionHooks(PaperEntityRenderer owner, Executor serverThread) {
        this.owner = owner;
        this.serverThread = serverThread;
    }

    void bind(Player player, Channel channel) {
        owner.checkThread();
        if (closed) return;
        unbind(player.getUniqueId());
        channels.put(player.getUniqueId(), channel);
        channel.eventLoop().execute(() -> {
            if (closed || !channel.isOpen()) return;
            channel.pipeline().addBefore("packet_handler", name, new ChannelInboundHandlerAdapter() {
                @Override
                public void channelRead(ChannelHandlerContext context, Object message) throws Exception {
                    if (!closed && message instanceof ServerboundAttackPacket packet && owner.containsEntity(packet.entityId())) {
                        serverThread.execute(() -> {
                            if (!closed) owner.dispatchInteraction(player, packet.entityId(), EntityInteraction.Type.ATTACK, null, null, player.isSneaking());
                        });
                        return;
                    }
                    if (!closed && message instanceof ServerboundInteractPacket packet && owner.containsEntity(packet.entityId())) {
                        Vec3 hit = packet.location();
                        if (hit == null || !Double.isFinite(hit.x) || !Double.isFinite(hit.y) || !Double.isFinite(hit.z)) return;
                        EquipmentSlot slot = packet.hand() == InteractionHand.MAIN_HAND ? EquipmentSlot.HAND : EquipmentSlot.OFF_HAND;
                        Vector point = new Vector(hit.x, hit.y, hit.z);
                        serverThread.execute(() -> {
                            if (!closed) owner.dispatchInteraction(player, packet.entityId(), EntityInteraction.Type.INTERACT_AT, slot, point, packet.usingSecondaryAction());
                        });
                        return;
                    }
                    super.channelRead(context, message);
                }
            });
        });
    }

    void unbind(UUID player) {
        owner.checkThread();
        Channel channel = channels.remove(player);
        if (channel == null || channel.eventLoop().isShuttingDown()) return;
        channel.eventLoop().execute(() -> {
            if (channel.pipeline().get(name) != null) channel.pipeline().remove(name);
        });
    }

    @Override
    public void close() {
        owner.checkThread();
        if (closed) return;
        closed = true;
        for (UUID player : List.copyOf(channels.keySet())) unbind(player);
    }
}
