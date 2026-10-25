package dev.by1337.entityrender.dumper;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

/** Reproducible runtime hierarchy. Only the four hand-authored visual types are excluded. */
final class RuntimeGenerator {
    private static final String PREFIX = "net.minecraft.world.entity.";
    private static final Set<String> MANUAL = Set.of("Entity", "Display", "Display$TextDisplay", "Display$ItemDisplay", "Display$BlockDisplay", "decoration.ArmorStand");

    private record Property(String type, String suffix, String read, String write) {
    }

    static void run(Path schema, Path out) throws IOException {
        if (Files.exists(out)) throw new IOException("Output already exists: " + out);
        var entities = Schema.entities(schema);
        var fields = Schema.fields(schema);
        Files.createDirectories(out);
        var factory = new StringBuilder("package dev.by1337.entityrender.core;\n\nfinal class GeneratedEntityFactory {\n")
                .append("    static BaseVirtualEntity create(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType) {\n")
                .append("        return switch (type.getKey().toString()) {\n");
        var conveniences = new StringBuilder("package dev.by1337.entityrender.api;\n\npublic interface GeneratedEntityFactories {\n")
                .append("    dev.by1337.entityrender.api.entity.VirtualEntity create(org.bukkit.entity.EntityType type, org.bukkit.Location location);\n");
        var own = new HashMap<String, List<Schema.Field>>();
        fields.forEach(field -> own.computeIfAbsent(field.owner(), ignored -> new ArrayList<>()).add(field));
        for (var entity : entities) {
            String relative = entity.name().substring(PREFIX.length());
            if (MANUAL.contains(relative)) continue;
            String apiName = api(entity.name());
            String parentApi = api(entity.parent());
            String implName = implementation(entity.name());
            String parentImpl = entity.parent().equals(PREFIX + "Entity") ? "BaseVirtualEntity" : implementation(entity.parent());
            var api = new StringBuilder("package " + apiName.substring(0, apiName.lastIndexOf('.')) + ";\n\n")
                    .append("/** Generated from ").append(entity.name()).append(". Values use Bukkit types; packed flag fields remain explicit. */\n")
                    .append("public interface ").append(apiName.substring(apiName.lastIndexOf('.') + 1)).append(" extends ").append(parentApi).append(" {\n");
            var impl = new StringBuilder("package dev.by1337.entityrender.core;\n\nclass ").append(implName).append(" extends ").append(parentImpl)
                    .append(" implements ").append(apiName).append(" {\n")
                    .append("    ").append(implName).append("(PaperEntityRenderer owner, int id, org.bukkit.Location location, org.bukkit.entity.EntityType type, net.minecraft.world.entity.EntityType<?> nativeType, dev.by1337.entityrender.core.metadata.MetadataLayout layout) {\n")
                    .append("        super(owner,id,location,type,nativeType,layout);\n    }\n");
            for (var field : own.getOrDefault(entity.name(), List.of())) {
                Property property = property(field);
                String name = propertyName(field);
                if (name.equals("Type")) name = "Variant";
                if (name.equals("Flags")) name = simple(entity.name()) + name;
                name += property.suffix();
                String getter = property.type().equals("boolean") ? "is" : "get";
                String key = "dev.by1337.entityrender.core.metadata.GeneratedMetadata." + relative.replace('$', '.') + "." + field.name();
                api.append("    /** NMS ").append(field.name()).append("; ").append(field.serializer()).append(". */\n")
                        .append("    ").append(property.type()).append(" ").append(getter).append(name).append("();\n")
                        .append("    void set").append(name).append("(").append(property.type()).append(" value);\n");
                impl.append("    @Override public ").append(property.type()).append(" ").append(getter).append(name).append("() { return ")
                        .append(property.read().replace("$", "get(" + key + ")")).append("; }\n")
                        .append("    @Override public void set").append(name).append("(").append(property.type()).append(" value) { ")
                        .append(property.type().equals("float") ? "finite(value); " : "")
                        .append("set(").append(key).append(", ").append(property.write().replace("$", "value")).append("); }\n");
            }
            RuntimeExtras.append(entity, own.getOrDefault(entity.name(), List.of()), fields.stream().anyMatch(f -> f.owner().endsWith(".HangingEntity") && f.name().equals("DATA_DIRECTION")), api, impl);
            if (relative.equals("LivingEntity")) api.append("    org.bukkit.inventory.ItemStack getEquipment(org.bukkit.inventory.EquipmentSlot slot);\n    void setEquipment(org.bukkit.inventory.EquipmentSlot slot, org.bukkit.inventory.ItemStack item);\n");
            api.append("}\n");
            impl.append("}\n");
            save(out.resolve("api/" + apiName.replace('.', '/') + ".java"), api.toString());
            save(out.resolve("core/dev/by1337/entityrender/core/" + implName + ".java"), impl.toString());
            if (!entity.types().isEmpty()) for (String key : entity.types().split(",")) {
                if (Set.of("minecraft:player", "minecraft:fishing_bobber", "minecraft:marker").contains(key)) continue;
                String method = camel(key.substring(key.indexOf(':') + 1));
                factory.append("            case \"").append(key).append("\" -> new ").append(implName)
                        .append("(owner,id,location,type,nativeType,owner.template(type,location.getWorld(),nativeType,dev.by1337.entityrender.core.metadata.GeneratedMetadata.")
                        .append(relative.replace('$', '.')).append(".LAYOUT));\n");
                conveniences.append("    default ").append(apiName).append(" create").append(method).append("(org.bukkit.Location location) { return (").append(apiName)
                        .append(") create(java.util.Objects.requireNonNull(org.bukkit.Registry.ENTITY_TYPE.get(org.bukkit.NamespacedKey.fromString(\"").append(key)
                        .append("\"))),location); }\n");
            }
        }
        factory.append("            default -> throw new IllegalArgumentException(\"Unsupported entity type: \" + type);\n        };\n    }\n}\n");
        conveniences.append("}\n");
        save(out.resolve("core/dev/by1337/entityrender/core/GeneratedEntityFactory.java"), factory.toString());
        save(out.resolve("api/dev/by1337/entityrender/api/GeneratedEntityFactories.java"), conveniences.toString());
        var version = Schema.read(schema, "version.tsv", "key\tvalue");
        String minecraft = version.stream().filter(row -> row[0].equals("minecraft")).findFirst().orElseThrow()[1];
        int protocol = Integer.parseInt(version.stream().filter(row -> row[0].equals("protocol")).findFirst().orElseThrow()[1]);
        if (!minecraft.matches("[A-Za-z0-9_.+\\-]+")) throw new IOException("Invalid Minecraft version");
        save(out.resolve("core/dev/by1337/entityrender/core/TargetVersion.java"), "package dev.by1337.entityrender.core;\n\nfinal class TargetVersion {\n    static final String MINECRAFT_VERSION=\"" + minecraft + "\";\n    static final int PROTOCOL=" + protocol + ";\n}\n");
        System.out.println("Generated runtime hierarchy for " + entities.size() + " entity classes");
    }

    private static Property property(Schema.Field field) {
        String nms = field.javaType().replace('$', '.');
        if (field.name().equals("DATA_COLLAR_COLOR")) return new Property("org.bukkit.DyeColor", "",
                "org.bukkit.DyeColor.valueOf(net.minecraft.world.item.DyeColor.byId($).name())",
                "net.minecraft.world.item.DyeColor.valueOf(java.util.Objects.requireNonNull($).name()).getId()");
        if (field.owner().endsWith(".Shulker") && field.name().equals("DATA_COLOR_ID")) return new Property("org.bukkit.DyeColor", "",
                "$ == 16 ? null : org.bukkit.DyeColor.valueOf(net.minecraft.world.item.DyeColor.byId($).name())",
                "(byte)($ == null ? 16 : net.minecraft.world.item.DyeColor.valueOf($.name()).getId())");
        return switch (field.serializer()) {
            case "BYTE" -> direct("byte");
            case "INT" -> direct("int");
            case "LONG" -> direct("long");
            case "FLOAT" -> direct("float");
            case "BOOLEAN" -> direct("boolean");
            case "STRING" -> direct("String");
            case "ITEM_STACK" -> conversion("org.bukkit.inventory.ItemStack", "item");
            case "BLOCK_STATE" -> conversion("org.bukkit.block.data.BlockData", "block");
            case "BLOCK_POS" -> conversion("org.bukkit.util.BlockVector", "position");
            case "OPTIONAL_BLOCK_POS" -> optional("org.bukkit.util.BlockVector", "position");
            case "OPTIONAL_BLOCK_STATE" -> optional("org.bukkit.block.data.BlockData", "block");
            case "OPTIONAL_UUID" -> new Property("java.util.UUID", "", "$.orElse(null)", "java.util.Optional.ofNullable($)");
            case "OPTIONAL_LIVING_ENTITY_REFERENCE" -> new Property("java.util.UUID", "", "$.map(net.minecraft.world.entity.EntityReference::getUUID).orElse(null)", "java.util.Optional.ofNullable($).map(net.minecraft.world.entity.EntityReference::of)");
            case "OPTIONAL_UNSIGNED_INT" -> direct("java.util.OptionalInt");
            case "COMPONENT" -> new Property("net.kyori.adventure.text.Component", "", "io.papermc.paper.adventure.PaperAdventure.asAdventure($)", "io.papermc.paper.adventure.PaperAdventure.asVanilla(java.util.Objects.requireNonNull($))");
            case "OPTIONAL_COMPONENT" -> new Property("net.kyori.adventure.text.Component", "", "$.map(io.papermc.paper.adventure.PaperAdventure::asAdventure).orElse(null)", "java.util.Optional.ofNullable($).map(io.papermc.paper.adventure.PaperAdventure::asVanilla)");
            case "DIRECTION" -> conversion("org.bukkit.block.BlockFace", "direction");
            case "VILLAGER_DATA" -> conversion("dev.by1337.entityrender.api.VillagerAppearance", "villager");
            case "CAT_VARIANT" -> holder("org.bukkit.entity.Cat.Type", "CAT_VARIANT");
            case "COW_VARIANT" -> holder("org.bukkit.entity.Cow.Variant", "COW_VARIANT");
            case "PIG_VARIANT" -> holder("org.bukkit.entity.Pig.Variant", "PIG_VARIANT");
            case "CHICKEN_VARIANT" -> holder("org.bukkit.entity.Chicken.Variant", "CHICKEN_VARIANT");
            case "CAT_SOUND_VARIANT" -> holder("org.bukkit.entity.Cat.SoundVariant", "CAT_SOUND_VARIANT");
            case "COW_SOUND_VARIANT" -> holder("org.bukkit.entity.Cow.SoundVariant", "COW_SOUND_VARIANT");
            case "PIG_SOUND_VARIANT" -> holder("org.bukkit.entity.Pig.SoundVariant", "PIG_SOUND_VARIANT");
            case "CHICKEN_SOUND_VARIANT" -> holder("org.bukkit.entity.Chicken.SoundVariant", "CHICKEN_SOUND_VARIANT");
            case "WOLF_SOUND_VARIANT" -> holder("org.bukkit.entity.Wolf.SoundVariant", "WOLF_SOUND_VARIANT");
            case "ZOMBIE_NAUTILUS_VARIANT" -> holder("org.bukkit.entity.ZombieNautilus.Variant", "ZOMBIE_NAUTILUS_VARIANT");
            case "WOLF_VARIANT" -> holder("org.bukkit.entity.Wolf.Variant", "WOLF_VARIANT");
            case "FROG_VARIANT" -> holder("org.bukkit.entity.Frog.Variant", "FROG_VARIANT");
            case "PAINTING_VARIANT" -> holder("org.bukkit.Art", "PAINTING_VARIANT");
            case "SNIFFER_STATE" -> new Property("org.bukkit.entity.Sniffer.State", "", "org.bukkit.entity.Sniffer.State.valueOf($.name())", nms + ".valueOf($.name())");
            case "ARMADILLO_STATE" -> enumeration("org.bukkit.entity.Armadillo.State", nms);
            case "WEATHERING_COPPER_STATE" -> enumeration("io.papermc.paper.world.WeatheringCopperState", nms);
            case "COPPER_GOLEM_STATE" -> new Property("String", "", "$.name()", nms + ".valueOf(java.util.Objects.requireNonNull($))");
            case "DYE_COLOR" -> new Property("org.bukkit.DyeColor", "", "org.bukkit.DyeColor.valueOf($.name())", nms + ".valueOf($.name())");
            case "HUMANOID_ARM" -> new Property("org.bukkit.inventory.MainHand", "", "org.bukkit.inventory.MainHand.valueOf($.name())", nms + ".valueOf($.name())");
            case "COMPOUND_TAG" -> new Property("String", "Snbt", "ValueConversions.compound($)", "ValueConversions.compound($)");
            case "RESOLVABLE_PROFILE" -> new Property("String", "Snbt", "ValueConversions.encode(" + nms + ".CODEC,$,registries())", "ValueConversions.decode(" + nms + ".CODEC,$,registries())");
            case "PARTICLE" -> new Property("String", "Snbt", "ValueConversions.encode(net.minecraft.core.particles.ParticleTypes.CODEC,$,registries())", "ValueConversions.decode(net.minecraft.core.particles.ParticleTypes.CODEC,$,registries())");
            case "PARTICLES" -> new Property("java.util.List<String>", "Snbt", "$.stream().map(value -> ValueConversions.encode(net.minecraft.core.particles.ParticleTypes.CODEC,value,registries())).toList()", "$.stream().map(element -> ValueConversions.decode(net.minecraft.core.particles.ParticleTypes.CODEC,element,registries())).toList()");
            default -> {
                if (nms.startsWith("net.minecraft.core.Holder<")) yield new Property("org.bukkit.NamespacedKey", "", "org.bukkit.NamespacedKey.fromString($.unwrapKey().orElseThrow().location().toString())",
                        "registries().lookupOrThrow(net.minecraft.core.registries.Registries." + field.serializer() + ").getOrThrow(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries." + field.serializer() + ",net.minecraft.resources.Identifier.parse($.toString())))");
                throw new IllegalArgumentException("No public API conversion for " + field);
            }
        };
    }

    private static Property enumeration(String type, String nms) {
        return new Property(type, "", type + ".valueOf($.name())", nms + ".valueOf(java.util.Objects.requireNonNull($).name())");
    }

    private static Property direct(String type) {
        return new Property(type, "", "$", "$");
    }

    private static Property conversion(String type, String method) {
        return new Property(type, "", "ValueConversions." + method + "($)", "ValueConversions." + method + "($)");
    }

    private static Property optional(String type, String method) {
        return new Property(type, "", "$.map(ValueConversions::" + method + ").orElse(null)", "java.util.Optional.ofNullable($).map(ValueConversions::" + method + ")");
    }

    private static Property holder(String type, String registry) {
        return new Property(type, "", "org.bukkit.craftbukkit.CraftRegistry.minecraftHolderToBukkit($,net.minecraft.core.registries.Registries." + registry + ")", "ValueConversions.holder(java.util.Objects.requireNonNull($),net.minecraft.core.registries.Registries." + registry + ")");
    }

    private static String propertyName(Schema.Field field) {
        String name = field.name().replaceFirst("^DATA_", "").replaceFirst("^ID_", "").replaceFirst("_ID$", "");
        if (name.startsWith("IS_")) name = name.substring(3);
        return camel(name);
    }

    private static String camel(String text) {
        var out = new StringBuilder();
        for (String part : text.split("_")) if (!part.isEmpty()) out.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1).toLowerCase(Locale.ROOT));
        return out.toString();
    }

    private static String simple(String name) {
        return name.substring(name.lastIndexOf('.') + 1).replace('$', '_');
    }

    private static String api(String name) {
        String relative = name.substring(PREFIX.length());
        int dot = relative.lastIndexOf('.');
        return "dev.by1337.entityrender.api.entity" + (dot < 0 ? "" : "." + relative.substring(0, dot)) + ".Virtual" + simple(name);
    }

    private static String implementation(String name) {
        return "Generated_" + name.substring(PREFIX.length()).replace('.', '_').replace('$', '_');
    }

    private static void save(Path file, String source) throws IOException {
        Files.createDirectories(file.getParent());
        Files.writeString(file, source);
    }
}
