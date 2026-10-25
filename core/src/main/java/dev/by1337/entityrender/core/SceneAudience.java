package dev.by1337.entityrender.core;

import org.bukkit.entity.Player;
import java.util.*;
import java.util.function.Consumer;

/** Mutable visibility belongs only to the transport consumer; readers receive immutable arrays. */
final class SceneAudience implements Consumer<PlayerDirectory.Sample> {
    private final Map<UUID, Viewer> viewers = new HashMap<>();
    private boolean pass, changed;
    private SceneTracker.Snapshot scene;

    Set<UUID> ids() {
        return viewers.keySet();
    }

    void update(SceneTracker.Snapshot scene, PlayerDirectory directory) {
        this.scene = scene;
        pass = !pass;
        changed = false;
        var settings = scene.settings();
        var index = directory.index(settings.center().getWorld());
        if (index != null) index.forEachNearby(settings.center(), settings.range(), this);
        var iterator = viewers.values().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().seen != pass) {
                iterator.remove();
                changed = true;
            }
        }
        if (changed) {
            var players = new Player[viewers.size()];
            int indexInSnapshot = 0;
            for (var viewer : viewers.values()) players[indexInSnapshot++] = viewer.player;
            scene.tracker().viewers(players.length == 0 ? Set.of() : Collections.unmodifiableSet(new PlayerSet(players)));
        }
    }

    @Override
    public void accept(PlayerDirectory.Sample sample) {
        var settings = scene.settings();
        var center = settings.center();
        if (!sample.valid().get() || Math.hypot(Math.hypot(sample.x() - center.getX(), sample.z() - center.getZ()), sample.y() - center.getY()) > settings.range()) return;
        var viewer = viewers.get(sample.id());
        if (viewer == null) {
            viewer = new Viewer();
            viewers.put(sample.id(), viewer);
            changed = true;
        }
        if (viewer.player != sample.player() || viewer.epoch != sample.epoch()) changed = true;
        viewer.player = sample.player();
        viewer.epoch = sample.epoch();
        viewer.seen = pass;
    }

    private static final class Viewer {
        Player player;
        long epoch;
        boolean seen;
    }

    /** The owned array never escapes; the outer wrapper rejects every mutation method. */
    private static final class PlayerSet extends AbstractSet<Player> {
        private final Player[] players;

        PlayerSet(Player[] players) {
            this.players = players;
        }

        @Override
        public int size() {
            return players.length;
        }

        @Override
        public Iterator<Player> iterator() {
            return Arrays.asList(players).iterator();
        }

        @Override
        public void forEach(Consumer<? super Player> action) {
            Objects.requireNonNull(action);
            for (var player : players) action.accept(player);
        }
    }
}
