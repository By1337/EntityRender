package dev.by1337.entityrender.dumper;

import java.nio.file.*;
import java.util.*;

public final class GeneratorChecks {
    public static void main(String[] args) throws Exception {
        Path temp = Files.createTempDirectory("entityrender-generator-checks-");
        Path schema = Files.createDirectory(temp.resolve("schema"));
        Schema.write(schema, "entities.tsv", "class\tparent\tabstract\tentityTypes", List.of(
                "net.minecraft.world.entity.Entity\t\ttrue\t",
                "net.minecraft.world.entity.a.Same\tnet.minecraft.world.entity.Entity\tfalse\tminecraft:first",
                "net.minecraft.world.entity.b.Same\tnet.minecraft.world.entity.Entity\tfalse\tminecraft:second"));
        Schema.write(schema, "fields.tsv", "owner\tname\tindex\tserializer\tnmsType\tdefaultStatus", List.of(
                "net.minecraft.world.entity.a.Same\tDATA_FLAGS\t0\tBYTE\tjava.lang.Byte\tNOT_CAPTURED",
                "net.minecraft.world.entity.b.Same\tDATA_FLAGS\t0\tBYTE\tjava.lang.Byte\tNOT_CAPTURED"));
        Path output = temp.resolve("generated");
        Generator.run(schema, output, null);
        require(Files.exists(output.resolve("api/dev/by1337/entityrender/api/entity/a/VirtualSame.java")), "First same-name entity lost");
        require(Files.exists(output.resolve("api/dev/by1337/entityrender/api/entity/b/VirtualSame.java")), "Second same-name entity lost");
        String source = Files.readString(output.resolve("core/dev/by1337/entityrender/core/metadata/GeneratedMetadata.java"));
        require(source.contains("class a") && source.contains("class b"), "Metadata namespace lost package hierarchy");
        Path defaults = temp.resolve("defaults.tsv");
        Files.writeString(defaults, "entity\towner\tfield\texpression\nnet.minecraft.world.entity.a.Same\tnet.minecraft.world.entity.b.Same\tDATA_FLAGS\t(byte) 0\n");
        boolean rejected = false;
        try {
            MetadataGenerator.generate(Schema.entities(schema), Schema.fields(schema), defaults);
        } catch (java.io.IOException expected) {
            rejected = true;
        }
        require(rejected, "Default from unrelated same-name entity accepted");
        System.out.println("Generator namespace and default ownership checks passed");
    }

    private static void require(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
}
