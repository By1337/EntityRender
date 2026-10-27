package dev.by1337.entityrender.core;

import net.minecraft.network.protocol.Packet;
import org.bukkit.*;
import org.bukkit.entity.Player;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

final class TrackerChecks {
    static void run(World world) throws Exception {
        var position = new AtomicReference<>(new Location(world, 1, 64, 0));
        Player viewer = (Player) Proxy.newProxyInstance(Player.class.getClassLoader(), new Class<?>[]{ Player.class }, (proxy, method, args) -> switch (method.getName()) {
            case "getLocation" -> position.get().clone();
            case "getWorld" -> world;
            case "isOnline" -> true;
            case "getUniqueId" -> new UUID(3, 3);
            case "hashCode" -> 3;
            case "equals" -> proxy == args[0];
            case "toString" -> "Tracker viewer";
            default -> throw new UnsupportedOperationException(method.toString());
        });
        var packets = new ArrayList<Packet<?>>();
        try (var renderer = new PaperEntityRenderer((player, packet) -> packets.add(packet), ignored -> Bukkit.getUnsafe().nextEntityId(world), ignored -> List.of(viewer))) {
            var first = renderer.createArmorStand(new Location(world, 0, 64, 0));
            var second = renderer.createArmorStand(new Location(world, 0, 65, 0));
            var center = new Location(world, 0, 64, 0);
            var tracker = renderer.createTracker(center, 32);
            require(tracker.getViewers().isEmpty() && !tracker.iterator().hasNext(), "Tracker viewers must start empty");
            center.setX(1000);
            tracker.add(first);
            tracker.add(second);
            renderer.flush();
            require(packets.size() == 4 && first.getViewers().size() == 1 && second.getViewers().size() == 1, "Group visibility failed");
            require(tracker.getViewers().equals(Set.of(viewer)), "Tracker player snapshot missing");
            var savedViewers = tracker.getViewers();
            var savedIterator = tracker.iterator();
            var visited = new ArrayList<Player>();
            tracker.forEach(visited::add);
            require(visited.equals(List.of(viewer)), "Tracker forEach missing viewer");
            boolean immutable = false;
            try {
                tracker.getViewers().clear();
            } catch (UnsupportedOperationException expected) {
                immutable = true;
            }
            require(immutable, "Tracker snapshot is mutable");
            packets.clear();
            renderer.flush();
            require(packets.isEmpty(), "Idle tracker repeats snapshots");
            require(tracker.getViewers() == savedViewers, "Idle tracker replaces its player snapshot");
            var membership = ((SceneTracker) tracker).snapshot();
            require(((SceneTracker) tracker).snapshot() == membership, "Idle tracker copies membership");
            position.set(new Location(world, 2, 64, 0));
            renderer.flush();
            require(tracker.getViewers() == savedViewers, "Moving within range replaces player snapshot");
            tracker.setRange(31);
            renderer.flush();
            require(((SceneTracker) tracker).snapshot().members() == membership.members(), "Settings change copies unchanged entity membership");
            require(tracker.getViewers() == savedViewers, "Unchanged audience replaces player snapshot after settings change");
            position.set(new Location(world, 100, 64, 0));
            renderer.flush();
            require(first.getViewers().isEmpty() && packets.size() == 2, "Out-of-range viewers remain");
            require(tracker.getViewers().isEmpty(), "Out-of-range tracker viewer retained");
            require(savedViewers.equals(Set.of(viewer)) && savedIterator.next() == viewer && !savedIterator.hasNext(), "Published snapshot or iterator changed during refresh");
            first.close();
            require(tracker.size() == 1, "Closed entity retained by tracker");
            position.set(new Location(world, 1, 64, 0));
            packets.clear();
            renderer.flush();
            require(packets.size() == 2, "Re-entry snapshot missing");
            var other = renderer.createTracker(new Location(world, 0, 64, 0), 32);
            boolean rejected = false;
            try {
                other.add(second);
            } catch (IllegalArgumentException expected) {
                rejected = true;
            }
            require(rejected, "Two trackers can fight over one entity");
            tracker.getCenter().setX(999);
            require(tracker.getCenter().getX() == 0, "Tracker center alias");
            var effects = renderer.createTracker(new Location(world, 0, 64, 0), 32);
            renderer.flush();
            require(effects.size() == 0 && effects.getViewers().equals(Set.of(viewer)), "Empty effects-only tracker has no viewers");
            effects.setRange(0);
            renderer.flush();
            require(effects.getViewers().isEmpty(), "Effects-only tracker ignores range changes");
            effects.setRange(32);
            renderer.flush();
            effects.close();
            require(effects.getViewers().isEmpty(), "Closed tracker retains players");
            ((SceneTracker) effects).viewers(Set.of(viewer));
            require(effects.getViewers().isEmpty(), "Late transport snapshot resurrected a closed tracker");
            tracker.close();
            require(tracker.getViewers().isEmpty(), "Tracker close must clear player snapshot immediately");
            renderer.flush();
            require(second.getViewers().isEmpty(), "Tracker close did not hide members");
            // The transport must remove old routes before assigning an entity to its new scene.
            other.add(second);
            renderer.flush();
            require(second.getViewers().size() == 1, "Transferred entity lost its new scene route");
            other.remove(second);
            var distant = renderer.createTracker(new Location(world, 100, 64, 0), 5);
            distant.add(second);
            renderer.flush();
            require(second.getViewers().isEmpty(), "Transferred entity retained the previous scene audience");
            audienceSnapshots(renderer, world, viewer);
            initializationBeforeAdmission(renderer, world, packets);
        }
        Bukkit.getLogger().info("ENTITY_RENDER_TRACKERS_PASS");
    }

    private static void initializationBeforeAdmission(PaperEntityRenderer renderer, World world, List<Packet<?>> packets) throws Exception {
        var tracker = (SceneTracker) renderer.createTracker(new Location(world, 0, 64, 0), 32);
        var item = renderer.createItem(new Location(world, 0, 64, 0));
        renderer.flush(); // Publish the empty, invisible item first.
        packets.clear();
        item.setSilent(true); // Ensure a frame is pending before the racing flush starts.
        var failure = new AtomicReference<Throwable>();
        Thread flushing;
        synchronized (tracker) {
            flushing = Thread.ofPlatform().start(() -> {
                try {
                    renderer.flush();
                } catch (Throwable error) {
                    failure.set(error);
                }
            });
            // Hold membership capture while initialization races the flush. This used
            // to publish an old empty item frame with newly attached membership.
            long deadline = System.nanoTime() + java.util.concurrent.TimeUnit.SECONDS.toNanos(5);
            var threads = java.lang.management.ManagementFactory.getThreadMXBean();
            while (true) {
                var info = threads.getThreadInfo(flushing.threadId());
                if (info != null && info.getThreadState() == Thread.State.BLOCKED && info.getLockInfo() != null
                        && info.getLockInfo().getIdentityHashCode() == System.identityHashCode(tracker))
                    break;
                require(flushing.isAlive() && System.nanoTime() < deadline, "Flush did not reach membership capture");
                Thread.sleep(1);
            }
            item.setItem(org.bukkit.inventory.ItemStack.of(Material.GUNPOWDER));
            item.setGravity(false);
            tracker.add(item);
        }
        flushing.join(5000);
        require(!flushing.isAlive() && failure.get() == null, "Racing flush failed: " + failure.get());
        var data = packets.stream().filter(packet -> packet instanceof net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket)
                .map(packet -> (net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket) packet)
                .filter(packet -> packet.id() == item.getId()).findFirst().orElseThrow();
        var key = dev.by1337.entityrender.core.metadata.GeneratedMetadata.item.ItemEntity.DATA_ITEM;
        var value = data.packedItems().stream().filter(entry -> entry.id() == key.index()).findFirst().orElseThrow().value();
        require(value instanceof net.minecraft.world.item.ItemStack stack && stack.is(net.minecraft.world.item.Items.GUNPOWDER), "First admitted frame contains an empty item");
        item.close();
        tracker.close();
    }

    private static void audienceSnapshots(PaperEntityRenderer renderer, World world, Player first) {
        var tracker = (SceneTracker) renderer.createTracker(new Location(world, 0, 64, 0), 32);
        var audience = new SceneAudience();
        var directory = new PlayerDirectory();
        var id = first.getUniqueId();
        directory.replace(List.of(new PlayerDirectory.Sample(first, id, world, 1, 64, 0, 1, Set.of())));
        audience.update(tracker.snapshot(), directory);
        var original = tracker.getViewers();
        require(original.contains(first), "Effects audience incorrectly requires an entity chunk");
        directory.replace(List.of(new PlayerDirectory.Sample(first, id, world, 2, 64, 0, 1, Set.of())));
        audience.update(tracker.snapshot(), directory);
        require(tracker.getViewers() == original, "Unchanged session/membership allocates a snapshot");
        directory.replace(List.of(new PlayerDirectory.Sample(first, id, world, 2, 64, 0, 2, Set.of())));
        audience.update(tracker.snapshot(), directory);
        require(tracker.getViewers() != original, "Session replacement does not publish a new snapshot");
        Player replacement = (Player) Proxy.newProxyInstance(Player.class.getClassLoader(), new Class<?>[]{ Player.class }, (proxy, method, args) -> switch (method.getName()) {
            case "getUniqueId" -> id;
            case "hashCode" -> 3;
            case "equals" -> proxy == args[0];
            case "toString" -> "Replacement tracker viewer";
            default -> throw new UnsupportedOperationException(method.toString());
        });
        directory.replace(List.of(new PlayerDirectory.Sample(replacement, id, world, 2, 64, 0, 3, Set.of())));
        audience.update(tracker.snapshot(), directory);
        require(tracker.getViewers().equals(Set.of(replacement)) && original.equals(Set.of(first)), "Reconnect mutated an old snapshot or retained the old Player");
        var failure = new AtomicReference<Throwable>();
        var running = new java.util.concurrent.atomic.AtomicBoolean(true);
        var reader = Thread.ofVirtual().start(() -> {
            try {
                while (running.get()) {
                    var snapshot = tracker.getViewers();
                    var before = snapshot.toArray();
                    Thread.yield();
                    require(Arrays.equals(before, snapshot.toArray()), "Concurrent publication mutated a reader's snapshot");
                    for (var player : tracker) require(player == first || player == replacement, "Unexpected published viewer");
                }
            } catch (Throwable error) {
                failure.set(error);
            }
        });
        try {
            for (int i = 0; i < 2000; i++) {
                var sample = new PlayerDirectory.Sample((i & 1) == 0 ? first : replacement, id, world, 2, 64, 0, i + 4, Set.of());
                sample.valid().set(i % 3 != 0);
                directory.replace(List.of(sample));
                audience.update(tracker.snapshot(), directory);
                require(tracker.getViewers().isEmpty() == !sample.valid().get(), "Invalid session remains in scene audience");
            }
        } finally {
            running.set(false);
            try {
                reader.join(10000);
            } catch (InterruptedException error) {
                Thread.currentThread().interrupt();
                throw new AssertionError(error);
            }
        }
        require(!reader.isAlive() && failure.get() == null, "Concurrent audience reader failed: " + failure.get());
        directory.replace(List.of());
        audience.update(tracker.snapshot(), directory);
        require(tracker.getViewers().isEmpty() && audience.ids().isEmpty(), "Departed player retained by scene");
        tracker.close();
    }

    private static void require(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
}
