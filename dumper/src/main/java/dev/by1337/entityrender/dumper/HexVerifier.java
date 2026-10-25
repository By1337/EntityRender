package dev.by1337.entityrender.dumper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

/** Test-side bridge for comparing an independent encoder with the NMS golden vectors. */
public final class HexVerifier {
    private HexVerifier() {
    }

    @FunctionalInterface
    public interface Encoder {
        /** Encode a raw metadata value payload, without the field index or serializer id. */
        byte[] encode(String serializer, String sample, String input) throws Exception;
    }

    /** Checks every captured sample. Unsupported NMS adapters are not counted as tested. */
    public static int verify(Path schema, Encoder encoder) throws Exception {
        int checked = 0;
        for (String[] row : Schema.read(schema, "hex.tsv", HexFixtures.HEADER)) {
            if (row[3].equals("UNSUPPORTED")) continue;
            if (!row[3].equals("PASS")) throw new IOException("Invalid fixture status: " + row[3]);
            byte[] expected = HexFormat.of().parseHex(row[4]);
            byte[] actual = encoder.encode(row[0], row[1], row[2]);
            if (!Arrays.equals(expected, actual)) throw new AssertionError("Hex mismatch for " + row[0] + "/" + row[1]
                    + ": NMS=" + row[4] + ", actual=" + (actual == null ? "null" : HexFormat.of().formatHex(actual)));
            checked++;
        }
        if (checked == 0) throw new IOException("Schema has no successful hex vectors");
        return checked;
    }
}
