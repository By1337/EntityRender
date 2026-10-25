package dev.by1337.entityrender.core;

import dev.by1337.entityrender.api.entity.VirtualDisplay;
import dev.by1337.entityrender.core.metadata.MetadataLayout;
import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Transformation;
import org.joml.*;
import java.util.Objects;
import dev.by1337.entityrender.core.metadata.GeneratedMetadata;

abstract class AbstractDisplayEntity extends BaseVirtualEntity implements VirtualDisplay {
    AbstractDisplayEntity(PaperEntityRenderer owner, int id, Location location, EntityType type,
        net.minecraft.world.entity.EntityType<?> nmsType, MetadataLayout layout) {
        super(owner, id, location, type, nmsType, layout);
    }

    @Override
    public Transformation getTransformation() {
        return new Transformation(new Vector3f(get(GeneratedMetadata.Display.DATA_TRANSLATION_ID)), new Quaternionf(get(GeneratedMetadata.Display.DATA_LEFT_ROTATION_ID)), new Vector3f(get(GeneratedMetadata.Display.DATA_SCALE_ID)), new Quaternionf(get(GeneratedMetadata.Display.DATA_RIGHT_ROTATION_ID)));
    }

    @Override
    public void setTransformation(Transformation transformation) {
        checkOpen();
        Objects.requireNonNull(transformation);
        var translation = new Vector3f(transformation.getTranslation());
        var scale = new Vector3f(transformation.getScale());
        var left = new Quaternionf(transformation.getLeftRotation());
        var right = new Quaternionf(transformation.getRightRotation());
        if (!translation.isFinite() || !scale.isFinite() || !left.isFinite() || !right.isFinite()
                || left.lengthSquared() == 0 || right.lengthSquared() == 0)
            throw new IllegalArgumentException("Invalid display transformation");
        set(GeneratedMetadata.Display.DATA_TRANSLATION_ID, translation);
        set(GeneratedMetadata.Display.DATA_SCALE_ID, scale);
        set(GeneratedMetadata.Display.DATA_LEFT_ROTATION_ID, left);
        set(GeneratedMetadata.Display.DATA_RIGHT_ROTATION_ID, right);
    }

    @Override
    public Display.Billboard getBillboard() {
        return switch (get(GeneratedMetadata.Display.DATA_BILLBOARD_RENDER_CONSTRAINTS_ID)) {
            case 0 -> Display.Billboard.FIXED;
            case 1 -> Display.Billboard.VERTICAL;
            case 2 -> Display.Billboard.HORIZONTAL;
            case 3 -> Display.Billboard.CENTER;
            default -> throw new IllegalStateException("Invalid billboard metadata");
        };
    }

    @Override
    public void setBillboard(Display.Billboard billboard) {
        byte value = switch (Objects.requireNonNull(billboard)) {
            case FIXED -> 0;
            case VERTICAL -> 1;
            case HORIZONTAL -> 2;
            case CENTER -> 3;
        };
        set(GeneratedMetadata.Display.DATA_BILLBOARD_RENDER_CONSTRAINTS_ID, value);
    }

    @Override
    public int getInterpolationDelay() {
        return get(GeneratedMetadata.Display.DATA_TRANSFORMATION_INTERPOLATION_START_DELTA_TICKS_ID);
    }

    @Override
    public void setInterpolationDelay(int ticks) {
        checkOpen();
        metadata.set(GeneratedMetadata.Display.DATA_TRANSFORMATION_INTERPOLATION_START_DELTA_TICKS_ID, ticks, true);
        owner.enqueue(this);
    }

    @Override
    public int getInterpolationDuration() {
        return get(GeneratedMetadata.Display.DATA_TRANSFORMATION_INTERPOLATION_DURATION_ID);
    }

    @Override
    public void setInterpolationDuration(int ticks) {
        if (ticks < 0) throw new IllegalArgumentException("Negative interpolation duration");
        set(GeneratedMetadata.Display.DATA_TRANSFORMATION_INTERPOLATION_DURATION_ID, ticks);
    }

    @Override
    public int getTeleportDuration() {
        return get(GeneratedMetadata.Display.DATA_POS_ROT_INTERPOLATION_DURATION_ID);
    }

    @Override
    public void setTeleportDuration(int ticks) {
        if (ticks < 0 || ticks > 59) throw new IllegalArgumentException("Teleport duration must be 0..59");
        set(GeneratedMetadata.Display.DATA_POS_ROT_INTERPOLATION_DURATION_ID, ticks);
    }

    @Override
    public float getViewRange() {
        return get(GeneratedMetadata.Display.DATA_VIEW_RANGE_ID);
    }

    @Override
    public void setViewRange(float range) {
        finite(range);
        if (range < 0) throw new IllegalArgumentException("Negative view range");
        set(GeneratedMetadata.Display.DATA_VIEW_RANGE_ID, range);
    }

    @Override
    public Display.Brightness getBrightness() {
        int value = get(GeneratedMetadata.Display.DATA_BRIGHTNESS_OVERRIDE_ID);
        return value == -1 ? null : new Display.Brightness((value >> 4) & 15, (value >> 20) & 15);
    }

    @Override
    public void setBrightness(Display.Brightness value) {
        set(GeneratedMetadata.Display.DATA_BRIGHTNESS_OVERRIDE_ID, value == null ? -1 : value.getBlockLight() << 4 | value.getSkyLight() << 20);
    }
}
