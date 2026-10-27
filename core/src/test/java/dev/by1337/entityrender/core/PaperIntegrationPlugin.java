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
                peacefulPrototypes();
                live();
                AsyncChecks.run(this).whenComplete((ignored, error) -> {
                    getServer().getScheduler().runTask(this, () -> finish(error));
                });
            } catch (Throwable failure) {
                finish(failure);
            }
        });
    }

    private void finish(Throwable failure) {
        try {
            if (failure == null) {
                Files.writeString(Path.of("entityrender-test.result"), "PASS\n");
                getLogger().info("ENTITY_RENDER_PAPER_TEST_PASS");
            } else {
                getLogger().log(java.util.logging.Level.SEVERE, "EntityRender Paper integration failed", failure);
                Files.writeString(Path.of("entityrender-test.result"), "FAIL " + failure + "\n");
            }
        } catch (Exception error) {
            getLogger().log(java.util.logging.Level.SEVERE, "Cannot write integration result", error);
        } finally {
            getServer().shutdown();
        }
    }

    private static void require(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }

    private void peacefulPrototypes() throws Exception {
        var world = getServer().getWorlds().getFirst();
        var difficulty = world.getDifficulty();
        int before = world.getEntities().size();
        world.setDifficulty(Difficulty.PEACEFUL);
        try (var renderer = EntityRender.createManual(this)) {
            var position = new Location(world, 0, 64, 0);
            var creeper = renderer.createCreeper(position);
            creeper.setPowered(true);
            require(creeper.isPowered(), "Peaceful creeper metadata failed");
            renderer.createZombie(position);
            renderer.createSkeleton(position);
            renderer.flush();
            require(world.getDifficulty() == Difficulty.PEACEFUL, "Renderer changed world difficulty");
            require(world.getEntities().size() == before, "Prototypes registered real world entities");
        } finally {
            world.setDifficulty(difficulty);
        }
    }

    private void live() throws Exception {
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
            int nativeId = Bukkit.getUnsafe().nextEntityId(world);
            require(nativeId != stand.getId() && nativeId != display.getId() && stand.getId() != display.getId(), "Entity IDs collide with server allocator");
        }
        // Compare actual initialized NMS metadata with the compact defaults.
        var nativeStand = new net.minecraft.world.entity.decoration.ArmorStand(net.minecraft.world.entity.EntityTypes.ARMOR_STAND, ((CraftWorld) world).getHandle());
        var nativeText = new net.minecraft.world.entity.Display.TextDisplay(net.minecraft.world.entity.EntityTypes.TEXT_DISPLAY, ((CraftWorld) world).getHandle());
        compareDefaults("ArmorStand", nativeStand.getEntityData().packAll(), new MetadataState(GeneratedMetadata.decoration.ArmorStand.LAYOUT).fullSnapshot());
        compareDefaults("TextDisplay", nativeText.getEntityData().packAll(), new MetadataState(GeneratedMetadata.Display.TextDisplay.LAYOUT).fullSnapshot());
        var nativeItem = new net.minecraft.world.entity.Display.ItemDisplay(net.minecraft.world.entity.EntityTypes.ITEM_DISPLAY, ((CraftWorld) world).getHandle());
        var nativeBlock = new net.minecraft.world.entity.Display.BlockDisplay(net.minecraft.world.entity.EntityTypes.BLOCK_DISPLAY, ((CraftWorld) world).getHandle());
        compareDefaults("ItemDisplay", nativeItem.getEntityData().packAll(), new MetadataState(GeneratedMetadata.Display.ItemDisplay.LAYOUT).fullSnapshot());
        compareDefaults("BlockDisplay", nativeBlock.getEntityData().packAll(), new MetadataState(GeneratedMetadata.Display.BlockDisplay.LAYOUT).fullSnapshot());
        AllEntitiesChecks.run(world);
        InteractionChecks.run(world);
        TrackerChecks.run(world);
    }

    private static void compareDefaults(String name, List<net.minecraft.network.syncher.SynchedEntityData.DataValue<?>> nativeValues,
        List<net.minecraft.network.syncher.SynchedEntityData.DataValue<?>> compact) {
        require(nativeValues.size() == compact.size(), name + " metadata size");
        for (int i = 0; i < compact.size(); i++) require(Objects.equals(nativeValues.get(i).value(), compact.get(i).value()),
                name + " default mismatch at " + i + ": NMS=" + nativeValues.get(i).value() + ", compact=" + compact.get(i).value());
    }
}
