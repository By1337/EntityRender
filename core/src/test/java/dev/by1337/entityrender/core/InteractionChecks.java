package dev.by1337.entityrender.core;

import dev.by1337.entityrender.api.EntityInteraction;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.embedded.EmbeddedChannel;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import net.minecraft.network.protocol.game.ServerboundAttackPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.Vec3;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import java.lang.reflect.Proxy;
import java.util.*;

final class InteractionChecks {
    static void run(World world) {
        var renderer = new PaperEntityRenderer((player, packet) -> {
        }, ignored -> Bukkit.getUnsafe().nextEntityId(world));
        Player viewer = (Player) Proxy.newProxyInstance(Player.class.getClassLoader(), new Class<?>[]{ Player.class }, (proxy, method, args) -> switch (method.getName()) {
            case "getWorld" -> world;
            case "isOnline", "isSneaking" -> true;
            case "getUniqueId" -> new UUID(2, 2);
            case "hashCode" -> 2;
            case "equals" -> proxy == args[0];
            case "toString" -> "Interaction viewer";
            default -> throw new UnsupportedOperationException(method.toString());
        });
        var scheduled = new ArrayDeque<Runnable>();
        var callbacks = new ArrayList<EntityInteraction>();
        var channel = new EmbeddedChannel();
        channel.pipeline().addLast("packet_handler", new ChannelInboundHandlerAdapter());
        try (renderer; var hooks = new InteractionHooks(renderer, scheduled::addLast)) {
            var entity = renderer.createArmorStand(new Location(world, 0, 64, 0));
            entity.show(viewer);
            entity.setInteractionHandler(callbacks::add);
            var nativeTarget = new net.minecraft.world.entity.decoration.ArmorStand(net.minecraft.world.entity.EntityTypes.ARMOR_STAND, ((org.bukkit.craftbukkit.CraftWorld) world).getHandle());
            nativeTarget.setId(entity.getId());
            hooks.bind(viewer, channel);
            channel.runPendingTasks();
            var packet = new ServerboundInteractPacket(nativeTarget.getId(), InteractionHand.OFF_HAND, new Vec3(.25, .5, .75), true);
            channel.writeInbound(packet);
            require(channel.readInbound() == null, "Virtual interaction reached vanilla");
            require(callbacks.isEmpty(), "Callback ran on the network thread");
            while (!scheduled.isEmpty()) scheduled.removeFirst().run();
            var event = callbacks.getFirst();
            require(event.type() == EntityInteraction.Type.INTERACT_AT && event.hand() == EquipmentSlot.OFF_HAND && event.secondaryAction(), "Interaction fields lost");
            event.hitPosition().setX(99);
            require(event.hitPosition().getX() == .25, "Interaction point alias");
            channel.writeInbound(new ServerboundAttackPacket(nativeTarget.getId()));
            while (!scheduled.isEmpty()) scheduled.removeFirst().run();
            require(callbacks.size() == 2 && callbacks.getLast().type() == EntityInteraction.Type.ATTACK && callbacks.getLast().secondaryAction(), "Attack packet lost its server-side sneaking state");
            entity.hide(viewer);
            channel.writeInbound(new ServerboundAttackPacket(nativeTarget.getId()));
            while (!scheduled.isEmpty()) scheduled.removeFirst().run();
            require(callbacks.size() == 2, "Non-viewer interaction accepted");
            nativeTarget.setId(Bukkit.getUnsafe().nextEntityId(world));
            var unrelated = new ServerboundAttackPacket(nativeTarget.getId());
            channel.writeInbound(unrelated);
            require(channel.readInbound() == unrelated, "Unrelated server interaction consumed");
            hooks.close();
            channel.runPendingTasks();
            nativeTarget.setId(entity.getId());
            var afterClose = new ServerboundAttackPacket(nativeTarget.getId());
            channel.writeInbound(afterClose);
            require(channel.readInbound() == afterClose, "Pipeline hook leaked after close");
        } finally {
            channel.finishAndReleaseAll();
        }
        Bukkit.getLogger().info("ENTITY_RENDER_INTERACTIONS_PASS");
    }

    private static void require(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
}
