package dev.by1337.entityrender.dumper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipFile;
import java.util.zip.ZipException;

/** Compiles a build-time probe against the supplied core and runs it in an isolated child JVM. */
final class CaptureLauncher {
    static void run(Map<String, String> options) throws Exception {
        Path server = Main.path(options, "server"), out = Main.path(options, "out");
        if (Files.exists(out)) throw new IOException("Capture output already exists: " + out);
        Path minecraft;
        try (var versions = Files.walk(server.resolve("versions"))) {
            var jars = versions.filter(Files::isRegularFile).filter(p -> p.getFileName().toString().startsWith("paper-") && p.toString().endsWith(".jar")).sorted().toList();
            if (jars.size() != 1) throw new IOException("Expected one prepared Paper version in " + server);
            minecraft = jars.getFirst();
        }
        String version;
        try (var jar = new ZipFile(minecraft.toFile()); var input = jar.getInputStream(jar.getEntry("version.json"))) {
            version = new String(input.readAllBytes(), StandardCharsets.UTF_8).replaceAll("(?s).*?\\\"id\\\"\\s*:\\s*\\\"([^\\\"]+)\\\".*", "$1");
        }
        var classpath = new ArrayList<Path>();
        classpath.add(minecraft);
        Path bundle = selectBundle(server);
        classpath.addAll(Launcher.extractLibraries(bundle, server, server.resolve("libraries")));
        Path work = Files.createTempDirectory("entityrender-capture-");
        Files.createDirectories(work.resolve("plugins"));
        Files.createDirectories(work.resolve("classes"));
        Path source = work.resolve("DefaultsProbe.java");
        try (var input = Objects.requireNonNull(CaptureLauncher.class.getResourceAsStream("/capture/DefaultsProbe.java"))) {
            Files.copy(input, source);
        }
        Path java = Path.of(options.getOrDefault("java", Path.of(System.getProperty("java.home"), "bin", "java").toString()));
        String cp = String.join(File.pathSeparator, classpath.stream().map(Path::toString).toList());
        var compile = new ProcessBuilder(java.resolveSibling("javac").toString(), "-encoding", "UTF-8", "-cp", cp, "-d", work.resolve("classes").toString(), source.toString()).directory(work.toFile()).inheritIO().start();
        if (compile.waitFor() != 0) throw new IOException("Capture probe does not match this core; workdir: " + work);
        try (var jar = new JarOutputStream(Files.newOutputStream(work.resolve("plugins/entityrender-capture.jar")))) {
            try (var classes = Files.walk(work.resolve("classes"))) {
                for (var file : classes.filter(Files::isRegularFile).toList()) {
                    jar.putNextEntry(new JarEntry(work.resolve("classes").relativize(file).toString().replace(File.separatorChar, '/')));
                    Files.copy(file, jar);
                    jar.closeEntry();
                }
            }
            jar.putNextEntry(new JarEntry("paper-plugin.yml"));
            jar.write(("name: EntityRenderCapture\nversion: '1'\nmain: dev.by1337.entityrender.capture.DefaultsProbe\napi-version: '" + version + "'\n").getBytes(StandardCharsets.UTF_8));
            jar.closeEntry();
        }
        Files.writeString(work.resolve("eula.txt"), "eula=true\n");
        Files.writeString(work.resolve("server.properties"), "server-ip=127.0.0.1\nserver-port=0\nonline-mode=false\nenforce-secure-profile=false\nenable-status=false\nview-distance=2\nsimulation-distance=2\nlevel-type=minecraft:flat\ngenerator-settings={\"layers\":[{\"block\":\"minecraft:bedrock\",\"height\":1}],\"biome\":\"minecraft:plains\"}\ngenerate-structures=false\nallow-nether=false\nlevel-seed=1\ndifficulty=peaceful\npause-when-empty-seconds=-1\n");
        var args = List.of("-Dfile.encoding=UTF-8", "-Dterminal.jline=false", "-Dterminal.ansi=false", "-XX:ActiveProcessorCount=2", "-Xms256m", "-Xmx1536m", "-Dentityrender.capture.out=" + out, "-cp", cp, "org.bukkit.craftbukkit.Main", "--nogui");
        Path argfile = work.resolve("args.txt");
        Files.write(argfile, args.stream().map(CaptureLauncher::quote).toList());
        System.out.println("Capturing fully loaded Paper " + version + " in child JVM; workdir: " + work);
        var process = new ProcessBuilder(java.toString(), "@" + argfile).directory(work.toFile()).redirectErrorStream(true).redirectOutput(work.resolve("console.log").toFile()).start();
        try {
            if (!process.waitFor(120, TimeUnit.SECONDS)) throw new IOException("Capture timed out; log: " + work.resolve("console.log"));
            if (process.exitValue() != 0 || !Files.exists(work.resolve("capture.complete"))) throw new IOException("Capture failed; log: " + work.resolve("console.log"));
        } finally {
            if (process.isAlive()) process.destroyForcibly().waitFor();
        }
        System.out.println("Captured defaults: " + out);
    }

    static Path selectBundle(Path server) throws IOException {
        try (var files = Files.list(server)) {
            var candidates = files.filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().startsWith("paper") && path.toString().endsWith(".jar"))
                    .sorted(Comparator.comparing((Path path) -> !path.getFileName().toString().equals("paper.jar"))
                            .thenComparing(path -> path.getFileName().toString()))
                    .toList();
            for (var path : candidates) {
                try (var jar = new ZipFile(path.toFile())) {
                    if (jar.getEntry("META-INF/libraries.list") != null) return path;
                } catch (ZipException incompleteDownload) {
                    // A leftover partial download must not hide a valid prepared bundle.
                }
            }
        }
        throw new IOException("No valid local Paper bundle in " + server);
    }

    private static String quote(String value) {
        return '"' + value.replace("\\", "\\\\").replace("\"", "\\\"") + '"';
    }
}
