package dev.by1337.entityrender.api.entity;

import org.bukkit.inventory.*;
import org.bukkit.util.EulerAngle;

public interface VirtualArmorStand extends VirtualEntity {
    /** Queues a client arm swing; only HAND and OFF_HAND are accepted. */
    void swingArm(EquipmentSlot hand);

    boolean isSmall();

    void setSmall(boolean small);

    boolean hasArms();

    void setArms(boolean arms);

    boolean hasBasePlate();

    void setBasePlate(boolean basePlate);

    boolean isMarker();

    void setMarker(boolean marker);

    EulerAngle getHeadPose();

    void setHeadPose(EulerAngle pose);

    EulerAngle getBodyPose();

    void setBodyPose(EulerAngle pose);

    EulerAngle getLeftArmPose();

    void setLeftArmPose(EulerAngle pose);

    EulerAngle getRightArmPose();

    void setRightArmPose(EulerAngle pose);

    EulerAngle getLeftLegPose();

    void setLeftLegPose(EulerAngle pose);

    EulerAngle getRightLegPose();

    void setRightLegPose(EulerAngle pose);

    /** Returns an independent copy; null means empty. HAND/OFF_HAND/FEET/LEGS/CHEST/HEAD only. */
    ItemStack getEquipment(EquipmentSlot slot);

    void setEquipment(EquipmentSlot slot, ItemStack item);
}
