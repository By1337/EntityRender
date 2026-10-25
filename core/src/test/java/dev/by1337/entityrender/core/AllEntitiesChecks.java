package dev.by1337.entityrender.core;

import io.netty.buffer.Unpooled;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import java.lang.reflect.*;
import java.util.*;

final class AllEntitiesChecks {
    static void run(World world) throws Exception {
        int before = world.getEntities().size();
        var packets = new ArrayList<Packet<?>>();
        var renderer = new PaperEntityRenderer((player, packet) -> packets.add(packet), ignored -> Bukkit.getUnsafe().nextEntityId(world));
        Player viewer = (Player) Proxy.newProxyInstance(Player.class.getClassLoader(), new Class<?>[]{ Player.class }, (proxy, method, args) -> switch (method.getName()) {
            case "getWorld" -> world;
            case "isOnline" -> true;
            case "getUniqueId" -> new UUID(1, 1);
            case "hashCode" -> 1;
            case "equals" -> proxy == args[0];
            case "toString" -> "EntityRender viewer";
            default -> throw new UnsupportedOperationException(method.toString());
        });
        int types = 0, properties = 0;
        try (renderer) {
            for (EntityType type : Registry.ENTITY_TYPE) {
                if (Set.of("minecraft:player", "minecraft:fishing_bobber", "minecraft:marker").contains(type.getKey().toString())) continue;
                try {
                    var entity = (BaseVirtualEntity) renderer.create(type, new Location(world, 0, 64, 0));
                    int marker = Bukkit.getUnsafe().nextEntityId(world);
                    renderer.create(type, new Location(world, 0, 64, 0)).close();
                    int after = Bukkit.getUnsafe().nextEntityId(world);
                    if (after - marker != (type == EntityType.ENDER_DRAGON ? 10 : 2)) throw new AssertionError("Repeated creation allocated a real NMS entity: " + type);
                    byte[] expected = metadata(entity, world);
                    for (Class<?> c = entity.getClass(); c != null && c.getSimpleName().startsWith("Generated_"); c = c.getSuperclass()) {
                        for (Method setter : c.getDeclaredMethods()) {
                            if (!setter.getName().startsWith("set") || setter.getParameterCount() != 1) continue;
                            String suffix = setter.getName().substring(3);
                            Method getter = c.getDeclaredMethod((setter.getParameterTypes()[0] == boolean.class ? "is" : "get") + suffix);
                            Object value = getter.invoke(entity);
                            setter.invoke(entity, value);
                            properties++;
                        }
                    }
                    if (!Arrays.equals(expected, metadata(entity, world))) throw new AssertionError("Public property roundtrip changed native bytes: " + type);
                    packets.clear();
                    entity.show(viewer);
                    if (packets.size() < 2 || !(packets.get(1) instanceof ClientboundSetEntityDataPacket)) throw new AssertionError("Missing entity snapshot: " + type);
                    packets.clear();
                    entity.setRotation(90, 20);
                    renderer.flush();
                    if (type.getEntityClass() != null && org.bukkit.entity.LivingEntity.class.isAssignableFrom(type.getEntityClass())
                            && packets.stream().noneMatch(packet -> packet instanceof ClientboundRotateHeadPacket))
                        throw new AssertionError("Living entity head rotation missing: " + type);
                    entity.hide(viewer);
                    entity.close();
                    types++;
                } catch (Throwable failure) {
                    throw new AssertionError("Entity coverage failed for " + type, failure);
                }
            }
            if (world.getEntities().size() != before) throw new AssertionError("Prototype sweep registered entities in the world");
            var frame = renderer.createItemFrame(new Location(world, 3, 70, 3));
            frame.setFacing(org.bukkit.block.BlockFace.EAST);
            if (frame.getFacing() != org.bukkit.block.BlockFace.EAST) throw new AssertionError("Item-frame facing roundtrip failed");
            var direction = frame.getClass().getMethod("getDirection");
            if (direction.invoke(frame) != org.bukkit.block.BlockFace.EAST) throw new AssertionError("Item-frame spawn facing disagrees with metadata direction");
            var falling = renderer.createFallingBlock(new Location(world, 4, 70, 4));
            falling.show(viewer);
            packets.clear();
            falling.setBlockData(Bukkit.createBlockData(Material.STONE));
            renderer.flush();
            if (packets.stream().noneMatch(packet -> packet instanceof ClientboundRemoveEntitiesPacket)
                    || packets.stream().filter(packet -> packet instanceof ClientboundAddEntityPacket).map(packet -> (ClientboundAddEntityPacket) packet)
                            .noneMatch(packet -> packet.getData() == net.minecraft.world.level.block.Block.getId(net.minecraft.world.level.block.Blocks.STONE.defaultBlockState())))
                throw new AssertionError("Falling-block spawn state was not refreshed");
            var orb = renderer.createExperienceOrb(new Location(world, 5, 70, 5));
            orb.setValue(17);
            packets.clear();
            orb.show(viewer);
            if (!(packets.getFirst() instanceof ClientboundAddEntityPacket)) throw new AssertionError("Missing native orb spawn packet");
        }
        Bukkit.getLogger().info("ENTITY_RENDER_COVERAGE types=" + types + " properties=" + properties);
    }

    private static byte[] metadata(BaseVirtualEntity entity, World world) {
        var raw = Unpooled.buffer();
        try {
            var buffer = new RegistryFriendlyByteBuf(raw, ((org.bukkit.craftbukkit.CraftWorld) world).getHandle().registryAccess());
            ClientboundSetEntityDataPacket.STREAM_CODEC.encode(buffer, new ClientboundSetEntityDataPacket(entity.getId(), entity.metadata.fullSnapshot()));
            byte[] bytes = new byte[raw.readableBytes()];
            raw.readBytes(bytes);
            return bytes;
        } finally {
            raw.release();
        }
    }
}
