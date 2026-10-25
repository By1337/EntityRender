package dev.by1337.entityrender.core;

import dev.by1337.entityrender.EntityRender;
import dev.by1337.entityrender.core.metadata.*;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.inventory.*;
import org.bukkit.plugin.java.JavaPlugin;
import java.nio.file.*;
import java.util.*;

/** Ephemeral integration plugin; never included in the runtime artifacts. */
public final class PaperIntegrationPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getScheduler().runTask(this, () -> {
            try {
                RuntimeChecks.main(new String[0]);
                live();
                Files.writeString(Path.of("entityrender-test.result"), "PASS\n");
                getLogger().info("ENTITY_RENDER_PAPER_TEST_PASS");
            } catch (Throwable failure) {
                getLogger().log(java.util.logging.Level.SEVERE, "EntityRender Paper integration failed", failure);
                try {
                    Files.writeString(Path.of("entityrender-test.result"), "FAIL " + failure + "\n");
                } catch (Exception e) {
                    failure.addSuppressed(e);
                }
            } finally {
                getServer().shutdown();
            }
        });
    }

    private static void require(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }

    private void live() {
        World world = getServer().getWorlds().getFirst();
        int before = world.getEntities().size();
        try (var renderer = EntityRender.createManual(this)) {
            var stand = renderer.createArmorStand(new Location(world, 0, 64, 0));
            stand.setCustomName(Component.text("Приз 👀"));
            require(stand.getCustomName().equals(Component.text("Приз 👀")), "Adventure conversion failed");
            var item = new ItemStack(Material.DIAMOND, 3);
            stand.setEquipment(EquipmentSlot.HEAD, item);
            item.setAmount(9);
            require(stand.getEquipment(EquipmentSlot.HEAD).getAmount() == 3, "Equipment input alias");
            stand.getEquipment(EquipmentSlot.HEAD).setAmount(99);
            require(stand.getEquipment(EquipmentSlot.HEAD).getAmount() == 3, "Equipment getter alias");
            stand.setEquipment(EquipmentSlot.HEAD, null);
            require(stand.getEquipment(EquipmentSlot.HEAD) == null, "Equipment clear failed");
            var display = renderer.createTextDisplay(new Location(world, 0, 65, 0));
            display.setText(Component.text("Награда"));
            require(display.getText().equals(Component.text("Награда")), "Text component conversion failed");
            var itemDisplay = renderer.createItemDisplay(new Location(world, 0, 65, 1));
            var displayed = new ItemStack(Material.DIAMOND, 2);
            itemDisplay.setItemStack(displayed);
            displayed.setAmount(5);
            require(itemDisplay.getItemStack().getAmount() == 2, "ItemDisplay input alias");
            itemDisplay.getItemStack().setAmount(9);
            require(itemDisplay.getItemStack().getAmount() == 2, "ItemDisplay getter alias");
            for (var transform : org.bukkit.entity.ItemDisplay.ItemDisplayTransform.values()) {
                itemDisplay.setItemDisplayTransform(transform);
                require(itemDisplay.getItemDisplayTransform() == transform, "Item display transform mapping failed");
            }
            var blockDisplay = renderer.createBlockDisplay(new Location(world, 0, 65, 2));
            var block = Bukkit.createBlockData("minecraft:oak_log[axis=x]");
            blockDisplay.setBlockData(block);
            ((org.bukkit.block.data.Orientable) block).setAxis(Axis.Z);
            require(((org.bukkit.block.data.Orientable) blockDisplay.getBlockData()).getAxis() == Axis.X, "Block data input alias");
            ((org.bukkit.block.data.Orientable) blockDisplay.getBlockData()).setAxis(Axis.Y);
            require(((org.bukkit.block.data.Orientable) blockDisplay.getBlockData()).getAxis() == Axis.X, "Block data getter alias");
            renderer.flush();
            require(world.getEntities().size() == before, "Virtual entities were added to the world");
            int nativeId = Bukkit.getUnsafe().nextEntityId();
            require(nativeId != stand.getId() && nativeId != display.getId() && stand.getId() != display.getId(), "Entity IDs collide with server allocator");
        }
        // Compare actual initialized NMS metadata with the compact defaults.
        var nativeStand = new net.minecraft.world.entity.decoration.ArmorStand(net.minecraft.world.entity.EntityType.ARMOR_STAND, ((CraftWorld) world).getHandle());
        var nativeText = new net.minecraft.world.entity.Display.TextDisplay(net.minecraft.world.entity.EntityType.TEXT_DISPLAY, ((CraftWorld) world).getHandle());
        compareDefaults("ArmorStand", nativeStand.getEntityData().packAll(), new MetadataState(GeneratedMetadata.decoration.ArmorStand.LAYOUT).fullSnapshot());
        compareDefaults("TextDisplay", nativeText.getEntityData().packAll(), new MetadataState(GeneratedMetadata.Display.TextDisplay.LAYOUT).fullSnapshot());
        var nativeItem = new net.minecraft.world.entity.Display.ItemDisplay(net.minecraft.world.entity.EntityType.ITEM_DISPLAY, ((CraftWorld) world).getHandle());
        var nativeBlock = new net.minecraft.world.entity.Display.BlockDisplay(net.minecraft.world.entity.EntityType.BLOCK_DISPLAY, ((CraftWorld) world).getHandle());
        compareDefaults("ItemDisplay", nativeItem.getEntityData().packAll(), new MetadataState(GeneratedMetadata.Display.ItemDisplay.LAYOUT).fullSnapshot());
        compareDefaults("BlockDisplay", nativeBlock.getEntityData().packAll(), new MetadataState(GeneratedMetadata.Display.BlockDisplay.LAYOUT).fullSnapshot());
    }

    private static void compareDefaults(String name, List<net.minecraft.network.syncher.SynchedEntityData.DataValue<?>> nativeValues,
        List<net.minecraft.network.syncher.SynchedEntityData.DataValue<?>> compact) {
        require(nativeValues.size() == compact.size(), name + " metadata size");
        for (int i = 0; i < compact.size(); i++) require(Objects.equals(nativeValues.get(i).value(), compact.get(i).value()),
                name + " default mismatch at " + i + ": NMS=" + nativeValues.get(i).value() + ", compact=" + compact.get(i).value());
    }
}
