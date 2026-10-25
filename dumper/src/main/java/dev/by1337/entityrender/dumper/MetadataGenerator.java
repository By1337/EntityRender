package dev.by1337.entityrender.dumper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

/** Generates typed keys and inherited layouts; NMS packages/nested classes form the namespace. */
final class MetadataGenerator {
    private static final String PREFIX = "net.minecraft.world.entity.";

    private record Default(String owner, String field, String expression) {
    }

    private static final class Node {
        final String name;
        final Map<String, Node> children = new TreeMap<>();
        Schema.Entity entity;

        Node(String name) {
            this.name = name;
        }
    }

    static String generate(List<Schema.Entity> entities, List<Schema.Field> fields, Path defaultsFile) throws IOException {
        var byName = new TreeMap<String, Schema.Entity>();
        var byField = new HashMap<String, Schema.Field>();
        var ownFields = new HashMap<String, List<Schema.Field>>();
        var defaults = new HashMap<String, List<Default>>();
        Node root = new Node("GeneratedMetadata");
        for (var entity : entities) {
            if (byName.putIfAbsent(entity.name(), entity) != null) throw new IOException("Duplicate entity: " + entity.name());
            Node node = root;
            for (String segment : namespace(entity.name()).split("\\.")) node = node.children.computeIfAbsent(segment, Node::new);
            if (node.entity != null) throw new IOException("Metadata namespace collision: " + entity.name());
            node.entity = entity;
        }
        for (var entity : entities) {
            var visited = new HashSet<String>();
            for (var parent = entity; parent != null; parent = byName.get(parent.parent())) {
                if (!visited.add(parent.name())) throw new IOException("Cyclic entity hierarchy: " + entity.name());
                if (!parent.parent().isEmpty() && !byName.containsKey(parent.parent())) throw new IOException("Unknown parent: " + parent.parent());
            }
        }
        for (var field : fields) {
            if (!byName.containsKey(field.owner())) throw new IOException("Unknown field owner: " + field.owner());
            if (byField.putIfAbsent(field.owner() + "#" + field.name(), field) != null) throw new IOException("Duplicate metadata field: " + field);
            ownFields.computeIfAbsent(field.owner(), ignored -> new ArrayList<>()).add(field);
        }
        ownFields.values().forEach(list -> list.sort(Comparator.comparing(Schema.Field::name)));
        if (defaultsFile != null) {
            var seen = new HashSet<String>();
            for (var row : Schema.read(defaultsFile.getParent(), defaultsFile.getFileName().toString(), "entity\towner\tfield\texpression")) {
                if (!byName.containsKey(row[0]) || !byField.containsKey(row[1] + "#" + row[2])) throw new IOException("Stale default rule: " + String.join(" / ", row));
                var ancestor = byName.get(row[0]);
                while (ancestor != null && !ancestor.name().equals(row[1])) ancestor = byName.get(ancestor.parent());
                if (ancestor == null) throw new IOException("Default key is not inherited by " + row[0] + ": " + row[1] + "#" + row[2]);
                if (!seen.add(row[0] + "#" + row[1] + "#" + row[2])) throw new IOException("Duplicate default rule: " + String.join(" / ", row));
                if (row[3].isBlank()) throw new IOException("Empty default expression");
                defaults.computeIfAbsent(row[0], ignored -> new ArrayList<>()).add(new Default(row[1], row[2], row[3]));
            }
        }
        defaults.values().forEach(list -> list.sort(Comparator.comparing(Default::owner).thenComparing(Default::field)));
        var output = new StringBuilder("package dev.by1337.entityrender.core.metadata;\n\n")
                .append("/** Generated from the NMS schema and reviewed default rules. Do not edit by hand. */\n");
        emit(root, ownFields, defaults, output, 0);
        return output.toString();
    }

    private static void emit(Node node, Map<String, List<Schema.Field>> fields, Map<String, List<Default>> defaults, StringBuilder out, int depth) {
        String indent = "    ".repeat(depth);
        out.append(indent).append(depth == 0 ? "public final class " : "public static final class ").append(node.name).append(" {\n")
                .append(indent).append("    private ").append(node.name).append("() {}\n");
        if (node.entity != null) {
            var entity = node.entity;
            var own = fields.getOrDefault(entity.name(), List.of());
            out.append(indent).append("    // NMS: ").append(entity.name()).append("\n");
            for (var field : own) {
                out.append(indent).append("    public static final MetadataKey<").append(field.javaType().replace('$', '.')).append("> ")
                        .append(field.name()).append(" = new MetadataKey<>(").append(field.index())
                        .append(", net.minecraft.network.syncher.EntityDataSerializers.").append(field.serializer());
                switch (field.serializer()) {
                    case "VECTOR3" -> out.append(", value -> new org.joml.Vector3f(value)");
                    case "QUATERNION" -> out.append(", value -> new org.joml.Quaternionf(value)");
                    default -> {
                    }
                }
                out.append(");\n");
            }
            out.append(indent).append("    public static final MetadataLayout LAYOUT = new MetadataLayout(")
                    .append(entity.parent().isEmpty() ? "null" : reference(entity.parent()) + ".LAYOUT");
            for (var field : own) out.append(", ").append(field.name());
            out.append(")");
            for (var value : defaults.getOrDefault(entity.name(), List.of())) out.append("\n").append(indent).append("        .withDefault(")
                    .append(value.owner().equals(entity.name()) ? value.field() : reference(value.owner()) + "." + value.field())
                    .append(", ").append(value.expression()).append(")");
            out.append(";\n");
        }
        for (var child : node.children.values()) {
            out.append("\n");
            emit(child, fields, defaults, out, depth + 1);
        }
        out.append(indent).append("}\n");
    }

    private static String reference(String name) {
        return "GeneratedMetadata." + namespace(name);
    }

    private static String namespace(String name) {
        if (!name.startsWith(PREFIX)) throw new IllegalArgumentException("Unexpected NMS entity class: " + name);
        return name.substring(PREFIX.length()).replace('$', '.');
    }
}
