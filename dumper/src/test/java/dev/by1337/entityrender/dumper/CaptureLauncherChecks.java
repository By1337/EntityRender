package dev.by1337.entityrender.dumper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public final class CaptureLauncherChecks {
    public static void main(String[] args) throws Exception {
        Path server = Files.createTempDirectory("entityrender-bundle-checks-");
        try {
            Files.writeString(server.resolve("paper-000-partial.jar"), "incomplete download");
            bundle(server.resolve("purpur.jar"));
            try {
                CaptureLauncher.selectBundle(server);
                throw new AssertionError("Accepted a broken Paper bundle or another server fork");
            } catch (IOException expected) {
            }
            Path versioned = server.resolve("paper-1.21.10.jar");
            bundle(versioned);
            require(CaptureLauncher.selectBundle(server).equals(versioned));
            Path canonical = server.resolve("paper.jar");
            bundle(canonical);
            require(CaptureLauncher.selectBundle(server).equals(canonical));
            Files.writeString(canonical, "incomplete replacement");
            require(CaptureLauncher.selectBundle(server).equals(versioned));
        } finally {
            try (var files = Files.walk(server)) {
                for (var path : files.sorted(Comparator.reverseOrder()).toList()) Files.delete(path);
            }
        }
        System.out.println("Capture bundle selection checks passed");
    }

    private static void bundle(Path file) throws IOException {
        try (var jar = new ZipOutputStream(Files.newOutputStream(file))) {
            jar.putNextEntry(new ZipEntry("META-INF/libraries.list"));
            jar.closeEntry();
        }
    }

    private static void require(boolean condition) {
        if (!condition) throw new AssertionError("Capture selected the wrong prepared Paper bundle");
    }
}
