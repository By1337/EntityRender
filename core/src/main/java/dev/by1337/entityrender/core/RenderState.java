package dev.by1337.entityrender.core;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.*;
import net.minecraft.world.entity.PositionPath;
import net.minecraft.world.phys.Vec3;
import java.util.*;

/** All actual viewer state and packet construction belongs to one transport consumer. */
final class RenderState {
    private final PaperEntityRenderer owner;
    private final Map<Integer, Entry> entities = new HashMap<>();
    private List<SceneTracker.Snapshot> scenes = List.of();

    RenderState(PaperEntityRenderer owner) {
        this.owner = owner;
    }

    void accept(List<PublishedEntity> frames, List<SceneTracker.Snapshot> scenes) {
        this.scenes = scenes;
        for (var frame : frames) {
            var entry = entities.computeIfAbsent(frame.id(), ignored -> new Entry());
            entry.previous = entry.current;
            entry.current = frame;
            entry.changed = true;
        }
    }

    void render(PlayerDirectory directory) {
        var automatic = new HashMap<Integer, Set<UUID>>();
        for (var scene : scenes) {
            var settings = scene.settings();
            var center = settings.center();
            var index = directory.index(center.getWorld());
            var viewers = new HashSet<UUID>();
            var players = new HashSet<org.bukkit.entity.Player>();
            if (index != null) for (var sample : index.nearby(center, settings.range())) {
                if (sample.valid().get() && Math.hypot(Math.hypot(sample.x() - center.getX(), sample.z() - center.getZ()), sample.y() - center.getY()) <= settings.range()) {
                    viewers.add(sample.id());
                    players.add(sample.player());
                }
            }
            scene.tracker().viewers(players);
            for (int member : scene.members()) automatic.put(member, viewers);
        }
        RuntimeException failure = null;
        var iterator = entities.entrySet().iterator();
        while (iterator.hasNext()) {
            var entry = iterator.next().getValue();
            var frame = entry.current;
            try {
                Set<UUID> desired = frame.closed() ? Set.of() : automatic.getOrDefault(frame.id(), frame.requested().keySet());
                var oldIterator = entry.viewers.entrySet().iterator();
                while (oldIterator.hasNext()) {
                    var old = oldIterator.next();
                    var view = directory.get(old.getKey());
                    boolean sameSession = view != null && view.sample.epoch() == old.getValue();
                    if (!desired.contains(old.getKey()) || !sameSession || !view.ready(frame)) {
                        if (sameSession) owner.send(view.sample.player(), new ClientboundRemoveEntitiesPacket(frame.id()));
                        oldIterator.remove();
                    }
                }
                Set<UUID> newcomers = null;
                for (var id : desired) {
                    var view = directory.get(id);
                    if (view == null || !view.ready(frame) || entry.viewers.containsKey(id)) continue;
                    spawn(frame, view);
                    entry.viewers.put(id, view.sample.epoch());
                    if (newcomers == null) newcomers = new HashSet<>();
                    newcomers.add(id);
                }
                if (entry.changed || entry.repair) {
                    boolean respawn = entry.repair || entry.previous != null && (entry.previous.respawn() != frame.respawn() || entry.previous.spawnData() != frame.spawnData());
                    var updates = new ArrayList<Packet<?>>();
                    if (!respawn && entry.previous != null && !entry.previous.position().equals(frame.position())) {
                        var p = frame.position();
                        updates.add(new ClientboundEntityPositionSyncPacket(frame.id(), PositionPath.of(new Vec3(p.x(), p.y(), p.z())), p.yaw(), p.pitch(), p.onGround()));
                        if (frame.headChanged()) updates.add(owner.spawnPackets.head(frame.id(), p.yaw()));
                    }
                    if (!frame.changed().isEmpty()) updates.add(new ClientboundSetEntityDataPacket(frame.id(), frame.changed()));
                    if (frame.equipmentChanged() && !frame.equipment().isEmpty()) updates.add(new ClientboundSetEquipmentPacket(frame.id(), frame.equipment()));
                    for (var id : entry.viewers.keySet()) if (newcomers == null || !newcomers.contains(id)) {
                        var view = directory.get(id);
                        if (respawn) {
                            owner.send(view.sample.player(), new ClientboundRemoveEntitiesPacket(frame.id()));
                            spawn(frame, view);
                        } else for (var packet : updates) owner.send(view.sample.player(), packet);
                    }
                }
                entry.changed = false;
                entry.repair = false;
                if (frame.closed()) iterator.remove();
            } catch (RuntimeException error) {
                entry.repair = true;
                if (failure == null) failure = error;
                else failure.addSuppressed(error);
            } finally {
                frame.handle().viewers(entry.viewers.keySet());
            }
        }
        if (failure != null) throw failure;
    }

    private void spawn(PublishedEntity frame, PlayerDirectory.View view) {
        var p = frame.position();
        try {
            owner.send(view.sample.player(), owner.spawnPackets.create(frame.type(), frame.nativeType(), frame.id(), frame.uuid(), p.x(), p.y(), p.z(), p.pitch(), p.yaw(), frame.spawnData()));
            owner.send(view.sample.player(), new ClientboundSetEntityDataPacket(frame.id(), frame.metadata()));
            if (!frame.equipment().isEmpty()) owner.send(view.sample.player(), new ClientboundSetEquipmentPacket(frame.id(), frame.equipment()));
        } catch (RuntimeException failure) {
            try {
                owner.send(view.sample.player(), new ClientboundRemoveEntitiesPacket(frame.id()));
            } catch (RuntimeException cleanup) {
                failure.addSuppressed(cleanup);
            }
            throw failure;
        }
    }

    int size() {
        return entities.size();
    }

    PublishedEntity snapshot(int id) {
        var entry = entities.get(id);
        return entry == null ? null : entry.current;
    }

    void close(PlayerDirectory directory) {
        RuntimeException failure = null;
        for (var entry : entities.values()) {
            for (var viewer : entry.viewers.entrySet()) {
                var current = directory.get(viewer.getKey());
                if (current != null && current.sample.epoch() == viewer.getValue()) try {
                    owner.send(current.sample.player(), new ClientboundRemoveEntitiesPacket(entry.current.id()));
                } catch (RuntimeException error) {
                    if (failure == null) failure = error;
                    else failure.addSuppressed(error);
                }
            }
            entry.current.handle().viewers(Set.of());
        }
        entities.clear();
        scenes = List.of();
        owner.spawnPackets.close();
        if (failure != null) throw failure;
    }

    private static final class Entry {
        PublishedEntity current, previous;
        boolean changed, repair;
        final Map<UUID, Long> viewers = new HashMap<>();
    }
}
