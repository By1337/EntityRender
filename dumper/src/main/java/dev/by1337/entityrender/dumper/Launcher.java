package dev.by1337.entityrender.dumper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.security.MessageDigest;
import java.util.*;
import java.util.regex.Pattern;
import java.util.zip.ZipFile;

final class Launcher {
    static void run(Map<String, String> options) throws Exception {
        Path minecraft = Main.path(options, "minecraft");
        Path out = Main.path(options, "out");
        if (Files.exists(out)) throw new IOException("Output already exists; use a new directory: " + out);
        String metadata;
        try (var jar = new ZipFile(minecraft.toFile())) {
            var entry = jar.getEntry("version.json");
            if (entry == null) throw new IOException("Minecraft jar has no version.json: " + minecraft);
            try (var in = jar.getInputStream(entry)) {
                metadata = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            }
            if (jar.getEntry("net/minecraft/server/Bootstrap.class") == null) throw new IOException("Expected unbundled, Mojang-named Minecraft classes; this tool does not remap jars");
        }
        String version = jsonScalar(metadata, "id");
        String protocol = jsonScalar(metadata, "protocol_version");
        String javaVersion = jsonScalar(metadata, "java_version");
        Path cache = Path.of(options.getOrDefault("cache", ".entityrender/cache")).toAbsolutePath().normalize();
        var classpath = new ArrayList<Path>();
        classpath.add(Path.of(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI()));
        classpath.add(minecraft);
        if (options.containsKey("bundle")) {
            classpath.addAll(extractLibraries(Main.path(options, "bundle"), cache, options.containsKey("libraries") ? Main.path(options, "libraries") : null));
        } else if (options.containsKey("libraries")) {
            Path libraries = Main.path(options, "libraries");
            try (var files = Files.walk(libraries)) {
                classpath.addAll(files.filter(Files::isRegularFile).filter(p -> p.toString().endsWith(".jar")).sorted().toList());
            }
        } else {
            Path bundle = options.containsKey("bundle") ? Main.path(options, "bundle")
                    : minecraft.resolveSibling("minecraft_server_" + version + ".jar");
            if (!Files.isRegularFile(bundle)) throw new IOException("Supply --bundle <original server jar> or --libraries <directory>; not found: " + bundle);
            classpath.addAll(extractLibraries(bundle, cache, null));
        }
        if (classpath.size() <= 2) throw new IOException("No Minecraft dependency jars found");
        Path java = Path.of(options.getOrDefault("java", Path.of(System.getProperty("java.home"), "bin", "java").toString()));
        Files.createDirectories(cache);
        Path work = Files.createTempDirectory(cache, "worker-");
        Path argfile = work.resolve("args.txt");
        String cp = String.join(File.pathSeparator, classpath.stream().map(Path::toString).toList());
        var arguments = List.of("-Dfile.encoding=UTF-8", "-Duser.language=en", "-Duser.country=US", "-cp", cp,
                Main.class.getName(), "worker", "--out", out.toString(), "--version", version, "--protocol", protocol);
        Files.write(argfile, arguments.stream().map(Launcher::quote).toList(), StandardCharsets.UTF_8);
        System.out.println("Bootstrapping Minecraft " + version + " (requires Java " + javaVersion + ") using " + java);
        int exit = new ProcessBuilder(java.toString(), "@" + argfile).directory(work.toFile()).inheritIO().start().waitFor();
        if (exit != 0) throw new IOException("Minecraft worker exited with code " + exit + "; check --java (Minecraft requires Java " + javaVersion + "). Work directory: " + work);
    }

    private static List<Path> extractLibraries(Path bundle, Path cache, Path libraries) throws Exception {
        var result = new ArrayList<Path>();
        try (var jar = new ZipFile(bundle.toFile())) {
            var list = jar.getEntry("META-INF/libraries.list");
            if (list == null) throw new IOException("No META-INF/libraries.list in " + bundle);
            List<String> lines;
            try (var in = jar.getInputStream(list)) {
                lines = new String(in.readAllBytes(), StandardCharsets.UTF_8).lines().toList();
            }
            for (String line : lines) {
                if (line.isBlank()) continue;
                String[] parts = line.split("\t", -1);
                if (parts.length != 3 || !parts[0].matches("[a-fA-F0-9]{64}")) throw new IOException("Invalid libraries.list entry: " + line);
                if (libraries != null) {
                    Path local = libraries.resolve(parts[2]).normalize();
                    if (!local.startsWith(libraries) || !Files.isRegularFile(local) || !sha256(local).equalsIgnoreCase(parts[0])) throw new IOException("Missing or mismatched listed dependency: " + local);
                    result.add(local);
                    continue;
                }
                Path target = cache.resolve("libraries").resolve(parts[0].toLowerCase(Locale.ROOT) + ".jar");
                if (!Files.isRegularFile(target) || !sha256(target).equalsIgnoreCase(parts[0])) {
                    var entry = jar.getEntry("META-INF/libraries/" + parts[2]);
                    if (entry == null) throw new IOException("Missing bundled dependency: " + parts[2]);
                    Files.createDirectories(target.getParent());
                    Path temp = Files.createTempFile(target.getParent(), "extract-", ".jar");
                    try {
                        try (var in = jar.getInputStream(entry)) {
                            Files.copy(in, temp, StandardCopyOption.REPLACE_EXISTING);
                        }
                        if (!sha256(temp).equalsIgnoreCase(parts[0])) throw new IOException("Dependency checksum mismatch: " + parts[2]);
                        Files.move(temp, target, StandardCopyOption.REPLACE_EXISTING);
                    } finally {
                        Files.deleteIfExists(temp);
                    }
                }
                result.add(target);
            }
        }
        return result;
    }

    private static String sha256(Path path) throws Exception {
        var digest = MessageDigest.getInstance("SHA-256");
        try (var in = Files.newInputStream(path)) {
            byte[] buffer = new byte[65536];
            for (int count; (count = in.read(buffer)) != -1;) digest.update(buffer, 0, count);
        }
        return HexFormat.of().formatHex(digest.digest());
    }

    private static String jsonScalar(String json, String key) throws IOException {
        var match = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*(?:\"([^\"]*)\"|(\\d+))").matcher(json);
        if (!match.find()) throw new IOException("Missing " + key + " in version.json");
        String value = match.group(1) != null ? match.group(1) : match.group(2);
        if (!value.matches("[A-Za-z0-9._+\\-]+")) throw new IOException("Unsupported version.json value: " + key);
        return value;
    }

    private static String quote(String value) {
        return "\"" + value.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r") + "\"";
    }
}
