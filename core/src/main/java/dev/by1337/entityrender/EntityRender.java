package dev.by1337.entityrender;

import dev.by1337.entityrender.api.EntityRenderer;
import dev.by1337.entityrender.core.PaperEntityRenderer;
import org.bukkit.plugin.Plugin;

/** Version-specific Paper implementation entry point. This library is not a Bukkit plugin. */
public final class EntityRender {
    private EntityRender() {
    }

    /** Initialize on main; edits may run asynchronously and publish automatically each tick. */
    public static EntityRenderer create(Plugin owner) {
        return PaperEntityRenderer.create(owner, true);
    }

    /** Initialize on main; caller may publish from any thread. Cleanup on disable is automatic. */
    public static EntityRenderer createManual(Plugin owner) {
        return PaperEntityRenderer.create(owner, false);
    }
}
