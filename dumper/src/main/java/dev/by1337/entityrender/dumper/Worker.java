package dev.by1337.entityrender.dumper;

import java.lang.reflect.*;
import java.nio.file.*;
import java.util.*;

final class Worker {
    static Class<?> type(String name) throws ClassNotFoundException {
        return Class.forName(name);
    }

    static void run(Path out, String version, String protocol) throws Exception {
        type("net.minecraft.SharedConstants").getMethod("tryDetectVersion").invoke(null);
        type("net.minecraft.server.Bootstrap").getMethod("bootStrap").invoke(null);
        Class<?> accessorType = type("net.minecraft.network.syncher.EntityDataAccessor");
        Class<?> serializerType = type("net.minecraft.network.syncher.EntityDataSerializer");
        Class<?> serializersType = type("net.minecraft.network.syncher.EntityDataSerializers");
        Class<?> entityType = type("net.minecraft.world.entity.EntityType");
        Class<?> entityTypes;
        try {
            entityTypes = type("net.minecraft.world.entity.EntityTypes");
        } catch (ClassNotFoundException legacy) {
            entityTypes = entityType;
        }
        Class<?> entityBase = type("net.minecraft.world.entity.Entity");
        var serializerNames = new IdentityHashMap<Object, String>();
        var serializers = new TreeMap<String, Object>();
        var serializerRows = new TreeMap<String, String>();
        Method getSerializerId = serializersType.getMethod("getSerializedId", serializerType);
        for (java.lang.reflect.Field field : sortedFields(serializersType)) {
            if (!Modifier.isStatic(field.getModifiers()) || field.getType() != serializerType) continue;
            field.setAccessible(true);
            Object value = field.get(null);
            if (serializerNames.put(value, field.getName()) != null) throw new IllegalStateException("Aliased serializer: " + field);
            int id = (int) getSerializerId.invoke(null, value);
            if (id < 0) throw new IllegalStateException("Unregistered serializer: " + field);
            serializers.put(field.getName(), value);
            serializerRows.put(field.getName(), field.getName() + "\t" + id + "\t" + argument(field.getGenericType()));
        }
        var classes = new TreeMap<String, Class<?>>();
        var entityKeys = new TreeMap<String, SortedSet<String>>();
        for (java.lang.reflect.Field field : sortedFields(entityTypes)) {
            if (!Modifier.isStatic(field.getModifiers()) || field.getType() != entityType) continue;
            field.setAccessible(true);
            if (!(field.getGenericType() instanceof ParameterizedType parameterized)
                    || !(parameterized.getActualTypeArguments()[0] instanceof Class<?> clazz)) {
                throw new IllegalStateException("Cannot determine entity class: " + field);
            }
            Object value = field.get(null);
            String key = entityType.getMethod("getKey", entityType).invoke(null, value).toString();
            entityKeys.computeIfAbsent(clazz.getName(), ignored -> new TreeSet<>()).add(key);
            for (Class<?> c = clazz; c != null && entityBase.isAssignableFrom(c); c = c.getSuperclass()) classes.put(c.getName(), c);
        }
        var entityRows = new ArrayList<String>();
        var fieldRows = new ArrayList<String>();
        var fields = new ArrayList<Schema.Field>();
        Method getIndex = accessorType.getMethod("id"), getSerializer = accessorType.getMethod("serializer");
        for (var entry : classes.entrySet()) {
            Class<?> clazz = entry.getValue();
            String parent = clazz == entityBase ? "" : clazz.getSuperclass().getName();
            entityRows.add(clazz.getName() + "\t" + parent + "\t" + Modifier.isAbstract(clazz.getModifiers()) + "\t"
                    + String.join(",", entityKeys.getOrDefault(clazz.getName(), Collections.emptySortedSet())));
            for (java.lang.reflect.Field field : sortedFields(clazz)) {
                if (!Modifier.isStatic(field.getModifiers()) || field.getType() != accessorType) continue;
                field.setAccessible(true);
                Object accessor = field.get(null);
                int index = (int) getIndex.invoke(accessor);
                String serializer = serializerNames.get(getSerializer.invoke(accessor));
                if (serializer == null) throw new IllegalStateException("Unknown serializer: " + field);
                if (index < 0 || index > 254) throw new IllegalStateException("Invalid metadata index: " + field);
                String valueType = argument(field.getGenericType());
                fields.add(new Schema.Field(clazz.getName(), field.getName(), index, serializer, valueType));
                fieldRows.add(clazz.getName() + "\t" + field.getName() + "\t" + index + "\t" + serializer + "\t" + valueType + "\tNOT_CAPTURED");
            }
        }
        for (Class<?> clazz : classes.values()) {
            var indices = new HashSet<Integer>();
            for (Class<?> c = clazz; c != null && entityBase.isAssignableFrom(c); c = c.getSuperclass()) {
                String name = c.getName();
                for (var field : fields) if (field.owner().equals(name) && !indices.add(field.index())) throw new IllegalStateException("Duplicate inherited metadata index in " + clazz);
            }
        }
        List<String> hexRows = HexFixtures.capture(serializers);
        Files.createDirectories(out.getParent());
        Path stage = Files.createTempDirectory(out.getParent(), ".entity-dump-");
        Schema.write(stage, "version.tsv", "key\tvalue", List.of("format\t1", "minecraft\t" + version, "protocol\t" + protocol));
        Schema.write(stage, "entities.tsv", "class\tparent\tabstract\tentityTypes", entityRows);
        Schema.write(stage, "serializers.tsv", "name\tnetworkId\tnmsType", serializerRows.values());
        Schema.write(stage, "fields.tsv", "owner\tname\tindex\tserializer\tnmsType\tdefaultStatus", fieldRows);
        Schema.write(stage, "hex.tsv", HexFixtures.HEADER, hexRows);
        Files.move(stage, out);
        System.out.println("Dumped " + classes.size() + " entity classes, " + fields.size() + " metadata fields, " + serializers.size() + " serializers to " + out);
    }

    private static String argument(Type type) {
        if (!(type instanceof ParameterizedType p)) throw new IllegalStateException("Missing generic metadata type: " + type);
        return p.getActualTypeArguments()[0].getTypeName();
    }

    private static List<java.lang.reflect.Field> sortedFields(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields()).sorted(Comparator.comparing(java.lang.reflect.Field::getName)).toList();
    }
}
