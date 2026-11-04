package dev.by1337.entityrender.bukkit;

import dev.by1337.bpatch.PatchArchive;
import dev.by1337.core.ServerVersion;
import dev.by1337.core.plugin.BasePlugin;
import dev.by1337.core.util.reflect.ClasspathUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/** Loads only the API and implementation matching the running Paper server. */
public final class EntityRenderPlugin extends BasePlugin {
    private boolean runtimeLoaded;

    @Override
    public void onLoad() {
        super.onLoad();
        String version = ServerVersion.CURRENT_ID;
        try {
            String cacheVersion = getDescription().getVersion() + "\n"
                    + Files.getLastModifiedTime(getFile().toPath()).toMillis() + "\n" + version;
            Path directory = getDataFolder().toPath().resolve(".runtime");
            RuntimeCache.prepare(directory, cacheVersion);
            Path api = unpack("api", version, directory);
            Path core = unpack("core", version, directory);
            ClasspathUtil.addUrl(this, api);
            ClasspathUtil.addUrl(this, core);
            Class.forName("dev.by1337.entityrender.EntityRender", false, getClass().getClassLoader());
            runtimeLoaded = true;
            log.info("Loaded EntityRender runtime for Paper {}", version);
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalStateException("Unable to load EntityRender for Paper " + version, e);
        }
    }

    @Override
    public void onEnable() {
        if (!runtimeLoaded) {
            log.error("EntityRender runtime was not loaded; disabling plugin");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        super.onEnable();
    }

    private Path unpack(String module, String version, Path directory) throws IOException {
        Path target = directory.resolve(module + "+" + version + ".jar");
        if (Files.isRegularFile(target)) {
            return target;
        }
        try (var input = getResource("runtime/" + module + ".zip")) {
            if (input == null) {
                throw new IOException("Missing embedded " + module + " archive");
            }
            try (PatchArchive archive = PatchArchive.open(input)) {
                if (archive.versions().stream().noneMatch(entry -> entry.id().equals(version))) {
                    throw new IOException("Unsupported Paper version " + version + "; supported: "
                            + archive.versions().stream().map(PatchArchive.Version::id).toList());
                }
                archive.unpack(version, target);
                return target;
            }
        }
    }
}
