package dev.by1337.entityrender.core;

import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.*;
import net.minecraft.world.phys.Vec3;
import java.util.UUID;

/** Version-specific spawn construction; the NMS codec owns the orb packet representation. */
final class SpawnPackets implements AutoCloseable {
    private FriendlyByteBuf scratch;

    private FriendlyByteBuf buffer() {
        if (scratch == null) scratch = new FriendlyByteBuf(Unpooled.buffer(32));
        scratch.clear();
        return scratch;
    }

    Packet<?> head(int id, float yaw) {
        var buffer = buffer();
        buffer.writeVarInt(id);
        buffer.writeByte(net.minecraft.util.Mth.floor(yaw * 256.0F / 360.0F));
        return ClientboundRotateHeadPacket.STREAM_CODEC.decode(buffer);
    }

    Packet<?> signal(int id, PublishedEntity.Signal signal) {
        return switch (signal) {
            case PublishedEntity.Signal.Hurt hurt -> new ClientboundHurtAnimationPacket(id, hurt.yaw());
            case PublishedEntity.Signal.Swing swing -> new ClientboundSwingAnimationPacket(id,
                    swing.data() == 0 ? net.minecraft.world.InteractionHand.MAIN_HAND : net.minecraft.world.InteractionHand.OFF_HAND,
                    net.minecraft.world.item.component.SwingAnimation.DEFAULT);
            case PublishedEntity.Signal.Effect effect -> {
                var buffer = buffer();
                buffer.writeInt(id);
                buffer.writeByte(effect.data());
                yield ClientboundEntityEventPacket.STREAM_CODEC.decode(buffer);
            }
        };
    }

    @Override
    public void close() {
        if (scratch != null) {
            scratch.release();
            scratch = null;
        }
    }

    Packet<?> create(org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, int id, UUID uuid,
        double x, double y, double z, float pitch, float yaw, int data) {
        return new ClientboundAddEntityPacket(id, uuid, x, y, z, pitch, yaw, nativeType, data, Vec3.ZERO, yaw);
    }
}
