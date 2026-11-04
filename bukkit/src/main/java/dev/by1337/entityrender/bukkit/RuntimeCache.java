package dev.by1337.entityrender.bukkit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

/** Owns extracted runtime JARs and the ClasspathUtil cache beneath the same directory. */
final class RuntimeCache {
    private RuntimeCache() {
    }

    static void prepare(Path directory, String version) throws IOException {
        Path marker = directory.resolve(".version");
        if (Files.isRegularFile(marker) && Files.readString(marker).equals(version)) {
            return;
        }
        if (Files.exists(directory)) {
            try (var paths = Files.walk(directory)) {
                for (Path path : paths.sorted(Comparator.reverseOrder()).toList()) {
                    Files.delete(path);
                }
            }
        }
        Files.createDirectories(directory);
        Files.writeString(marker, version);
    }
}
