package dev.by1337.entityrender.dumper;

import java.nio.file.Path;
import java.util.*;

public final class Main {
    public static void main(String[] args) {
        try {
            if (args.length == 0 || args[0].equals("--help")) {
                help();
                return;
            }
            var options = options(args);
            switch (args[0]) {
                case "dump" -> Launcher.run(options);
                case "generate" -> Generator.run(path(options, "schema"), path(options, "out"), options.containsKey("defaults") ? path(options, "defaults") : null);
                case "runtime" -> RuntimeGenerator.run(path(options, "schema"), path(options, "out"));
                case "diff" -> Schema.diff(path(options, "old"), path(options, "new"));
                case "verify" -> Schema.verify(path(options, "expected"), path(options, "actual"));
                case "worker" -> Worker.run(path(options, "out"), required(options, "version"), required(options, "protocol"));
                default -> throw new IllegalArgumentException("Unknown command: " + args[0]);
            }
        } catch (Throwable e) {
            var diagnostics = new java.io.PrintStream(new java.io.FileOutputStream(java.io.FileDescriptor.err), true, java.nio.charset.StandardCharsets.UTF_8);
            diagnostics.println("EntityRender dumper failed: " + e);
            e.printStackTrace(diagnostics);
            System.exit(1);
        }
    }

    private static Map<String, String> options(String[] args) {
        Set<String> allowed = switch (args[0]) {
            case "dump" -> Set.of("minecraft", "bundle", "libraries", "java", "out", "cache");
            case "generate" -> Set.of("schema", "out", "defaults");
            case "runtime" -> Set.of("schema", "out");
            case "diff" -> Set.of("old", "new");
            case "verify" -> Set.of("expected", "actual");
            case "worker" -> Set.of("out", "version", "protocol");
            default -> Set.of();
        };
        var result = new HashMap<String, String>();
        for (int i = 1; i < args.length; i += 2) {
            if (!args[i].startsWith("--") || i + 1 == args.length) throw new IllegalArgumentException("Expected --option value");
            String key = args[i].substring(2);
            if (!allowed.contains(key)) throw new IllegalArgumentException("Unknown option: " + args[i]);
            if (result.putIfAbsent(key, args[i + 1]) != null) throw new IllegalArgumentException("Duplicate option: " + args[i]);
        }
        return result;
    }

    static String required(Map<String, String> options, String key) {
        String value = options.get(key);
        if (value == null || value.isBlank()) throw new IllegalArgumentException("Missing --" + key);
        return value;
    }

    static Path path(Map<String, String> options, String key) {
        return Path.of(required(options, key)).toAbsolutePath().normalize();
    }

    private static void help() {
        System.out.println("""
                EntityRender metadata tools (Java 21+, no third-party tool dependencies)
                dump --minecraft <Mojang-named.jar> --out <schema-dir>
                     [--bundle <original-server-bundler.jar>] [--libraries <directory>]
                     [--java <java-executable>] [--cache <directory>]
                generate --schema <schema-dir> --out <new-directory> [--defaults <reviewed-defaults.tsv>]
                diff --old <schema-dir> --new <schema-dir>
                verify --expected <schema-dir> --actual <schema-dir>

                No downloads or remapping. Minecraft dependencies come from a local bundle
                or directory. A sibling minecraft_server_<version>.jar is detected automatically.
                Each dump bootstraps Minecraft in a separate JVM; no game server is started.
                """);
    }
}
