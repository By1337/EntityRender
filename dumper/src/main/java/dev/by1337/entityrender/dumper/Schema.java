package dev.by1337.entityrender.dumper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

final class Schema {
    static final List<String> FILES = List.of("version.tsv", "entities.tsv", "serializers.tsv", "fields.tsv", "hex.tsv");

    record Entity(String name, String parent, boolean abstractType, String types) {
    }

    record Field(String owner, String name, int index, String serializer, String javaType) {
    }

    static void write(Path dir, String file, String header, Collection<String> rows) throws IOException {
        var lines = new ArrayList<String>();
        lines.add(header);
        lines.addAll(rows);
        Files.write(dir.resolve(file), lines, StandardCharsets.UTF_8);
    }

    static List<String[]> read(Path dir, String file, String header) throws IOException {
        List<String> lines = Files.readAllLines(dir.resolve(file), StandardCharsets.UTF_8);
        if (lines.isEmpty() || !lines.getFirst().equals(header)) throw new IOException("Unsupported schema header: " + file);
        var result = new ArrayList<String[]>();
        int width = header.split("\t", -1).length;
        for (int i = 1; i < lines.size(); i++) {
            String[] cells = lines.get(i).split("\t", -1);
            if (cells.length != width) throw new IOException("Malformed " + file + " line " + (i + 1));
            result.add(cells);
        }
        return result;
    }

    static List<Entity> entities(Path dir) throws IOException {
        var rows = read(dir, "entities.tsv", "class\tparent\tabstract\tentityTypes");
        return rows.stream().map(r -> new Entity(r[0], r[1], Boolean.parseBoolean(r[2]), r[3])).toList();
    }

    static List<Field> fields(Path dir) throws IOException {
        var rows = read(dir, "fields.tsv", "owner\tname\tindex\tserializer\tnmsType\tdefaultStatus");
        return rows.stream().map(r -> new Field(r[0], r[1], Integer.parseInt(r[2]), r[3], r[4])).toList();
    }

    static void diff(Path oldDir, Path newDir) throws IOException {
        int changes = 0;
        for (String file : FILES) {
            var oldRows = keyed(oldDir.resolve(file), file);
            var newRows = keyed(newDir.resolve(file), file);
            var keys = new TreeSet<>(oldRows.keySet());
            keys.addAll(newRows.keySet());
            for (String key : keys) {
                String before = oldRows.get(key), after = newRows.get(key);
                if (Objects.equals(before, after)) continue;
                changes++;
                System.out.println(file + " " + key);
                if (before != null) System.out.println("- " + before);
                if (after != null) System.out.println("+ " + after);
            }
        }
        System.out.println(changes + " changed rows");
    }

    static void verify(Path expected, Path actual) throws IOException {
        var changed = new ArrayList<String>();
        for (String file : FILES) if (Files.mismatch(expected.resolve(file), actual.resolve(file)) != -1) changed.add(file);
        if (!changed.isEmpty()) throw new IOException("Golden schema mismatch: " + String.join(", ", changed) + "; use diff to inspect");
        System.out.println("Golden schema and NMS hex vectors match");
    }

    private static Map<String, String> keyed(Path path, String file) throws IOException {
        var result = new TreeMap<String, String>();
        var lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        if (lines.isEmpty()) throw new IOException("Empty schema file: " + path);
        result.put("#header", lines.getFirst());
        for (String line : lines.subList(1, lines.size())) {
            String[] cells = line.split("\t", -1);
            int width = file.equals("fields.tsv") || file.equals("hex.tsv") ? 2 : 1;
            if (cells.length < width) throw new IOException("Malformed schema row: " + path);
            String key = String.join("#", Arrays.copyOf(cells, width));
            if (result.putIfAbsent(key, line) != null) throw new IOException("Duplicate schema key: " + key);
        }
        return result;
    }
}
