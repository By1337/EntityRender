package dev.by1337.entityrender.capture;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.CraftEntityType;
import org.bukkit.plugin.java.JavaPlugin;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.RegistryOps;
import net.minecraft.nbt.NbtOps;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntitySpawnRequest;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.UpdateInterval;
import com.mojang.serialization.Codec;
import io.netty.buffer.Unpooled;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Comparator;
import java.util.HexFormat;
import java.util.IdentityHashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/** Runs only inside the disposable dumper JVM, never shipped in EntityRender core. */
public class DefaultsProbe extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getScheduler().runTask(this, () -> {
            try {
                capture();
            } catch (Throwable error) {
                getLogger().log(java.util.logging.Level.SEVERE, "Native defaults capture failed", error);
                try {
                    Files.writeString(Path.of("capture.failed"), error.toString());
                } catch (Exception ignored) {
                }
            } finally {
                Bukkit.shutdown();
            }
        });
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void capture() throws Exception {
        var level = ((CraftWorld) Bukkit.getWorlds().getFirst()).getHandle();
        var registries = level.registryAccess();
        var names = new IdentityHashMap<EntityDataSerializer<?>, String>();
        for (var field : EntityDataSerializers.class.getDeclaredFields()) if (java.lang.reflect.Modifier.isStatic(field.getModifiers()) && field.getType() == EntityDataSerializer.class) {
            field.setAccessible(true);
            names.put((EntityDataSerializer<?>) field.get(null), field.getName());
        }
        var metadata = new ArrayList<String>();
        metadata.add("entity\tindex\tserializer\tencoding\tvalue");
        var spawns = new ArrayList<String>();
        spawns.add("entity\tstatus\tspawnData\tencoding\tvalue");
        long before = java.util.stream.StreamSupport.stream(level.getAllEntities().spliterator(), false).count();
        for (var type : Arrays.stream(org.bukkit.entity.EntityType.values()).filter(t -> t != org.bukkit.entity.EntityType.UNKNOWN).sorted(Comparator.comparing(t -> t.getKey().toString())).toList()) {
            var key = type.getKey().toString();
            var nativeType = CraftEntityType.bukkitToMinecraft(type);
            var entity = nativeType.create(level, new EntitySpawnRequest(EntitySpawnReason.COMMAND, true));
            if (entity == null || type == org.bukkit.entity.EntityType.MARKER || type == org.bukkit.entity.EntityType.FISHING_BOBBER) {
                spawns.add(key + "\tUNSUPPORTED\t0\tNONE\t-");
                continue;
            }
            // The constructor picks a random profession. Use a stable library default,
            // otherwise unrelated captures produce a diff and every virtual zombie
            // villager inherits whichever profession happened to be picked at build time.
            if (entity instanceof net.minecraft.world.entity.monster.zombie.ZombieVillager zombie) {
                zombie.setVillagerData(zombie.getVillagerData().withProfession(registries, net.minecraft.world.entity.npc.villager.VillagerProfession.NONE));
            }
            var tracker = new ServerEntity(level, entity, UpdateInterval.periodic(1), false, new ServerEntity.Synchronizer() {
                public void sendToTrackingPlayers(net.minecraft.network.protocol.Packet<? super net.minecraft.network.protocol.game.ClientGamePacketListener> packet) {
                }

                public void sendToTrackingPlayersAndSelf(net.minecraft.network.protocol.Packet<? super net.minecraft.network.protocol.game.ClientGamePacketListener> packet) {
                }

                public void sendToTrackingPlayersFiltered(net.minecraft.network.protocol.Packet<? super net.minecraft.network.protocol.game.ClientGamePacketListener> packet, java.util.function.Predicate<ServerPlayer> filter) {
                }
            }, Set.of());
            var packet = entity.getAddEntityPacket(tracker);
            int data = packet instanceof net.minecraft.network.protocol.game.ClientboundAddEntityPacket add ? add.getData() : 0;
            if (type == org.bukkit.entity.EntityType.FALLING_BLOCK) {
                var state = net.minecraft.world.level.block.Block.stateById(data);
                spawns.add(key + "\tOK\t0\tBLOCK_STATE\t" + base64(snbt(net.minecraft.world.level.block.state.BlockState.CODEC, state, registries)));
            } else spawns.add(key + "\tOK\t" + data + "\tINT\t-");
            for (var value : entity.getEntityData().packAll()) {
                var serializer = value.serializer();
                String name = Objects.requireNonNull(names.get(serializer));
                Object item = value.value();
                String encoding = "WIRE", payload;
                if (item instanceof Holder<?> holder) {
                    var resource = holder.unwrapKey().orElseThrow();
                    encoding = "HOLDER";
                    payload = resource.registry() + " " + resource.identifier();
                } else {
                    Codec codec = switch (name) {
                        case "ITEM_STACK" -> net.minecraft.world.item.ItemStack.OPTIONAL_CODEC;
                        case "VILLAGER_DATA" -> net.minecraft.world.entity.npc.villager.VillagerData.CODEC;
                        case "RESOLVABLE_PROFILE" -> net.minecraft.world.item.component.ResolvableProfile.CODEC;
                        case "PARTICLE" -> net.minecraft.core.particles.ParticleTypes.CODEC;
                        case "PARTICLES" -> net.minecraft.core.particles.ParticleTypes.CODEC.listOf();
                        case "BLOCK_STATE" -> net.minecraft.world.level.block.state.BlockState.CODEC;
                        case "OPTIONAL_BLOCK_STATE" -> net.minecraft.world.level.block.state.BlockState.CODEC.listOf();
                        case "COMPONENT" -> net.minecraft.network.chat.ComponentSerialization.CODEC;
                        case "OPTIONAL_COMPONENT" -> net.minecraft.network.chat.ComponentSerialization.CODEC.listOf();
                        default -> null;
                    };
                    if (codec != null) {
                        encoding = "NBT:" + name;
                        if (name.startsWith("OPTIONAL_")) item = ((Optional<?>) item).stream().toList();
                        payload = snbt(codec, item, registries);
                    } else {
                        var buffer = new RegistryFriendlyByteBuf(Unpooled.buffer(), registries);
                        try {
                            ((EntityDataSerializer) serializer).codec().encode(buffer, item);
                            byte[] bytes = new byte[buffer.readableBytes()];
                            buffer.getBytes(0, bytes);
                            payload = HexFormat.of().formatHex(bytes);
                        } finally {
                            buffer.release();
                        }
                    }
                }
                metadata.add(key + "\t" + value.id() + "\t" + EntityDataSerializers.getSerializedId(serializer) + "\t" + encoding + "\t" + base64(payload));
            }
        }
        if (java.util.stream.StreamSupport.stream(level.getAllEntities().spliterator(), false).count() != before) throw new IllegalStateException("Capture registered actual entities in the world");
        Path out = Path.of(System.getProperty("entityrender.capture.out"));
        Files.createDirectories(out);
        Files.write(out.resolve("metadata-defaults.tsv"), metadata);
        Files.write(out.resolve("spawn-defaults.tsv"), spawns);
        Files.writeString(out.resolve("capture-version.tsv"), "minecraft\t" + Bukkit.getMinecraftVersion() + "\nformat\t1\n");
        Files.writeString(Path.of("capture.complete"), "PASS");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static String snbt(Codec codec, Object value, RegistryAccess access) {
        return codec.encodeStart(RegistryOps.create(NbtOps.INSTANCE, access), value).getOrThrow().toString();
    }

    private static String base64(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }
}
