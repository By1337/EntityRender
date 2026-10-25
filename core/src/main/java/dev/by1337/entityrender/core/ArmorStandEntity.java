package dev.by1337.entityrender.core;

import dev.by1337.entityrender.api.entity.VirtualArmorStand;
import dev.by1337.entityrender.core.metadata.MetadataKey;
import net.minecraft.core.Rotations;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.util.EulerAngle;
import java.util.*;
import dev.by1337.entityrender.core.metadata.GeneratedMetadata;

final class ArmorStandEntity extends BaseVirtualEntity implements VirtualArmorStand {

    ArmorStandEntity(PaperEntityRenderer owner, int id, Location location) {
        super(owner, id, location, EntityType.ARMOR_STAND, net.minecraft.world.entity.EntityTypes.ARMOR_STAND, GeneratedMetadata.decoration.ArmorStand.LAYOUT);
    }

    @Override
    public boolean isSmall() {
        return flag(GeneratedMetadata.decoration.ArmorStand.DATA_CLIENT_FLAGS, 1);
    }

    @Override
    public void setSmall(boolean value) {
        flag(GeneratedMetadata.decoration.ArmorStand.DATA_CLIENT_FLAGS, 1, value);
    }

    @Override
    public boolean hasArms() {
        return flag(GeneratedMetadata.decoration.ArmorStand.DATA_CLIENT_FLAGS, 4);
    }

    @Override
    public void setArms(boolean value) {
        flag(GeneratedMetadata.decoration.ArmorStand.DATA_CLIENT_FLAGS, 4, value);
    }

    @Override
    public boolean hasBasePlate() {
        return !flag(GeneratedMetadata.decoration.ArmorStand.DATA_CLIENT_FLAGS, 8);
    }

    @Override
    public void setBasePlate(boolean value) {
        flag(GeneratedMetadata.decoration.ArmorStand.DATA_CLIENT_FLAGS, 8, !value);
    }

    @Override
    public boolean isMarker() {
        return flag(GeneratedMetadata.decoration.ArmorStand.DATA_CLIENT_FLAGS, 16);
    }

    @Override
    public void setMarker(boolean value) {
        flag(GeneratedMetadata.decoration.ArmorStand.DATA_CLIENT_FLAGS, 16, value);
    }

    private EulerAngle pose(MetadataKey<Rotations> key) {
        var value = get(key);
        return new EulerAngle(Math.toRadians(value.x()), Math.toRadians(value.y()), Math.toRadians(value.z()));
    }

    private void pose(MetadataKey<Rotations> key, EulerAngle value) {
        Objects.requireNonNull(value);
        finite(value.getX());
        finite(value.getY());
        finite(value.getZ());
        float x = (float) Math.toDegrees(value.getX()), y = (float) Math.toDegrees(value.getY()), z = (float) Math.toDegrees(value.getZ());
        finite(x);
        finite(y);
        finite(z);
        set(key, new Rotations(x, y, z));
    }

    @Override
    public EulerAngle getHeadPose() {
        return pose(GeneratedMetadata.decoration.ArmorStand.DATA_HEAD_POSE);
    }

    @Override
    public void setHeadPose(EulerAngle pose) {
        pose(GeneratedMetadata.decoration.ArmorStand.DATA_HEAD_POSE, pose);
    }

    @Override
    public EulerAngle getBodyPose() {
        return pose(GeneratedMetadata.decoration.ArmorStand.DATA_BODY_POSE);
    }

    @Override
    public void setBodyPose(EulerAngle pose) {
        pose(GeneratedMetadata.decoration.ArmorStand.DATA_BODY_POSE, pose);
    }

    @Override
    public EulerAngle getLeftArmPose() {
        return pose(GeneratedMetadata.decoration.ArmorStand.DATA_LEFT_ARM_POSE);
    }

    @Override
    public void setLeftArmPose(EulerAngle pose) {
        pose(GeneratedMetadata.decoration.ArmorStand.DATA_LEFT_ARM_POSE, pose);
    }

    @Override
    public EulerAngle getRightArmPose() {
        return pose(GeneratedMetadata.decoration.ArmorStand.DATA_RIGHT_ARM_POSE);
    }

    @Override
    public void setRightArmPose(EulerAngle pose) {
        pose(GeneratedMetadata.decoration.ArmorStand.DATA_RIGHT_ARM_POSE, pose);
    }

    @Override
    public EulerAngle getLeftLegPose() {
        return pose(GeneratedMetadata.decoration.ArmorStand.DATA_LEFT_LEG_POSE);
    }

    @Override
    public void setLeftLegPose(EulerAngle pose) {
        pose(GeneratedMetadata.decoration.ArmorStand.DATA_LEFT_LEG_POSE, pose);
    }

    @Override
    public EulerAngle getRightLegPose() {
        return pose(GeneratedMetadata.decoration.ArmorStand.DATA_RIGHT_LEG_POSE);
    }

    @Override
    public void setRightLegPose(EulerAngle pose) {
        pose(GeneratedMetadata.decoration.ArmorStand.DATA_RIGHT_LEG_POSE, pose);
    }

    private static void validateSlot(EquipmentSlot slot) {
        switch (Objects.requireNonNull(slot)) {
            case HAND, OFF_HAND, FEET, LEGS, CHEST, HEAD -> {
            }
            default -> throw new IllegalArgumentException("Unsupported armor stand equipment slot: " + slot);
        }
    }

    @Override
    public ItemStack getEquipment(EquipmentSlot slot) {
        validateSlot(slot);
        return super.getEquipment(slot);
    }

    @Override
    public void setEquipment(EquipmentSlot slot, ItemStack item) {
        validateSlot(slot);
        super.setEquipment(slot, item);
    }
}
