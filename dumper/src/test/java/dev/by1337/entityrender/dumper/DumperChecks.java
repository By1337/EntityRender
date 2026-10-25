package dev.by1337.entityrender.dumper;

import java.nio.file.*;
import java.util.*;

/** Integration assertions against actual Minecraft output. No test framework required. */
public final class DumperChecks {
    public static void main(String[] args) throws Exception {
        Path schema = Path.of(args[0]);
        var hex = new HashMap<String, String>();
        for (String[] row : Schema.read(schema, "hex.tsv", HexFixtures.HEADER)) {
            if (row[3].equals("PASS")) hex.put(row[0] + "/" + row[1], row[4]);
        }
        expect(hex, "INT/0", "00");
        expect(hex, "INT/127", "7f");
        expect(hex, "INT/128", "8001");
        expect(hex, "INT/-1", "ffffffff0f");
        expect(hex, "LONG/-1", "ffffffffffffffffff01");
        expect(hex, "BOOLEAN/false", "00");
        expect(hex, "BOOLEAN/true", "01");
        expect(hex, "STRING/ascii", "0c456e7469747952656e646572");
        expect(hex, "FLOAT/80000000", "80000000");
        expect(hex, "BLOCK_POS/0,0,0", "0000000000000000");
        expect(hex, "OPTIONAL_UNSIGNED_INT/empty", "00");
        expect(hex, "OPTIONAL_UNSIGNED_INT/0", "01");
        var fields = Schema.fields(schema);
        var creeper = fields.stream().filter(f -> f.owner().equals("net.minecraft.world.entity.monster.Creeper") && f.name().equals("DATA_IS_POWERED")).findFirst().orElseThrow();
        if (creeper.index() != 17 || !creeper.serializer().equals("BOOLEAN")) throw new AssertionError("Unexpected 1.21.4 creeper metadata");
        boolean mismatchDetected = false;
        try {
            HexVerifier.verify(schema, (serializer, sample, input) -> new byte[]{ 42 });
        } catch (AssertionError expected) {
            mismatchDetected = true;
        }
        if (!mismatchDetected) throw new AssertionError("Hex verifier accepted invalid encoder");
        System.out.println("Minecraft 1.21.4 integration assertions passed (" + hex.size() + " golden vectors)");
    }

    private static void expect(Map<String, String> actual, String key, String expected) {
        if (!expected.equals(actual.get(key))) throw new AssertionError(key + ": expected " + expected + ", got " + actual.get(key));
    }
}
