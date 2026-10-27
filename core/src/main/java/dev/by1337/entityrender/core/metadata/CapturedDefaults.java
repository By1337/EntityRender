package dev.by1337.entityrender.core.metadata;

import com.mojang.serialization.Codec;
import dev.by1337.entityrender.core.ValueConversions;
import io.netty.buffer.Unpooled;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import org.bukkit.entity.EntityType;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.HexFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/** Build-time captured native values. No Entity, EntityType factory or ServerEntity is instantiated here. */
public final class CapturedDefaults {
    private record Row(int index, int serializer, String encoding, String value) {
    }

    private record Spawn(boolean supported, int data, String encoding, String value) {
    }

    public record Template(MetadataLayout layout, int spawnData) {
    }

    private static final Map<String, List<Row>> METADATA = new HashMap<>();
    private static final Map<String, Spawn> SPAWNS = new HashMap<>();
    static {
        try (var input = CapturedDefaults.class.getResourceAsStream("/dev/by1337/entityrender/defaults/capture-version.tsv")) {
            if (input == null) throw new IllegalStateException("Missing captured defaults version");
            var lines = new String(input.readAllBytes(), StandardCharsets.UTF_8).lines().toList();
            if (!lines.contains("minecraft\t" + dev.by1337.entityrender.core.generated.TargetVersion.MINECRAFT_VERSION) || !lines.contains("format\t1")) throw new IllegalStateException("Captured defaults version does not match this EntityRender build");
        } catch (IOException error) {
            throw new UncheckedIOException(error);
        }
        for (String[] row : rows("metadata-defaults.tsv", 5)) METADATA.computeIfAbsent(row[0], ignored -> new ArrayList<>()).add(new Row(Integer.parseInt(row[1]), Integer.parseInt(row[2]), row[3], text(row[4])));
        for (String[] row : rows("spawn-defaults.tsv", 5)) {
            if (SPAWNS.putIfAbsent(row[0], new Spawn(row[1].equals("OK"), Integer.parseInt(row[2]), row[3], row[4].equals("-") ? "" : text(row[4]))) != null) throw new IllegalStateException("Duplicate captured spawn type " + row[0]);
        }
    }

    private CapturedDefaults() {
    }

    public static Template load(EntityType type, MetadataLayout generated, RegistryAccess registries) {
        String key = type.getKey().toString();
        var spawn = SPAWNS.get(key);
        if (spawn == null || !spawn.supported()) throw new IllegalArgumentException("No captured construction defaults for " + key + "; an explicit contextual factory is required");
        var rows = Objects.requireNonNull(METADATA.get(key), "No captured metadata for " + key);
        var values = new ArrayList<SynchedEntityData.DataValue<?>>(rows.size());
        for (var row : rows) {
            var serializer = Objects.requireNonNull(EntityDataSerializers.getSerializer(row.serializer()), "Unknown captured serializer");
            Object value;
            if (row.encoding().equals("WIRE")) {
                var buffer = new RegistryFriendlyByteBuf(Unpooled.wrappedBuffer(HexFormat.of().parseHex(row.value())), registries);
                try {
                    value = serializer.codec().decode(buffer);
                    if (buffer.readableBytes() != 0) throw new IllegalStateException("Unconsumed captured metadata");
                } finally {
                    buffer.release();
                }
            } else if (row.encoding().equals("HOLDER")) value = holder(registries, row.value());
            else if (row.encoding().startsWith("NBT:")) value = decode(row.encoding().substring(4), row.value(), registries);
            else throw new IllegalStateException("Unknown captured encoding " + row.encoding());
            values.add(dataValue(row.index(), serializer, value));
        }
        int spawnData = spawn.data();
        if (spawn.encoding().equals("BLOCK_STATE")) {
            var state = ValueConversions.decode(net.minecraft.world.level.block.state.BlockState.CODEC, spawn.value(), registries);
            spawnData = net.minecraft.world.level.block.Block.getId(state);
        }
        return new Template(generated.withSnapshot(values), spawnData);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static Object holder(RegistryAccess access, String encoded) {
        String[] parts = encoded.split(" ", 2);
        if (parts.length != 2) throw new IllegalStateException("Invalid captured holder");
        var registry = ResourceKey.createRegistryKey(Identifier.parse(parts[0]));
        return access.lookupOrThrow((ResourceKey) registry).getOrThrow(ResourceKey.create(registry, Identifier.parse(parts[1])));
    }

    private static Object decode(String serializer, String value, RegistryAccess access) {
        Codec<?> codec = switch (serializer) {
            case "ITEM_STACK" -> net.minecraft.world.item.ItemStack.OPTIONAL_CODEC;
            case "VILLAGER_DATA" -> net.minecraft.world.entity.npc.villager.VillagerData.CODEC;
            case "RESOLVABLE_PROFILE" -> net.minecraft.world.item.component.ResolvableProfile.CODEC;
            case "PARTICLE" -> net.minecraft.core.particles.ParticleTypes.CODEC;
            case "PARTICLES" -> net.minecraft.core.particles.ParticleTypes.CODEC.listOf();
            case "BLOCK_STATE" -> net.minecraft.world.level.block.state.BlockState.CODEC;
            case "OPTIONAL_BLOCK_STATE" -> net.minecraft.world.level.block.state.BlockState.CODEC.listOf();
            case "COMPONENT" -> net.minecraft.network.chat.ComponentSerialization.CODEC;
            case "OPTIONAL_COMPONENT" -> net.minecraft.network.chat.ComponentSerialization.CODEC.listOf();
            default -> throw new IllegalStateException("Unknown captured NBT serializer " + serializer);
        };
        var decoded = ValueConversions.decode(codec, value, access);
        return serializer.startsWith("OPTIONAL_") ? ((List<?>) decoded).stream().findFirst() : decoded;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static SynchedEntityData.DataValue<?> dataValue(int index, net.minecraft.network.syncher.EntityDataSerializer serializer, Object value) {
        return new SynchedEntityData.DataValue(index, serializer, value);
    }

    private static List<String[]> rows(String name, int width) {
        String path = "/dev/by1337/entityrender/defaults/" + name;
        try (var input = CapturedDefaults.class.getResourceAsStream(path)) {
            if (input == null) throw new IllegalStateException("Missing build-time defaults " + path + "; run the capture tool before packaging");
            var rows = new String(input.readAllBytes(), StandardCharsets.UTF_8).lines().skip(1).filter(line -> !line.isBlank()).map(line -> line.split("\\t", -1)).toList();
            for (var row : rows) if (row.length != width) throw new IllegalStateException("Invalid captured row in " + path);
            return rows;
        } catch (IOException error) {
            throw new UncheckedIOException(error);
        }
    }

    private static String text(String base64) {
        return new String(Base64.getDecoder().decode(base64), StandardCharsets.UTF_8);
    }
}
