package dev.by1337.entityrender.core;

import dev.by1337.entityrender.api.entity.VirtualTextDisplay;
import io.papermc.paper.adventure.PaperAdventure;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.*;
import java.util.Objects;
import dev.by1337.entityrender.core.metadata.GeneratedMetadata;

final class TextDisplayEntity extends AbstractDisplayEntity implements VirtualTextDisplay {
    TextDisplayEntity(PaperEntityRenderer owner, int id, Location location) {
        super(owner, id, location, EntityType.TEXT_DISPLAY, net.minecraft.world.entity.EntityType.TEXT_DISPLAY, GeneratedMetadata.Display.TextDisplay.LAYOUT);
    }

    @Override
    public Component getText() {
        return PaperAdventure.asAdventure(get(GeneratedMetadata.Display.TextDisplay.DATA_TEXT_ID));
    }

    @Override
    public void setText(Component text) {
        set(GeneratedMetadata.Display.TextDisplay.DATA_TEXT_ID, PaperAdventure.asVanilla(Objects.requireNonNull(text)));
    }

    @Override
    public int getLineWidth() {
        return get(GeneratedMetadata.Display.TextDisplay.DATA_LINE_WIDTH_ID);
    }

    @Override
    public void setLineWidth(int width) {
        if (width < 0) throw new IllegalArgumentException("Negative line width");
        set(GeneratedMetadata.Display.TextDisplay.DATA_LINE_WIDTH_ID, width);
    }

    @Override
    public int getBackgroundColor() {
        return get(GeneratedMetadata.Display.TextDisplay.DATA_BACKGROUND_COLOR_ID);
    }

    @Override
    public void setBackgroundColor(int argb) {
        set(GeneratedMetadata.Display.TextDisplay.DATA_BACKGROUND_COLOR_ID, argb);
    }

    @Override
    public byte getTextOpacity() {
        return get(GeneratedMetadata.Display.TextDisplay.DATA_TEXT_OPACITY_ID);
    }

    @Override
    public void setTextOpacity(byte opacity) {
        set(GeneratedMetadata.Display.TextDisplay.DATA_TEXT_OPACITY_ID, opacity);
    }

    @Override
    public boolean isShadowed() {
        return flag(GeneratedMetadata.Display.TextDisplay.DATA_STYLE_FLAGS_ID, 1);
    }

    @Override
    public void setShadowed(boolean value) {
        flag(GeneratedMetadata.Display.TextDisplay.DATA_STYLE_FLAGS_ID, 1, value);
    }

    @Override
    public boolean isSeeThrough() {
        return flag(GeneratedMetadata.Display.TextDisplay.DATA_STYLE_FLAGS_ID, 2);
    }

    @Override
    public void setSeeThrough(boolean value) {
        flag(GeneratedMetadata.Display.TextDisplay.DATA_STYLE_FLAGS_ID, 2, value);
    }

    @Override
    public boolean isDefaultBackground() {
        return flag(GeneratedMetadata.Display.TextDisplay.DATA_STYLE_FLAGS_ID, 4);
    }

    @Override
    public void setDefaultBackground(boolean value) {
        flag(GeneratedMetadata.Display.TextDisplay.DATA_STYLE_FLAGS_ID, 4, value);
    }

    @Override
    public TextDisplay.TextAlignment getAlignment() {
        byte value = get(GeneratedMetadata.Display.TextDisplay.DATA_STYLE_FLAGS_ID);
        return (value & 8) != 0 ? TextDisplay.TextAlignment.LEFT : (value & 16) != 0 ? TextDisplay.TextAlignment.RIGHT : TextDisplay.TextAlignment.CENTER;
    }

    @Override
    public void setAlignment(TextDisplay.TextAlignment alignment) {
        int mask = switch (Objects.requireNonNull(alignment)) {
            case CENTER -> 0;
            case LEFT -> 8;
            case RIGHT -> 16;
        };
        set(GeneratedMetadata.Display.TextDisplay.DATA_STYLE_FLAGS_ID, (byte) ((get(GeneratedMetadata.Display.TextDisplay.DATA_STYLE_FLAGS_ID) & ~24) | mask));
    }
}
