package dev.by1337.entityrender.core;

import dev.by1337.entityrender.core.metadata.MetadataLayout;
import net.minecraft.server.level.*;
import net.minecraft.world.entity.*;
import org.bukkit.World;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.CraftEntityType;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/** Main-thread bootstrap and shared per-world native defaults. No prototype is allocated on a producer. */
final class WorldResources {
    record Template(MetadataLayout layout, int spawnData) {
    }

    final World world;
    private volatile boolean valid = true;
    final ServerLevel level;
    final net.minecraft.core.RegistryAccess registries;
    private final RenderService service;
    private final Map<org.bukkit.entity.EntityType, Loading> templates = new ConcurrentHashMap<>();

    WorldResources(RenderService service, World world) {
        this.service = service;
        this.world = world;
        level = ((CraftWorld) world).getHandle();
        registries = level.registryAccess();
    }

    void invalidate() {
        valid = false;
        for (var loading : templates.values()) loading.result.completeExceptionally(new IllegalStateException("World was unloaded"));
    }

    Template template(org.bukkit.entity.EntityType type, MetadataLayout generated) {
        if (!valid) throw new IllegalStateException("World was unloaded");
        var loading = templates.computeIfAbsent(type, ignored -> new Loading());
        Runnable initialize = () -> {
            if (!loading.started.compareAndSet(false, true)) return;
            try {
                if (!valid) throw new IllegalStateException("World was unloaded");
                var nativeType = CraftEntityType.bukkitToMinecraft(type);
                var prototype = nativeType.create(level, EntitySpawnReason.COMMAND);
                if (prototype == null) throw new IllegalArgumentException("Entity requires additional construction context: " + type);
                var tracker = new ServerEntity(level, prototype, UpdateInterval.periodic(1), false, new ServerEntity.Synchronizer() {
                    public void sendToTrackingPlayers(net.minecraft.network.protocol.Packet<? super net.minecraft.network.protocol.game.ClientGamePacketListener> packet) {
                    }

                    public void sendToTrackingPlayersAndSelf(net.minecraft.network.protocol.Packet<? super net.minecraft.network.protocol.game.ClientGamePacketListener> packet) {
                    }

                    public void sendToTrackingPlayersFiltered(net.minecraft.network.protocol.Packet<? super net.minecraft.network.protocol.game.ClientGamePacketListener> packet, java.util.function.Predicate<ServerPlayer> predicate) {
                    }
                }, Set.of());
                var spawn = prototype.getAddEntityPacket(tracker);
                int data = spawn instanceof net.minecraft.network.protocol.game.ClientboundAddEntityPacket packet ? packet.getData() : 0;
                loading.result.complete(new Template(generated.withSnapshot(prototype.getEntityData().packAll()), data));
            } catch (Throwable error) {
                loading.result.completeExceptionally(error);
            }
        };
        // A main caller can finish an initialization already queued by another producer.
        if (org.bukkit.Bukkit.isPrimaryThread()) initialize.run();
        else if (!loading.result.isDone() && loading.scheduled.compareAndSet(false, true)) service.onMain(() -> {
            initialize.run();
            return null;
        })
                .whenComplete((ignored, error) -> {
                    if (error != null) loading.result.completeExceptionally(error);
                });
        return loading.result.join();
    }

    int spawnData(org.bukkit.entity.EntityType type) {
        var entry = templates.get(type);
        return entry == null ? 0 : entry.result.join().spawnData();
    }

    private static final class Loading {
        final AtomicBoolean started = new AtomicBoolean();
        final AtomicBoolean scheduled = new AtomicBoolean();
        final CompletableFuture<Template> result = new CompletableFuture<>();
    }
}
