package dev.by1337.entityrender;

import dev.by1337.entityrender.api.EntityRenderer;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import org.bukkit.plugin.Plugin;

/** Paper 1.21.4 implementation entry point. This library is not a Bukkit plugin. */
public final class EntityRender {
    private EntityRender() {
    }

    /** One scheduled flush per tick, with automatic cleanup on plugin disable. */
    public static EntityRenderer create(Plugin owner) {
        return PaperEntityRenderer.create(owner, true);
    }

    /** Caller controls flush timing. Cleanup on plugin disable is still automatic. */
    public static EntityRenderer createManual(Plugin owner) {
        return PaperEntityRenderer.create(owner, false);
    }
}
