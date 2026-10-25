package dev.by1337.entityrender.core;

import dev.by1337.entityrender.core.metadata.*;
import io.netty.buffer.Unpooled;
import net.minecraft.SharedConstants;
import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.*;
import net.minecraft.network.syncher.*;
import net.minecraft.server.Bootstrap;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.util.*;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Quaternionf;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import dev.by1337.entityrender.core.metadata.GeneratedMetadata;

public final class RuntimeChecks {
    record Sent(UUID viewer, Packet<?> packet) {
    }

    public static void main(String[] args) throws Exception {
        SharedConstants.tryDetectVersion();
        Bootstrap.bootStrap();
        metadata();
        lifecycle();
        Bootstrap.STDOUT.println("EntityRender runtime checks passed: metadata schema, dirty batching, snapshots, retry, ownership, copies, lifecycle, native packet codecs");
    }

    private static void require(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }

    private static void metadata() throws Exception {
        require(!GeneratedMetadata.monster.Creeper.LAYOUT.hasCompleteDefaults(), "Unknown entity defaults were fabricated");
        boolean missingDefaultsRejected = false;
        try {
            new MetadataState(GeneratedMetadata.monster.Creeper.LAYOUT);
        } catch (IllegalStateException expected) {
            missingDefaultsRejected = true;
        }
        require(missingDefaultsRejected, "Metadata state accepted unknown defaults");
        assertNativeLayout(net.minecraft.world.entity.decoration.ArmorStand.class, new MetadataState(GeneratedMetadata.decoration.ArmorStand.LAYOUT));
        assertNativeLayout(net.minecraft.world.entity.Display.TextDisplay.class, new MetadataState(GeneratedMetadata.Display.TextDisplay.LAYOUT));
        assertNativeLayout(net.minecraft.world.entity.Display.ItemDisplay.class, new MetadataState(GeneratedMetadata.Display.ItemDisplay.LAYOUT));
        assertNativeLayout(net.minecraft.world.entity.Display.BlockDisplay.class, new MetadataState(GeneratedMetadata.Display.BlockDisplay.LAYOUT));
        var state = new MetadataState(GeneratedMetadata.Display.TextDisplay.LAYOUT);
        require(!state.isDirty(), "Defaults must not enqueue changes");
        require(!state.set(GeneratedMetadata.Entity.DATA_SILENT, false), "Unchanged metadata was marked dirty");
        state.set(GeneratedMetadata.Entity.DATA_SILENT, true);
        state.set(GeneratedMetadata.Entity.DATA_CUSTOM_NAME_VISIBLE, true);
        var before = state.dirtySnapshot();
        require(before.size() == 2 && before.get(0).id() < before.get(1).id(), "Dirty values are not stable / minimal");
        state.fullSnapshot();
        require(state.isDirty(), "Full snapshot consumed dirty state");
        state.acknowledge();
        require(!state.isDirty(), "Dirty acknowledge failed");
        state.set(GeneratedMetadata.Entity.DATA_SILENT, false);
        require(state.dirtySnapshot().size() == 1, "Reset to default must be sent");
        require(before.stream().anyMatch(v -> v.id() == GeneratedMetadata.Entity.DATA_SILENT.index() && v.value().equals(true)), "Old packet snapshot was mutated");
        var input = new Vector3f(1, 2, 3);
        state.set(GeneratedMetadata.Display.DATA_TRANSLATION_ID, input);
        input.set(100);
        require(state.get(GeneratedMetadata.Display.DATA_TRANSLATION_ID).x() == 1, "Input alias");
        var returned = (Vector3f) state.get(GeneratedMetadata.Display.DATA_TRANSLATION_ID);
        returned.set(99);
        require(state.get(GeneratedMetadata.Display.DATA_TRANSLATION_ID).x() == 1, "Getter alias");
        var snapshot = state.dirtySnapshot();
        state.set(GeneratedMetadata.Display.DATA_TRANSLATION_ID, new Vector3f(5, 6, 7));
        require(snapshot.stream().filter(v -> v.id() == GeneratedMetadata.Display.DATA_TRANSLATION_ID.index()).map(v -> (Vector3fc) v.value()).findFirst().orElseThrow().x() == 1, "Packet alias");
        boolean rejected = false;
        try {
            state.set(GeneratedMetadata.decoration.ArmorStand.DATA_CLIENT_FLAGS, (byte) 1);
        } catch (IllegalArgumentException expected) {
            rejected = true;
        }
        require(rejected, "Foreign metadata key accepted");
        nativeRoundtrip(new ClientboundSetEntityDataPacket(42, new MetadataState(GeneratedMetadata.decoration.ArmorStand.LAYOUT).fullSnapshot()));
        nativeRoundtrip(new ClientboundSetEntityDataPacket(43, new MetadataState(GeneratedMetadata.Display.TextDisplay.LAYOUT).fullSnapshot()));
        nativeRoundtrip(new ClientboundSetEntityDataPacket(44, new MetadataState(GeneratedMetadata.Display.ItemDisplay.LAYOUT).fullSnapshot()));
        nativeRoundtrip(new ClientboundSetEntityDataPacket(45, new MetadataState(GeneratedMetadata.Display.BlockDisplay.LAYOUT).fullSnapshot()));
    }

    private static void assertNativeLayout(Class<?> type, MetadataState state) throws Exception {
        var fields = new TreeMap<Integer, Object>();
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
            for (var field : c.getDeclaredFields()) {
                if (!Modifier.isStatic(field.getModifiers()) || field.getType() != EntityDataAccessor.class) continue;
                field.setAccessible(true);
                var accessor = (EntityDataAccessor<?>) field.get(null);
                require(fields.put(accessor.id(), accessor.serializer()) == null, "Duplicate native accessor");
            }
        }
        var values = state.fullSnapshot();
        require(fields.size() == values.size(), "Layout omitted native fields: " + type);
        for (var value : values) require(fields.get(value.id()) == value.serializer(), "Native serializer/index mismatch: " + type + "/" + value.id());
    }

    private static void nativeRoundtrip(ClientboundSetEntityDataPacket packet) {
        var raw = Unpooled.buffer();
        try {
            var access = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);
            var buf = new RegistryFriendlyByteBuf(raw, access);
            ClientboundSetEntityDataPacket.STREAM_CODEC.encode(buf, packet);
            byte[] before = new byte[raw.readableBytes()];
            raw.getBytes(0, before);
            var decoded = ClientboundSetEntityDataPacket.STREAM_CODEC.decode(buf);
            require(!raw.isReadable() && decoded.id() == packet.id(), "Native packet roundtrip failed");
            raw.clear();
            ClientboundSetEntityDataPacket.STREAM_CODEC.encode(buf, decoded);
            byte[] after = new byte[raw.readableBytes()];
            raw.getBytes(0, after);
            require(Arrays.equals(before, after), "Native metadata bytes changed on roundtrip");
        } finally {
            raw.release();
        }
    }

    private static void lifecycle() throws Exception {
        World world = fake(World.class, UUID.randomUUID(), null);
        Player first = fake(Player.class, UUID.randomUUID(), world), second = fake(Player.class, UUID.randomUUID(), world);
        var sent = new ArrayList<Sent>();
        var ids = new AtomicInteger(100);
        var failNext = new AtomicBoolean();
        var renderer = new PaperEntityRenderer((player, packet) -> {
            if (failNext.compareAndSet(true, false)) throw new IllegalStateException("Injected transport failure");
            sent.add(new Sent(player.getUniqueId(), packet));
        }, ignored -> ids.getAndIncrement());
        var input = new Location(world, 1, 2, 3, 40, 15);
        var stand = renderer.createArmorStand(input);
        input.setX(999);
        require(stand.getPosition().getX() == 1, "Location alias");
        stand.show(first);
        require(sent.size() == 2, "Expected spawn + full metadata");
        require(sent.getFirst().packet() instanceof ClientboundAddEntityPacket spawn && spawn.getYRot() != 0, "Spawn rotation missing");
        stand.show(first);
        require(sent.size() == 2, "Duplicate show");
        sent.clear();
        stand.setInvisible(true);
        stand.setGlowing(true);
        stand.setSmall(true);
        stand.setArms(true);
        stand.show(second);
        require(sent.size() == 2, "New viewer did not receive full snapshot");
        sent.clear();
        renderer.flush();
        require(sent.size() == 2 && sent.get(0).packet() == sent.get(1).packet(), "Metadata packet not shared across viewers");
        var update = (ClientboundSetEntityDataPacket) sent.getFirst().packet();
        require(update.packedItems().size() == 2, "Same field changes were not coalesced");
        require(update.packedItems().stream().anyMatch(v -> v.id() == GeneratedMetadata.Entity.DATA_SHARED_FLAGS_ID.index() && v.value().equals((byte) 0x60)), "Entity flags overwrite each other");
        sent.clear();
        renderer.flush();
        require(sent.isEmpty(), "Idle flush sends packets");
        stand.setSmall(true);
        renderer.flush();
        require(sent.isEmpty(), "Unchanged setter sends packets");
        stand.setHeadPose(new EulerAngle(Math.PI / 2, 0, 0));
        renderer.flush();
        require(Math.abs(stand.getHeadPose().getX() - Math.PI / 2) < 0.0001, "Pose conversion lost radians");
        sent.clear();
        var vector = new org.bukkit.util.Vector(9, 8, 7);
        stand.setPosition(vector);
        vector.setX(999);
        stand.getPosition().setY(999);
        renderer.flush();
        require(sent.size() == 2 && sent.getFirst().packet() instanceof ClientboundEntityPositionSyncPacket, "Position update not batched");
        require(stand.getPosition().getX() == 9 && stand.getPosition().getY() == 8, "Vector alias");
        sent.clear();
        stand.setSilent(true);
        failNext.set(true);
        boolean failed = false;
        try {
            renderer.flush();
        } catch (IllegalStateException expected) {
            failed = true;
        }
        require(failed, "Injected failure not observed");
        sent.clear();
        renderer.flush();
        require(sent.size() == 2, "Failed broadcast was not retried");
        sent.clear();
        var display = renderer.createTextDisplay(new Location(world, 0, 0, 0));
        var transform = new Transformation(new Vector3f(1, 2, 3), new Quaternionf(), new Vector3f(2), new Quaternionf());
        display.setTransformation(transform);
        transform.getTranslation().set(999);
        display.getTransformation().getTranslation().set(99);
        require(display.getTransformation().getTranslation().x() == 1, "Transformation alias");
        display.setBillboard(org.bukkit.entity.Display.Billboard.CENTER);
        display.setBrightness(new org.bukkit.entity.Display.Brightness(7, 15));
        require(display.getBrightness().getBlockLight() == 7 && display.getBrightness().getSkyLight() == 15, "Brightness packing failed");
        display.setShadowed(true);
        display.setSeeThrough(true);
        display.setAlignment(org.bukkit.entity.TextDisplay.TextAlignment.RIGHT);
        require(display.isShadowed() && display.isSeeThrough() && display.getAlignment() == org.bukkit.entity.TextDisplay.TextAlignment.RIGHT, "Text flags overwrite each other");
        display.show(first);
        sent.clear();
        renderer.flush();
        sent.clear();
        display.setInterpolationDelay(0);
        renderer.flush();
        require(sent.size() == 1, "Forced interpolation restart was lost");
        sent.clear();
        var asyncError = new AtomicReference<Throwable>();
        Thread thread = new Thread(() -> {
            try {
                stand.setSilent(false);
            } catch (Throwable e) {
                asyncError.set(e);
            }
        });
        thread.start();
        thread.join();
        require(asyncError.get() instanceof IllegalStateException, "Async mutation accepted");
        boolean invalid = false;
        try {
            stand.setPosition(Double.NaN, 0, 0);
        } catch (IllegalArgumentException expected) {
            invalid = true;
        }
        require(invalid, "Non-finite position accepted");
        stand.hide(first);
        stand.hide(first);
        require(stand.getViewers().size() == 1, "Hide not idempotent");
        renderer.close();
        renderer.close();
        require(stand.isClosed() && display.isClosed() && renderer.size() == 0, "Owner cleanup failed");
        boolean closed = false;
        try {
            stand.setSmall(false);
        } catch (IllegalStateException expected) {
            closed = true;
        }
        require(closed, "Mutation after close accepted");
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
