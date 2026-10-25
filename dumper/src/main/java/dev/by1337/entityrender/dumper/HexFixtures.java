package dev.by1337.entityrender.dumper;

import java.lang.reflect.*;
import java.util.*;

/** Golden vectors are encoded by Minecraft itself, never by a tool-side codec. */
final class HexFixtures {
    static final String HEADER = "serializer\tsample\tinput\tstatus\thex\tnote";

    private record Sample(String name, String input, Object value) {
    }

    private record Unsupported(String reason) {
    }

    static List<String> capture(SortedMap<String, Object> serializers) throws Exception {
        var output = new ArrayList<String>();
        Class<?> serializerType = Worker.type("net.minecraft.network.syncher.EntityDataSerializer");
        Method codecMethod = serializerType.getMethod("codec");
        Method encode = Worker.type("net.minecraft.network.codec.StreamEncoder").getMethod("encode", Object.class, Object.class);
        Method decode = Worker.type("net.minecraft.network.codec.StreamDecoder").getMethod("decode", Object.class);
        Class<?> byteBuf = Worker.type("io.netty.buffer.ByteBuf");
        Method newBuffer = Worker.type("io.netty.buffer.Unpooled").getMethod("buffer");
        Method release = Worker.type("io.netty.util.ReferenceCounted").getMethod("release");
        Method writerIndex = byteBuf.getMethod("writerIndex");
        Method readableBytes = byteBuf.getMethod("readableBytes");
        Method getBytes = byteBuf.getMethod("getBytes", int.class, byte[].class);
        Class<?> registryAccessType = Worker.type("net.minecraft.core.RegistryAccess");
        Object registries = registryAccessType.getMethod("fromRegistryOfRegistries", Worker.type("net.minecraft.core.Registry"))
                .invoke(null, Worker.type("net.minecraft.core.registries.BuiltInRegistries").getField("REGISTRY").get(null));
        Constructor<?> bufferConstructor = Worker.type("net.minecraft.network.RegistryFriendlyByteBuf").getConstructor(byteBuf, registryAccessType);
        int passed = 0, unsupported = 0;
        for (var entry : serializers.entrySet()) {
            String name = entry.getKey();
            List<Sample> samples = samples(name);
            if (samples.isEmpty()) {
                output.add(name + "\tunsupported\t-\tUNSUPPORTED\t-\tNo sample adapter; dynamic registry context may be required");
                unsupported++;
                continue;
            }
            Object codec = codecMethod.invoke(entry.getValue());
            for (Sample sample : samples) {
                if (sample.value() instanceof Unsupported missing) {
                    output.add(name + "\t" + sample.name() + "\t" + sample.input() + "\tUNSUPPORTED\t-\t" + missing.reason());
                    continue;
                }
                Object raw = newBuffer.invoke(null), secondRaw = newBuffer.invoke(null);
                try {
                    Object buffer = bufferConstructor.newInstance(raw, registries);
                    Object second = bufferConstructor.newInstance(secondRaw, registries);
                    encode.invoke(codec, buffer, sample.value());
                    byte[] original = new byte[(int) writerIndex.invoke(raw)];
                    getBytes.invoke(raw, 0, original);
                    Object restored = decode.invoke(codec, buffer);
                    if ((int) readableBytes.invoke(raw) != 0) throw new IllegalStateException("Decoder left unread bytes");
                    encode.invoke(codec, second, restored);
                    byte[] roundtrip = new byte[(int) writerIndex.invoke(secondRaw)];
                    getBytes.invoke(secondRaw, 0, roundtrip);
                    if (!Arrays.equals(original, roundtrip)) throw new IllegalStateException("Encode/decode/encode changed bytes");
                    output.add(name + "\t" + sample.name() + "\t" + sample.input() + "\tPASS\t" + HexFormat.of().formatHex(original) + "\t-");
                    passed++;
                } catch (InvocationTargetException e) {
                    throw new IllegalStateException("NMS codec fixture failed: " + name + "/" + sample.name(), e.getCause());
                } finally {
                    release.invoke(raw);
                    release.invoke(secondRaw);
                }
            }
        }
        output.sort(String::compareTo);
        System.out.println("NMS hex vectors: " + passed + " passed, " + unsupported + " serializers without sample adapters");
        return output;
    }

    private static List<Sample> samples(String name) throws Exception {
        var result = new ArrayList<Sample>();
        var declaredType = Worker.type("net.minecraft.network.syncher.EntityDataSerializers").getField(name).getGenericType();
        if (declaredType instanceof java.lang.reflect.ParameterizedType generic
                && generic.getActualTypeArguments()[0] instanceof Class<?> valueType && valueType.isEnum()) {
            for (Object value : valueType.getEnumConstants()) add(result, ((Enum<?>) value).name(), value);
            return result;
        }
        switch (name) {
            case "BYTE" -> {
                for (byte value : new byte[]{ Byte.MIN_VALUE, -1, 0, 1, Byte.MAX_VALUE }) add(result, Byte.toString(value), value);
            }
            case "INT" -> {
                for (int value : new int[]{ Integer.MIN_VALUE, -1, 0, 1, 127, 128, 255, 16383, 16384, Integer.MAX_VALUE }) add(result, Integer.toString(value), value);
            }
            case "LONG" -> {
                for (long value : new long[]{ Long.MIN_VALUE, -1, 0, 1, 127, 128, 16384, Long.MAX_VALUE }) add(result, Long.toString(value), value);
            }
            case "FLOAT" -> {
                for (float value : new float[]{ 0, -0.0f, 1, -1, 0.5f, Float.MIN_VALUE, Float.MAX_VALUE, Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NaN }) {
                    String bits = String.format(Locale.ROOT, "%08x", Float.floatToRawIntBits(value));
                    result.add(new Sample(bits, "float-bits:" + bits, value));
                }
            }
            case "BOOLEAN" -> {
                add(result, "false", false);
                add(result, "true", true);
            }
            case "STRING" -> {
                result.add(new Sample("empty", "utf8:", ""));
                result.add(new Sample("ascii", "utf8:EntityRender", "EntityRender"));
                result.add(new Sample("unicode", "utf8:Привет 👀", "Привет 👀"));
                result.add(new Sample("controls", "utf8-escaped:A\\u0000\\t\\nB", "A\0\t\nB"));
            }
            case "BLOCK_POS", "OPTIONAL_BLOCK_POS" -> {
                if (name.startsWith("OPTIONAL")) add(result, "empty", Optional.empty());
                for (int[] xyz : new int[][]{ { 0, 0, 0 }, { 123, 64, -456 }, { -33554432, -2048, 33554431 } }) {
                    Object pos = construct("net.minecraft.core.BlockPos", new Class<?>[]{ int.class, int.class, int.class }, xyz[0], xyz[1], xyz[2]);
                    String text = xyz[0] + "," + xyz[1] + "," + xyz[2];
                    result.add(new Sample(text, "block-pos:" + text, name.startsWith("OPTIONAL") ? Optional.of(pos) : pos));
                }
            }
            case "ROTATIONS", "VECTOR3", "QUATERNION" -> {
                String clazz = name.equals("ROTATIONS") ? "net.minecraft.core.Rotations" : name.equals("VECTOR3") ? "org.joml.Vector3f" : "org.joml.Quaternionf";
                boolean quaternion = name.equals("QUATERNION");
                for (float[] values : new float[][]{ { 0, 0, 0, 1 }, { 1.25f, -2.5f, 90, 0.5f } }) {
                    Object value = quaternion ? construct(clazz, new Class<?>[]{ float.class, float.class, float.class, float.class }, values[0], values[1], values[2], values[3])
                            : construct(clazz, new Class<?>[]{ float.class, float.class, float.class }, values[0], values[1], values[2]);
                    String text = values[0] + "," + values[1] + "," + values[2] + (quaternion ? "," + values[3] : "");
                    result.add(new Sample(text, name.toLowerCase(Locale.ROOT) + ":" + text, value));
                }
            }
            case "DIRECTION", "POSE", "ARMADILLO_STATE", "SNIFFER_STATE", "DYE_COLOR", "HUMANOID_ARM", "COPPER_GOLEM_STATE", "WEATHERING_COPPER_STATE" -> {
                String clazz = switch (name) {
                    case "DIRECTION" -> "net.minecraft.core.Direction";
                    case "POSE" -> "net.minecraft.world.entity.Pose";
                    case "ARMADILLO_STATE" -> "net.minecraft.world.entity.animal.armadillo.Armadillo$ArmadilloState";
                    case "DYE_COLOR" -> "net.minecraft.world.item.DyeColor";
                    case "HUMANOID_ARM" -> "net.minecraft.world.entity.HumanoidArm";
                    case "COPPER_GOLEM_STATE" -> "net.minecraft.world.entity.animal.golem.CopperGolemState";
                    case "WEATHERING_COPPER_STATE" -> "net.minecraft.world.level.block.WeatheringCopper$WeatherState";
                    default -> "net.minecraft.world.entity.animal.sniffer.Sniffer$State";
                };
                for (Object value : Worker.type(clazz).getEnumConstants()) add(result, ((Enum<?>) value).name(), value);
            }
            case "OPTIONAL_UNSIGNED_INT" -> {
                add(result, "empty", OptionalInt.empty());
                for (int value : new int[]{ 0, 1, 126, 127, 128, Integer.MAX_VALUE - 1 }) add(result, Integer.toString(value), OptionalInt.of(value));
            }
            case "OPTIONAL_UUID", "OPTIONAL_LIVING_ENTITY_REFERENCE" -> {
                add(result, "empty", Optional.empty());
                for (String uuid : List.of("00000000-0000-0000-0000-000000000000", "01234567-89ab-cdef-fedc-ba9876543210")) {
                    Object value = UUID.fromString(uuid);
                    if (name.equals("OPTIONAL_LIVING_ENTITY_REFERENCE")) {
                        var reference = Worker.type("net.minecraft.world.entity.EntityReference");
                        try {
                            value = reference.getConstructor(UUID.class).newInstance(value);
                        } catch (NoSuchMethodException factoryBased) {
                            value = reference.getMethod("of", UUID.class).invoke(null, value);
                        }
                    }
                    add(result, uuid, Optional.of(value));
                }
            }
            case "OPTIONAL_GLOBAL_POS" -> add(result, "empty", Optional.empty());
            case "COMPONENT", "OPTIONAL_COMPONENT" -> {
                if (name.startsWith("OPTIONAL")) add(result, "empty", Optional.empty());
                Object component = Worker.type("net.minecraft.network.chat.Component").getMethod("literal", String.class).invoke(null, "Приз 👀");
                result.add(new Sample("literal", "literal:Приз 👀", name.startsWith("OPTIONAL") ? Optional.of(component) : component));
            }
            case "ITEM_STACK" -> {
                add(result, "empty", field("net.minecraft.world.item.ItemStack", "EMPTY"));
                if (isModern() || needsLiveItemContext()) {
                    result.add(new Sample("diamond_3", "item:minecraft:diamond,count:3,components:default", new Unsupported("Requires loaded item component data; bootstrap alone is insufficient")));
                    break;
                }
                Object stack = construct("net.minecraft.world.item.ItemStack", new Class<?>[]{ Worker.type("net.minecraft.world.level.ItemLike"), int.class },
                        field("net.minecraft.world.item.Items", "DIAMOND"), 3);
                result.add(new Sample("diamond_3", "item:minecraft:diamond,count:3,components:default", stack));
            }
            case "VILLAGER_DATA" -> {
                if (isModern()) break; // Modern villager types/professions require dynamic registries.
                Class<?> dataType = (Class<?>) ((java.lang.reflect.ParameterizedType) declaredType).getActualTypeArguments()[0];
                if (java.util.Arrays.stream(dataType.getConstructors()).noneMatch(constructor -> constructor.getParameterCount() == 3
                        && constructor.getParameterTypes()[0].getName().equals("net.minecraft.world.entity.npc.VillagerType")))
                    break;
                for (int level : new int[]{ 1, 5 }) {
                    Object value = construct("net.minecraft.world.entity.npc.VillagerData", new Class<?>[]{ Worker.type("net.minecraft.world.entity.npc.VillagerType"), Worker.type("net.minecraft.world.entity.npc.VillagerProfession"), int.class },
                            field("net.minecraft.world.entity.npc.VillagerType", "PLAINS"), field("net.minecraft.world.entity.npc.VillagerProfession", "FARMER"), level);
                    result.add(new Sample("plains_farmer_" + level, "villager:plains,farmer," + level, value));
                }
            }
            case "BLOCK_STATE", "OPTIONAL_BLOCK_STATE" -> {
                if (name.startsWith("OPTIONAL")) add(result, "empty", Optional.empty());
                for (String block : List.of("AIR", "STONE", "OAK_LOG")) {
                    Object state = Worker.type("net.minecraft.world.level.block.Block").getMethod("defaultBlockState").invoke(field("net.minecraft.world.level.block.Blocks", block));
                    add(result, block, name.startsWith("OPTIONAL") ? Optional.of(state) : state);
                }
            }
            case "COMPOUND_TAG" -> {
                add(result, "empty", Worker.type("net.minecraft.nbt.CompoundTag").getConstructor().newInstance());
                Object tag = Worker.type("net.minecraft.nbt.CompoundTag").getConstructor().newInstance();
                tag.getClass().getMethod("putInt", String.class, int.class).invoke(tag, "answer", 42);
                result.add(new Sample("answer", "nbt:{answer:42}", tag));
            }
            case "PARTICLE", "PARTICLES" -> {
                Object flame = field("net.minecraft.core.particles.ParticleTypes", "FLAME");
                if (name.equals("PARTICLES")) {
                    add(result, "empty", List.of());
                    add(result, "FLAME", List.of(flame));
                } else add(result, "FLAME", flame);
            }
            default -> {
                /* Explicit UNSUPPORTED row, never a fabricated successful vector. */ }
        }
        return result;
    }

    private static void add(List<Sample> samples, String name, Object value) {
        samples.add(new Sample(name, name, value));
    }

    private static boolean isModern() {
        try {
            Worker.type("net.minecraft.world.entity.EntityTypes");
            return true;
        } catch (ClassNotFoundException legacy) {
            return false;
        }
    }

    private static boolean needsLiveItemContext() throws Exception {
        try {
            Class.forName("io.papermc.paper.util.sanitizer.ItemComponentSanitizer", false, HexFixtures.class.getClassLoader());
            return Worker.type("net.minecraft.server.MinecraftServer").getMethod("getServer").invoke(null) == null;
        } catch (ClassNotFoundException vanilla) {
            return false;
        }
    }

    private static Object field(String clazz, String name) throws Exception {
        return Worker.type(clazz).getField(name).get(null);
    }

    private static Object construct(String clazz, Class<?>[] parameters, Object... values) throws Exception {
        return Worker.type(clazz).getConstructor(parameters).newInstance(values);
    }
}
