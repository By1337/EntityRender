package dev.by1337.entityrender.core;

import dev.by1337.entityrender.core.metadata.CapturedDefaults;
import dev.by1337.entityrender.core.metadata.MetadataLayout;
import net.minecraft.core.RegistryAccess;
import org.bukkit.World;
import org.bukkit.craftbukkit.CraftWorld;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/** Per-world registry bindings for values captured by the offline dumper. Never constructs NMS entities. */
final class WorldResources {
    final World world;
    final RegistryAccess registries;
    private volatile boolean valid = true;
    private final Map<org.bukkit.entity.EntityType, CapturedDefaults.Template> templates = new ConcurrentHashMap<>();

    WorldResources(World world) {
        this.world = world;
        registries = world instanceof CraftWorld craft ? craft.getHandle().registryAccess()
                : RegistryAccess.fromRegistryOfRegistries(net.minecraft.core.registries.BuiltInRegistries.REGISTRY);
    }

    void invalidate() {
        valid = false;
        templates.clear();
    }

    CapturedDefaults.Template template(org.bukkit.entity.EntityType type, MetadataLayout generated) {
        if (!valid) throw new IllegalStateException("World was unloaded");
        var result = templates.computeIfAbsent(type, ignored -> CapturedDefaults.load(type, generated, registries));
        if (!valid) throw new IllegalStateException("World was unloaded");
        return result;
    }

    int spawnData(org.bukkit.entity.EntityType type) {
        var entry = templates.get(type);
        return entry == null ? 0 : entry.spawnData();
    }
}
