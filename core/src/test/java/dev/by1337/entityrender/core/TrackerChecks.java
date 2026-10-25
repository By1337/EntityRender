package dev.by1337.entityrender.core;

import net.minecraft.network.protocol.Packet;
import org.bukkit.*;
import org.bukkit.entity.Player;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

final class TrackerChecks {
    static void run(World world) {
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
        }
        Bukkit.getLogger().info("ENTITY_RENDER_TRACKERS_PASS");
    }

    private static void require(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
}
