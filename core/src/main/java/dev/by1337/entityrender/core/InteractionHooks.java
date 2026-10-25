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
    private final java.util.function.IntPredicate contains;
    private final Dispatcher dispatcher;

    private interface Dispatcher {
        void dispatch(Player player, int id, EntityInteraction.Type type, EquipmentSlot hand, Vector hit, boolean secondary);
    }

    private final Executor serverThread;
    private final java.util.function.Function<Player, java.util.function.BooleanSupplier> captureGuard;
    private final String name = "entityrender-" + UUID.randomUUID();
    private final Map<UUID, Channel> channels = new HashMap<>();
    private volatile boolean closed;

    InteractionHooks(PaperEntityRenderer owner, Executor serverThread) {
        this(owner::containsEntity, owner::dispatchInteraction, serverThread, player -> () -> !owner.isClosed());
    }

    InteractionHooks(RenderService service, Executor serverThread) {
        this(service::containsEntity, service::dispatchInteraction, serverThread, service::interactionGuard);
    }

    InteractionHooks(PaperEntityRenderer owner, Executor serverThread, java.util.function.Function<Player, java.util.function.BooleanSupplier> captureGuard) {
        this(owner::containsEntity, owner::dispatchInteraction, serverThread, captureGuard);
    }

    private InteractionHooks(java.util.function.IntPredicate contains, Dispatcher dispatcher, Executor serverThread, java.util.function.Function<Player, java.util.function.BooleanSupplier> captureGuard) {
        this.contains = contains;
        this.dispatcher = dispatcher;
        this.serverThread = serverThread;
        this.captureGuard = captureGuard;
    }

    void bind(Player player, Channel channel) {
        if (closed) return;
        unbind(player.getUniqueId());
        channels.put(player.getUniqueId(), channel);
        execute(channel, () -> {
            if (closed || !channel.isOpen()) return;
            channel.pipeline().addBefore("packet_handler", name, new ChannelInboundHandlerAdapter() {
                @Override
                public void channelRead(ChannelHandlerContext context, Object message) throws Exception {
                    if (!closed && message instanceof ServerboundAttackPacket packet && contains.test(packet.entityId())) {
                        var guard = captureGuard.apply(player);
                        serverThread.execute(() -> {
                            if (!closed && guard.getAsBoolean()) dispatcher.dispatch(player, packet.entityId(), EntityInteraction.Type.ATTACK, null, null, player.isSneaking());
                        });
                        return;
                    }
                    if (!closed && message instanceof ServerboundInteractPacket packet && contains.test(packet.entityId())) {
                        Vec3 hit = packet.location();
                        if (hit == null || !Double.isFinite(hit.x) || !Double.isFinite(hit.y) || !Double.isFinite(hit.z)) return;
                        EquipmentSlot slot = packet.hand() == InteractionHand.MAIN_HAND ? EquipmentSlot.HAND : EquipmentSlot.OFF_HAND;
                        Vector point = new Vector(hit.x, hit.y, hit.z);
                        var guard = captureGuard.apply(player);
                        serverThread.execute(() -> {
                            if (!closed && guard.getAsBoolean()) dispatcher.dispatch(player, packet.entityId(), EntityInteraction.Type.INTERACT_AT, slot, point, packet.usingSecondaryAction());
                        });
                        return;
                    }
                    super.channelRead(context, message);
                }
            });
        });
    }

    void unbind(UUID player) {
        Channel channel = channels.remove(player);
        if (channel == null || channel.eventLoop().isShuttingDown()) return;
        execute(channel, () -> {
            if (channel.pipeline().get(name) != null) channel.pipeline().remove(name);
        });
    }

    private static void execute(Channel channel, Runnable task) {
        try {
            channel.eventLoop().execute(task);
        } catch (java.util.concurrent.RejectedExecutionException error) {
            if (channel.isOpen() && !channel.eventLoop().isShuttingDown()) throw error;
        }
    }

    @Override
    public void close() {
        if (closed) return;
        closed = true;
        for (UUID player : List.copyOf(channels.keySet())) unbind(player);
    }
}
