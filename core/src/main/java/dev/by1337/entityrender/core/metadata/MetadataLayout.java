package dev.by1337.entityrender.core.metadata;

import java.util.*;

public final class MetadataLayout {
    private static final Object UNKNOWN = new Object();
    private final MetadataKey<?>[] keys;
    private final Object[] defaults;

    public MetadataLayout(MetadataLayout parent, MetadataKey<?>... ownKeys) {
        int length = parent == null ? 0 : parent.keys.length;
        for (var key : ownKeys) length = Math.max(length, key.index() + 1);
        keys = parent == null ? new MetadataKey<?>[length] : Arrays.copyOf(parent.keys, length);
        defaults = parent == null ? new Object[length] : Arrays.copyOf(parent.defaults, length);
        for (var key : ownKeys) {
            if (keys[key.index()] != null) throw new IllegalArgumentException("Duplicate metadata index " + key.index());
            keys[key.index()] = key;
            defaults[key.index()] = UNKNOWN;
        }
        for (int i = 0; i < keys.length; i++) if (keys[i] == null) throw new IllegalArgumentException("Missing metadata index " + i);
    }

    private MetadataLayout(MetadataKey<?>[] keys, Object[] defaults) {
        this.keys = keys;
        this.defaults = defaults;
    }

    public <T> MetadataLayout withDefault(MetadataKey<T> key, T value) {
        require(key);
        Objects.requireNonNull(value);
        Object[] replaced = defaults.clone();
        replaced[key.index()] = key.copy().apply(value);
        return new MetadataLayout(keys, replaced);
    }

    Object initialValue(int index) {
        if (defaults[index] == UNKNOWN) throw new IllegalStateException("Metadata default has not been captured for index " + index);
        return copy(keys[index], defaults[index]);
    }

    public boolean hasCompleteDefaults() {
        return Arrays.stream(defaults).noneMatch(value -> value == UNKNOWN);
    }

    private static <T> T copy(MetadataKey<T> key, Object value) {
        @SuppressWarnings("unchecked")
        T typed = (T) value;
        return key.copy().apply(typed);
    }

    int size() {
        return keys.length;
    }

    MetadataKey<?> key(int index) {
        return keys[index];
    }

    void require(MetadataKey<?> key) {
        if (key.index() >= keys.length || keys[key.index()] != key) throw new IllegalArgumentException("Key belongs to another metadata layout");
    }
}
