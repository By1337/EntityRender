package dev.by1337.entityrender.api.entity;

import org.bukkit.entity.Display;
import org.bukkit.util.Transformation;

public interface VirtualDisplay extends VirtualEntity {
    Transformation getTransformation();

    void setTransformation(Transformation transformation);

    Display.Billboard getBillboard();

    void setBillboard(Display.Billboard billboard);

    int getInterpolationDelay();

    /** Set even to the same value to restart client transformation interpolation. */
    void setInterpolationDelay(int ticks);

    int getInterpolationDuration();

    void setInterpolationDuration(int ticks);

    int getTeleportDuration();

    void setTeleportDuration(int ticks);

    float getViewRange();

    void setViewRange(float range);

    Display.Brightness getBrightness();

    /** null restores world lighting. */
    void setBrightness(Display.Brightness brightness);
}
