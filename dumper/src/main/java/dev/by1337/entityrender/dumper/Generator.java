package dev.by1337.entityrender.dumper;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

final class Generator {
    static void run(Path schema, Path out, Path defaults) throws IOException {
        if (Files.exists(out)) throw new IOException("Generator requires a new output directory; will not overwrite manual edits: " + out);
        List<Schema.Entity> entities = Schema.entities(schema);
        List<Schema.Field> fields = Schema.fields(schema);
        var names = new HashSet<String>();
        for (var entity : entities) if (!names.add(qualified(entity.name(), "api"))) throw new IOException("Generated entity name collision: " + entity.name());
        Set<String> owners = new HashSet<>();
        entities.forEach(e -> owners.add(e.name()));
        for (var entity : entities) if (!entity.parent().isEmpty() && !owners.contains(entity.parent())) throw new IOException("Missing parent: " + entity.parent());
        for (var field : fields) if (!owners.contains(field.owner())) throw new IOException("Missing field owner: " + field.owner());
        Files.createDirectories(out.getParent());
        Path stage = Files.createTempDirectory(out.getParent(), ".entity-generate-");
        for (var entity : entities) {
            String name = "Virtual" + simple(entity.name());
            String parent = entity.parent().isEmpty() ? "" : " extends " + qualified(entity.parent(), "api");
            var api = new StringBuilder("package " + generatedPackage(entity.name(), "api") + ";\n\n");
            api.append("/** Generated metadata skeleton; review names, flag bits and Bukkit type conversions.\n")
                    .append(" * NMS: ").append(entity.name()).append("\n * Entity types: ").append(entity.types()).append("\n */\n")
                    .append("public interface ").append(name).append(parent).append(" {\n");
            var impl = new StringBuilder("package " + generatedPackage(entity.name(), "core") + ";\n\n")
                    .append("/** Generated abstract skeleton. Metadata defaults have NOT been captured. */\n")
                    .append("public abstract class ").append(name).append("Skeleton");
            if (!entity.parent().isEmpty()) impl.append(" extends ").append(qualified(entity.parent(), "core")).append("Skeleton");
            impl.append(" implements ").append(qualified(entity.name(), "api")).append(" {\n");
            for (var field : fields) {
                if (!field.owner().equals(entity.name())) continue;
                String suffix = camel(field.name());
                String javaType = apiType(field.javaType());
                api.append("    /** NMS ").append(field.name()).append("; index ").append(field.index())
                        .append("; serializer ").append(field.serializer()).append("; type ").append(field.javaType()).append(".")
                        .append(javaType.equals("Object") ? " TODO: choose a public Bukkit/API type." : "")
                        .append(" */\n    ").append(javaType).append(" get").append(suffix).append("();\n")
                        .append("    void set").append(suffix).append("(").append(javaType).append(" value);\n\n");
                impl.append("    protected static final int ").append(field.name()).append(" = ").append(field.index()).append("; // ").append(field.serializer()).append("\n");
            }
            api.append("}\n");
            impl.append("}\n");
            save(stage.resolve("api/" + qualified(entity.name(), "api").replace('.', '/') + ".java"), api.toString());
            save(stage.resolve("core/" + qualified(entity.name(), "core").replace('.', '/') + "Skeleton.java"), impl.toString());
        }
        save(stage.resolve("core/dev/by1337/entityrender/core/metadata/GeneratedMetadata.java"), MetadataGenerator.generate(entities, fields, defaults));
        Files.move(stage, out);
        System.out.println("Generated " + entities.size() + " interface / abstract skeleton pairs in " + out);
    }

    private static String generatedPackage(String name, String module) {
        String relative = name.substring("net.minecraft.world.entity.".length());
        int dot = relative.lastIndexOf('.');
        return "dev.by1337.entityrender." + module + ".entity" + (dot < 0 ? "" : "." + relative.substring(0, dot));
    }

    private static String qualified(String name, String module) {
        return generatedPackage(name, module) + ".Virtual" + simple(name);
    }

    private static String simple(String type) {
        return type.substring(type.lastIndexOf('.') + 1).replace('$', '_');
    }

    private static String camel(String value) {
        var result = new StringBuilder();
        for (String part : value.split("_")) if (!part.isEmpty()) result.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1).toLowerCase(Locale.ROOT));
        return result.toString();
    }

    private static String apiType(String type) {
        return switch (type) {
            case "java.lang.Byte" -> "byte";
            case "java.lang.Integer" -> "int";
            case "java.lang.Long" -> "long";
            case "java.lang.Float" -> "float";
            case "java.lang.Boolean" -> "boolean";
            case "java.lang.String" -> "String";
            case "java.util.OptionalInt", "java.util.UUID" -> type;
            default -> "Object";
        };
    }

    private static void save(Path path, String content) throws IOException {
        Files.createDirectories(path.getParent());
        Files.writeString(path, content);
    }
}
