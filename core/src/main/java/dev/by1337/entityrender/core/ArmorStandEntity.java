package dev.by1337.entityrender.core;

import com.mojang.datafixers.util.Pair;
import dev.by1337.entityrender.api.entity.VirtualArmorStand;
import dev.by1337.entityrender.core.metadata.MetadataKey;
import net.minecraft.core.Rotations;
import net.minecraft.network.protocol.game.ClientboundSetEquipmentPacket;
import org.bukkit.Location;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.util.EulerAngle;
import java.util.*;
import dev.by1337.entityrender.core.metadata.GeneratedMetadata;

final class ArmorStandEntity extends BaseVirtualEntity implements VirtualArmorStand {
    private static final net.minecraft.world.entity.EquipmentSlot[] SLOTS = {
            net.minecraft.world.entity.EquipmentSlot.MAINHAND, net.minecraft.world.entity.EquipmentSlot.OFFHAND,
            net.minecraft.world.entity.EquipmentSlot.FEET, net.minecraft.world.entity.EquipmentSlot.LEGS,
            net.minecraft.world.entity.EquipmentSlot.CHEST, net.minecraft.world.entity.EquipmentSlot.HEAD };
    private final net.minecraft.world.item.ItemStack[] equipment = new net.minecraft.world.item.ItemStack[6];
    private int equipmentDirty;

    ArmorStandEntity(PaperEntityRenderer owner, int id, Location location) {
        super(owner, id, location, EntityType.ARMOR_STAND, net.minecraft.world.entity.EntityType.ARMOR_STAND, GeneratedMetadata.decoration.ArmorStand.LAYOUT);
        Arrays.fill(equipment, net.minecraft.world.item.ItemStack.EMPTY);
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
        return new EulerAngle(Math.toRadians(value.getX()), Math.toRadians(value.getY()), Math.toRadians(value.getZ()));
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

    private int slot(EquipmentSlot slot) {
        return switch (Objects.requireNonNull(slot)) {
            case HAND -> 0;
            case OFF_HAND -> 1;
            case FEET -> 2;
            case LEGS -> 3;
            case CHEST -> 4;
            case HEAD -> 5;
            default -> throw new IllegalArgumentException("Unsupported armor stand equipment slot: " + slot);
        };
    }

    @Override
    public ItemStack getEquipment(EquipmentSlot slot) {
        checkOpen();
        var item = equipment[slot(slot)];
        return item.isEmpty() ? null : CraftItemStack.asBukkitCopy(item);
    }

    @Override
    public void setEquipment(EquipmentSlot slot, ItemStack item) {
        checkOpen();
        int index = slot(slot);
        var snapshot = item == null ? net.minecraft.world.item.ItemStack.EMPTY : CraftItemStack.asNMSCopy(item);
        if (net.minecraft.world.item.ItemStack.matches(equipment[index], snapshot)) return;
        equipment[index] = snapshot;
        equipmentDirty |= 1 << index;
        owner.enqueue(this);
    }

    private ClientboundSetEquipmentPacket equipmentPacket(int mask) {
        var values = new ArrayList<Pair<net.minecraft.world.entity.EquipmentSlot, net.minecraft.world.item.ItemStack>>();
        for (int i = 0; i < equipment.length; i++) if ((mask & 1 << i) != 0) values.add(Pair.of(SLOTS[i], equipment[i].copy()));
        return new ClientboundSetEquipmentPacket(getId(), List.copyOf(values));
    }

    @Override
    protected void sendAdditionalSnapshot(Player player) {
        int mask = 0;
        for (int i = 0; i < equipment.length; i++) if (!equipment[i].isEmpty()) mask |= 1 << i;
        if (mask != 0) owner.send(player, equipmentPacket(mask));
    }

    @Override
    protected void flushAdditional() {
        if (equipmentDirty != 0) broadcast(equipmentPacket(equipmentDirty));
    }

    @Override
    protected void acknowledgeAdditional() {
        equipmentDirty = 0;
    }
}
