package dev.by1337.entityrender.core;

import dev.by1337.entityrender.api.EntityInteraction;
import io.netty.channel.*;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
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
                    if (!closed && message instanceof ServerboundInteractPacket packet && owner.containsEntity(packet.getEntityId())) {
                        packet.dispatch(new ServerboundInteractPacket.Handler() {
                            @Override
                            public void onAttack() {
                                enqueue(EntityInteraction.Type.ATTACK, null, null);
                            }

                            @Override
                            public void onInteraction(InteractionHand hand) {
                                enqueue(EntityInteraction.Type.INTERACT, hand, null);
                            }

                            @Override
                            public void onInteraction(InteractionHand hand, Vec3 hit) {
                                enqueue(EntityInteraction.Type.INTERACT_AT, hand, hit);
                            }

                            private void enqueue(EntityInteraction.Type type, InteractionHand hand, Vec3 hit) {
                                if (hit != null && (!Double.isFinite(hit.x) || !Double.isFinite(hit.y) || !Double.isFinite(hit.z))) return;
                                EquipmentSlot slot = hand == null ? null : hand == InteractionHand.MAIN_HAND ? EquipmentSlot.HAND : EquipmentSlot.OFF_HAND;
                                Vector point = hit == null ? null : new Vector(hit.x, hit.y, hit.z);
                                serverThread.execute(() -> {
                                    if (!closed) owner.dispatchInteraction(player, packet.getEntityId(), type, slot, point, packet.isUsingSecondaryAction());
                                });
                            }
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
