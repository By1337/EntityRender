package dev.by1337.entityrender.core;

import dev.by1337.entityrender.EntityRender;
import dev.by1337.entityrender.core.metadata.*;
import io.papermc.paper.adventure.PaperAdventure;
import net.kyori.adventure.text.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.*;
import net.minecraft.network.syncher.EntityDataSerializers;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

/** Race checks exercise publication, not a second implementation of the rendering algorithm. */
final class AsyncChecks {
    static CompletableFuture<Void> run(Plugin plugin) {
        World world = Bukkit.getWorlds().getFirst();
        var manual = (PaperEntityRenderer) EntityRender.createManual(plugin);
        var automatic = (PaperEntityRenderer) EntityRender.create(plugin);
        require(manual.service() == automatic.service(), "Renderers did not share a sampler/transport worker");
        var result = new CompletableFuture<Void>();
        Thread.ofVirtual().name("EntityRender-async-checks").start(() -> {
            try {
                metadataRaces();
                publishedVisibility(world);
                // Cold defaults must request main without blocking the main loop or creating a native entity here.
                var creeper = manual.createCreeper(new Location(world, 0, 70, 0));
                creeper.setPowered(true);
                manual.flush();
                manual.completion().get(15, TimeUnit.SECONDS);
                var ids = ConcurrentHashMap.<Integer>newKeySet();
                var failure = new AtomicReference<Throwable>();
                var producers = new ArrayList<Thread>();
                var done = new AtomicBoolean();
                var flusher = Thread.ofVirtual().start(() -> {
                    while (!done.get()) {
                        try {
                            manual.flush();
                            Thread.sleep(2);
                        } catch (Throwable error) {
                            failure.compareAndSet(null, error);
                            return;
                        }
                    }
                });
                for (int producer = 0; producer < 4; producer++) producers.add(Thread.ofVirtual().start(() -> {
                    try {
                        for (int i = 0; i < 1500; i++) {
                            var entity = manual.createTextDisplay(new Location(world, i % 16, 70, 0));
                            if (!ids.add(entity.getId())) throw new AssertionError("Duplicate pooled ID");
                            entity.setText(Component.text("personal-" + i));
                            entity.setPosition(i % 16, 71, 1);
                            entity.close();
                        }
                    } catch (Throwable error) {
                        failure.compareAndSet(null, error);
                    }
                }));
                for (var producer : producers) producer.join();
                done.set(true);
                flusher.join();
                if (failure.get() != null) throw new AssertionError("Concurrent lifecycle/publication failed", failure.get());
                require(ids.size() == 6000, "Concurrent ID pool lost entities");
                var dragon = manual.createEnderDragon(new Location(world, 0, 80, 0));
                for (int i = 0; i < 9; i++) require(!ids.contains(dragon.getId() + i), "Dragon ID block overlaps another entity");
                dragon.close();
                var last = automatic.createTextDisplay(new Location(world, 0, 71, 0));
                for (int i = 0; i < 1000; i++) last.setText(Component.text("tick-" + i));
                // Automatic publication must keep operating while the producer is asleep.
                PublishedEntity frame = null;
                for (int attempt = 0; attempt < 100; attempt++) {
                    Thread.sleep(20);
                    var observed = new AtomicReference<PublishedEntity>();
                    automatic.service().submit(() -> observed.set(automatic.transport.snapshot(last.getId()))).get(10, TimeUnit.SECONDS);
                    frame = observed.get();
                    if (frame != null && text(frame).equals(Component.text("tick-999"))) break;
                }
                require(frame != null && text(frame).equals(Component.text("tick-999")), "Automatic legacy flush did not publish async edits");
                last.close();
                creeper.close();
                manual.flush();
                manual.completion().get(15, TimeUnit.SECONDS);
                require(manual.size() == 0, "Closed entities remain in producer registry");
                closeRace(plugin, manual.service(), world);
                manual.close();
                automatic.close();
                plugin.getLogger().info("ENTITY_RENDER_ASYNC_PASS ids=6000 shared_service=true");
                result.complete(null);
            } catch (Throwable error) {
                manual.close();
                automatic.close();
                result.completeExceptionally(error);
            }
        });
        return result.orTimeout(90, TimeUnit.SECONDS);
    }

    private static void metadataRaces() throws Exception {
        var number = new MetadataKey<Integer>(0, EntityDataSerializers.INT);
        var flags = new MetadataKey<Byte>(1, EntityDataSerializers.BYTE);
        var layout = new MetadataLayout(null, number, flags).withDefault(number, 0).withDefault(flags, (byte) 0);
        var state = new MetadataState(layout);
        var ended = new AtomicBoolean();
        var last = new AtomicInteger();
        var reader = Thread.ofVirtual().start(() -> {
            while (!ended.get() || state.isDirty()) for (var value : state.drainOwned()) if (value.id() == 0) last.set((Integer) value.value());
        });
        for (int i = 1; i <= 200000; i++) state.setOwned(number, i, false);
        ended.set(true);
        reader.join();
        require(last.get() == 200000, "Dirty exchange lost the final concurrent write");
        var barrier = new CyclicBarrier(3);
        var failure = new AtomicReference<Throwable>();
        var first = Thread.ofVirtual().start(() -> flagRounds(state, flags, barrier, 1, failure));
        var second = Thread.ofVirtual().start(() -> flagRounds(state, flags, barrier, 2, failure));
        for (int round = 0; round < 1000; round++) {
            state.set(flags, (byte) 0);
            barrier.await(10, TimeUnit.SECONDS);
            barrier.await(10, TimeUnit.SECONDS);
            require(state.get(flags) == 3, "Packed flag CAS lost another writer's bit");
        }
        first.join();
        second.join();
        if (failure.get() != null) throw new AssertionError("Packed helper race failed", failure.get());
        var keys = new ArrayList<MetadataKey<Integer>>();
        for (int i = 0; i < 130; i++) keys.add(new MetadataKey<>(i, EntityDataSerializers.INT));
        var wide = new MetadataLayout(null, keys.toArray(MetadataKey<?>[]::new));
        for (var key : keys) wide = wide.withDefault(key, 0);
        var extended = new MetadataState(wide);
        for (int index : new int[]{ 0, 63, 64, 127, 129 }) extended.set(keys.get(index), index + 1);
        require(extended.drainOwned().size() == 5 && !extended.isDirty(), "Dirty masks above 64 fields were lost");
    }

    private static void flagRounds(MetadataState state, MetadataKey<Byte> flags, CyclicBarrier barrier, int bit, AtomicReference<Throwable> failure) {
        try {
            for (int round = 0; round < 1000; round++) {
                barrier.await(10, TimeUnit.SECONDS);
                state.update(flags, value -> (byte) (value | bit));
                barrier.await(10, TimeUnit.SECONDS);
            }
        } catch (Throwable error) {
            failure.compareAndSet(null, error);
        }
    }

    private static void closeRace(Plugin plugin, RenderService service, World world) throws Exception {
        var renderer = (PaperEntityRenderer) service.onMain(() -> EntityRender.createManual(plugin)).get(10, TimeUnit.SECONDS);
        var handles = new ConcurrentLinkedQueue<dev.by1337.entityrender.api.entity.VirtualEntity>();
        var error = new AtomicReference<Throwable>();
        var producer = Thread.ofVirtual().start(() -> {
            try {
                for (int i = 0; i < 4000; i++) {
                    var entity = renderer.createTextDisplay(new Location(world, 0, 70, 0));
                    handles.add(entity);
                    entity.setText(Component.text(i));
                }
            } catch (IllegalStateException closed) {
                if (!renderer.isClosed()) error.set(closed);
            } catch (Throwable failure) {
                error.set(failure);
            }
        });
        var publisher = Thread.ofVirtual().start(() -> {
            while (!renderer.isClosed()) try {
                renderer.flush();
                Thread.sleep(1);
            } catch (IllegalStateException closed) {
                if (!renderer.isClosed()) error.set(closed);
            } catch (Throwable failure) {
                error.set(failure);
                return;
            }
        });
        Thread.sleep(10);
        renderer.close();
        producer.join();
        publisher.join();
        service.fence().get(10, TimeUnit.SECONDS);
        require(error.get() == null && renderer.size() == 0, "Close raced publication/registration: " + error.get());
        require(handles.stream().allMatch(dev.by1337.entityrender.api.entity.VirtualEntity::isClosed), "Close left live handles");
        var retained = new AtomicInteger(-1);
        service.submit(() -> retained.set(renderer.transport.size())).get(10, TimeUnit.SECONDS);
        require(retained.get() == 0, "Late frame resurrected a closed renderer");
    }

    private record Sent(UUID viewer, Packet<?> packet) {
    }

    private static void publishedVisibility(World world) throws Exception {
        var packets = new ArrayList<Sent>();
        var directory = new PlayerDirectory();
        var ids = new AtomicInteger(50000000);
        try (var renderer = new PaperEntityRenderer((player, packet) -> packets.add(new Sent(player.getUniqueId(), packet)), ignored -> ids.getAndIncrement())) {
            Player first = player(world, new UUID(10, 10)), second = player(world, new UUID(20, 20));
            long chunk = PlayerDirectory.chunk(0, 0);
            var a = (BaseVirtualEntity) renderer.createTextDisplay(new Location(world, 0, 70, 0));
            var b = (BaseVirtualEntity) renderer.createTextDisplay(new Location(world, 0, 71, 0));
            ((dev.by1337.entityrender.api.entity.VirtualTextDisplay) a).setText(Component.text("A"));
            ((dev.by1337.entityrender.api.entity.VirtualTextDisplay) b).setText(Component.text("B"));
            a.show(first);
            b.show(second);
            directory.replace(List.of(sample(first, world, 1, Set.of(chunk)), sample(second, world, 1, Set.of(chunk))));
            renderer.transport.accept(List.of(a.publish(), b.publish()), List.of());
            renderer.transport.render(directory);
            require(packets.size() == 4, "Personal hologram initial snapshots missing");
            for (var sent : packets) if (sent.packet() instanceof ClientboundSetEntityDataPacket packet) {
                var expected = sent.viewer().equals(first.getUniqueId()) ? Component.text("A") : Component.text("B");
                require(text(packet.packedItems()).equals(expected), "Personal text leaked to another player");
            }
            packets.clear();
            ((dev.by1337.entityrender.api.entity.VirtualTextDisplay) a).setText(Component.text("not published"));
            directory.replace(List.of(sample(first, world, 2, Set.of()), sample(second, world, 1, Set.of(chunk))));
            renderer.transport.render(directory);
            require(packets.isEmpty(), "World/respawn reset sent an old-session packet");
            directory.chunk(first.getUniqueId(), 1, chunk, true);
            renderer.transport.render(directory);
            require(packets.isEmpty(), "Old-epoch chunk event restored visibility");
            directory.chunk(first.getUniqueId(), 2, chunk, true);
            renderer.transport.render(directory);
            require(packets.size() == 2 && text(((ClientboundSetEntityDataPacket) packets.get(1).packet()).packedItems()).equals(Component.text("A")), "Late viewer read mutable/unpublished metadata");
            packets.clear();
            renderer.transport.accept(List.of(a.publish()), List.of());
            renderer.transport.render(directory);
            require(packets.size() == 1 && packets.getFirst().viewer().equals(first.getUniqueId()), "Personal update broadcast to wrong viewer");
            packets.clear();
            a.setPosition(48, 70, 0);
            renderer.transport.accept(List.of(a.publish()), List.of());
            renderer.transport.render(directory);
            require(packets.size() == 1 && packets.getFirst().packet() instanceof ClientboundRemoveEntitiesPacket, "Entity remained visible in an unsent destination chunk");
            packets.clear();
            directory.chunk(first.getUniqueId(), 2, PlayerDirectory.chunk(48, 0), true);
            renderer.transport.render(directory);
            require(packets.size() == 2, "Chunk readiness did not restore manual visibility intent");
            packets.clear();
            World other = (World) Proxy.newProxyInstance(World.class.getClassLoader(), new Class<?>[]{ World.class }, (proxy, method, args) -> {
                if (method.getName().equals("getUID")) return new UUID(99, 99);
                if (method.getName().equals("hashCode")) return 99;
                if (method.getName().equals("equals")) return proxy == args[0];
                throw new UnsupportedOperationException(method.toString());
            });
            directory.replace(List.of(sample(first, other, 3, Set.of(chunk)), sample(second, world, 1, Set.of(chunk))));
            renderer.transport.render(directory);
            require(a.getViewers().isEmpty() && packets.isEmpty(), "World transition leaked an old-world entity");
            var stand = (BaseVirtualEntity) renderer.createArmorStand(new Location(world, 0, 70, 0));
            var item = new org.bukkit.inventory.ItemStack(Material.DIAMOND, 3);
            stand.setEquipment(org.bukkit.inventory.EquipmentSlot.HEAD, item);
            var equipmentFrame = stand.publish();
            item.setAmount(99);
            stand.setEquipment(org.bukkit.inventory.EquipmentSlot.HEAD, null);
            require(equipmentFrame.equipment().size() == 1 && equipmentFrame.equipment().getFirst().getSecond().getCount() == 3, "Published equipment was mutated or expanded to untouched slots");
            require(stand.publish().equipment().getFirst().getSecond().isEmpty(), "Clearing an equipment slot was not published");
            stand.close();
            a.close();
            renderer.transport.accept(List.of(a.publish()), List.of());
            renderer.transport.render(directory);
            require(a.getViewers().isEmpty(), "Closed personal entity retained viewers");
        }
    }

    private static PlayerDirectory.Sample sample(Player player, World world, long epoch, Set<Long> chunks) {
        return new PlayerDirectory.Sample(player, player.getUniqueId(), world, 0, 70, 0, epoch, chunks);
    }

    private static Component text(PublishedEntity entity) {
        return text(entity.metadata());
    }

    private static Component text(List<net.minecraft.network.syncher.SynchedEntityData.DataValue<?>> values) {
        var value = values.stream().filter(item -> item.id() == GeneratedMetadata.Display.TextDisplay.DATA_TEXT_ID.index()).findFirst().orElseThrow();
        return PaperAdventure.asAdventure((net.minecraft.network.chat.Component) value.value());
    }

    private static Player player(World world, UUID id) {
        return (Player) Proxy.newProxyInstance(Player.class.getClassLoader(), new Class<?>[]{ Player.class }, (proxy, method, args) -> switch (method.getName()) {
            case "getUniqueId" -> id;
            case "getWorld" -> world;
            case "isOnline" -> true;
            case "hashCode" -> id.hashCode();
            case "equals" -> proxy == args[0];
            default -> throw new UnsupportedOperationException(method.toString());
        });
    }

    private static void require(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
}
