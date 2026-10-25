package dev.by1337.entityrender.dumper;

import java.util.List;

/** Semantic helpers for packed fields and spawn-only state, using Bukkit-facing contracts. */
final class RuntimeExtras {
    static void append(Schema.Entity entity, List<Schema.Field> own, boolean hangingDirection, StringBuilder api, StringBuilder impl) {
        String simple = entity.name().substring(entity.name().lastIndexOf('.') + 1);
        String namespace = "dev.by1337.entityrender.core.metadata.GeneratedMetadata." + entity.name().substring("net.minecraft.world.entity.".length()).replace('$', '.');
        String flags = own.stream().filter(f -> f.serializer().equals("BYTE") && f.name().contains("FLAGS")).map(f -> namespace + "." + f.name()).findFirst().orElse(null);
        if (simple.equals("Mob") && flags != null) {
            flag(api, impl, "NoAi", flags, 1);
            flag(api, impl, "LeftHanded", flags, 2);
            flag(api, impl, "Aggressive", flags, 4);
        }
        if (simple.equals("TamableAnimal") && flags != null) {
            flag(api, impl, "Sitting", flags, 1);
            flag(api, impl, "Tamed", flags, 4);
        }
        if (simple.equals("Bat") && flags != null) flag(api, impl, "Hanging", flags, 1);
        if (simple.equals("Sheep")) {
            String key = namespace + ".DATA_WOOL_ID";
            method(api, impl, "boolean", "Sheared", "flag(" + key + ",16)", "flag(" + key + ",16,value);");
            method(api, impl, "org.bukkit.DyeColor", "Color", "org.bukkit.DyeColor.valueOf(net.minecraft.world.item.DyeColor.byId(get(" + key + ") & 15).name())",
                    "set(" + key + ",(byte)((get(" + key + ") & ~15) | net.minecraft.world.item.DyeColor.valueOf(value.name()).getId()));");
        }
        if (simple.equals("Horse")) {
            String horsePackage = entity.name().substring(0, entity.name().lastIndexOf('.'));
            String key = namespace + ".DATA_ID_TYPE_VARIANT";
            method(api, impl, "org.bukkit.entity.Horse.Color", "Color", "org.bukkit.entity.Horse.Color.valueOf(" + horsePackage + ".Variant.byId(get(" + key + ") & 255).name())",
                    "set(" + key + ",(get(" + key + ") & ~255) | " + horsePackage + ".Variant.valueOf(value.name()).getId());");
            method(api, impl, "org.bukkit.entity.Horse.Style", "Style", "org.bukkit.entity.Horse.Style.valueOf(" + horsePackage + ".Markings.byId((get(" + key + ") >> 8) & 255).name().replace(\"WHITE_FIELD\",\"WHITEFIELD\"))",
                    "set(" + key + ",(get(" + key + ") & ~65280) | " + horsePackage + ".Markings.valueOf(value.name().replace(\"WHITEFIELD\",\"WHITE_FIELD\")).getId() << 8);");
        }
        if (simple.equals("FallingBlockEntity")) {
            method(api, impl, "org.bukkit.block.data.BlockData", "BlockData", "ValueConversions.block(net.minecraft.world.level.block.Block.stateById(getSpawnData()))",
                    "setSpawnData(net.minecraft.world.level.block.Block.getId(ValueConversions.block(value)));");
        }
        if (simple.equals("ExperienceOrb") && own.isEmpty()) {
            method(api, impl, "int", "ExperienceValue", "getSpawnData()", "if (value < 0 || value > 32767) throw new IllegalArgumentException(\"Experience value must be 0..32767\"); setSpawnData(value);");
        }
        if (simple.equals("ExperienceOrb") && own.stream().anyMatch(f -> f.name().equals("DATA_VALUE"))) {
            method(api, impl, "int", "ExperienceValue", "get(" + namespace + ".DATA_VALUE)", "if (value < 0) throw new IllegalArgumentException(\"Experience value must be nonnegative\"); set(" + namespace + ".DATA_VALUE,value);");
        }
        if (simple.equals("ItemFrame") || simple.equals("Painting")) {
            method(api, impl, "org.bukkit.block.BlockFace", "Facing", hangingDirection ? "getDirection()" : "ValueConversions.direction(net.minecraft.core.Direction.from3DDataValue(getSpawnData()))",
                    (simple.equals("Painting") ? "if (value == org.bukkit.block.BlockFace.UP || value == org.bukkit.block.BlockFace.DOWN) throw new IllegalArgumentException(\"Painting requires a horizontal face\"); " : "")
                            + (hangingDirection ? "setDirection(value); " : "") + "setSpawnData(ValueConversions.direction(value).get3DDataValue());");
        }
    }

    private static void flag(StringBuilder api, StringBuilder impl, String name, String key, int mask) {
        method(api, impl, "boolean", name, "flag(" + key + "," + mask + ")", "flag(" + key + "," + mask + ",value);");
    }

    private static void method(StringBuilder api, StringBuilder impl, String type, String name, String getter, String setter) {
        String prefix = type.equals("boolean") ? "is" : "get";
        api.append("    ").append(type).append(" ").append(prefix).append(name).append("();\n    void set").append(name).append("(").append(type).append(" value);\n");
        impl.append("    @Override public ").append(type).append(" ").append(prefix).append(name).append("() { return ").append(getter).append("; }\n")
                .append("    @Override public void set").append(name).append("(").append(type).append(" value) { ").append(setter).append(" }\n");
    }
}
