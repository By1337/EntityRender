package dev.by1337.entityrender.core;

import dev.by1337.entityrender.api.entity.VirtualEntity;
import io.netty.buffer.Unpooled;
import net.minecraft.SharedConstants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.*;
import net.minecraft.server.Bootstrap;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import java.lang.reflect.Proxy;
import java.util.*;

/** Executed against each supported version's native server libraries. */
public final class HurtAnimationChecks {
    public static void main(String[] args) throws Exception {
        SharedConstants.tryDetectVersion();
        Bootstrap.bootStrap();
        World world = fake(World.class, UUID.randomUUID(), null);
        Player first = fake(Player.class, UUID.randomUUID(), world), late = fake(Player.class, UUID.randomUUID(), world);
        var sent = new ArrayList<Packet<?>>();
        try (var renderer = new PaperEntityRenderer((player, packet) -> sent.add(packet), ignored -> 900)) {
            VirtualEntity entity = renderer.createArmorStand(new Location(world, 0, 70, 0));
            entity.show(first);
            var thread = Thread.ofVirtual().start(() -> entity.hurtAnimation(123.5F));
            thread.join();
            entity.hurtAnimation(-45.0F);
            require(sent.isEmpty(), "Animation bypassed publication");
            renderer.flush();
            require(sent.size() == 4 && sent.get(0) instanceof ClientboundAddEntityPacket
                    && sent.get(1) instanceof ClientboundSetEntityDataPacket, "Animation must follow spawn/metadata");
            assertHurt(sent.get(2), entity.getId(), 123.5F);
            assertHurt(sent.get(3), entity.getId(), -45.0F);
            sent.clear();
            renderer.flush();
            require(sent.isEmpty(), "Animation replayed");
            entity.show(late);
            renderer.flush();
            require(sent.size() == 2, "Late viewer replayed animation");
            sent.clear();
            entity.hurtAnimation(0);
            renderer.flush();
            require(sent.size() == 2, "Animation not sent to both viewers");
            for (var packet : sent) assertHurt(packet, entity.getId(), 0);
            sent.clear();
            for (float yaw : new float[]{ Float.NaN, Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY }) {
                boolean rejected = false;
                try {
                    entity.hurtAnimation(yaw);
                } catch (IllegalArgumentException expected) {
                    rejected = true;
                }
                require(rejected, "Non-finite yaw accepted");
            }
            renderer.flush();
            require(sent.isEmpty(), "Invalid yaw queued an animation");
            entity.hurtAnimation(90);
            entity.close();
            renderer.flush();
            require(sent.stream().noneMatch(ClientboundHurtAnimationPacket.class::isInstance), "Closed entity emitted animation");
            boolean rejected = false;
            try {
                entity.hurtAnimation(0);
            } catch (IllegalStateException expected) {
                rejected = true;
            }
            require(rejected, "Closed entity accepted animation");
        }
        Bootstrap.STDOUT.println("Hurt animation checks passed: native codec, async publication, viewers, one-shot delivery, validation, close");
    }

    private static void assertHurt(Packet<?> packet, int id, float yaw) {
        require(packet instanceof ClientboundHurtAnimationPacket, "Wrong packet type");
        var hurt = (ClientboundHurtAnimationPacket) packet;
        require(hurt.id() == id && hurt.yaw() == yaw, "Wrong entity ID or yaw");
        var buffer = new FriendlyByteBuf(Unpooled.buffer());
        try {
            ClientboundHurtAnimationPacket.STREAM_CODEC.encode(buffer, hurt);
            var decoded = ClientboundHurtAnimationPacket.STREAM_CODEC.decode(buffer);
            require(decoded.equals(hurt) && !buffer.isReadable(), "Native codec roundtrip failed");
        } finally {
            buffer.release();
        }
    }

    private static void require(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }

    @SuppressWarnings("unchecked")
    private static <T> T fake(Class<T> type, UUID uuid, World world) {
        return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class<?>[]{ type }, (proxy, method, args) -> switch (method.getName()) {
            case "getUniqueId", "getUID" -> uuid;
            case "getWorld" -> world;
            case "isOnline" -> true;
            case "hashCode" -> uuid.hashCode();
            case "equals" -> proxy == args[0];
            case "toString", "getName" -> type.getSimpleName() + uuid;
            default -> throw new UnsupportedOperationException(method.toString());
        });
    }
}
