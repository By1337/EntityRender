package dev.by1337.entityrender.api.entity;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.TextDisplay;

public interface VirtualTextDisplay extends VirtualDisplay {
    Component getText();

    void setText(Component text);

    int getLineWidth();

    void setLineWidth(int width);

    int getBackgroundColor();

    /** Packed ARGB, including alpha. */
    void setBackgroundColor(int argb);

    byte getTextOpacity();

    void setTextOpacity(byte opacity);

    boolean isShadowed();

    void setShadowed(boolean shadow);

    boolean isSeeThrough();

    void setSeeThrough(boolean seeThrough);

    boolean isDefaultBackground();

    void setDefaultBackground(boolean defaultBackground);

    TextDisplay.TextAlignment getAlignment();

    void setAlignment(TextDisplay.TextAlignment alignment);
}
