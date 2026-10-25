package dev.by1337.entityrender.core.metadata;

import net.minecraft.network.syncher.SynchedEntityData;
import java.util.*;

/** Server-thread confined. Values in an array, changes in a bit mask, schema shared. */
public final class MetadataState {
    private final MetadataLayout layout;
    private final Object[] values;
    private final BitSet dirty;

    public MetadataState(MetadataLayout layout) {
        this.layout = layout;
        values = new Object[layout.size()];
        dirty = new BitSet(layout.size());
        for (int i = 0; i < values.length; i++) values[i] = layout.initialValue(i);
    }

    public <T> T get(MetadataKey<T> key) {
        layout.require(key);
        @SuppressWarnings("unchecked")
        T value = (T) values[key.index()];
        return key.copy().apply(value);
    }

    public <T> boolean set(MetadataKey<T> key, T value) {
        return set(key, value, false);
    }

    public <T> boolean set(MetadataKey<T> key, T value, boolean force) {
        layout.require(key);
        Objects.requireNonNull(value);
        T snapshot = key.copy().apply(value);
        if (!force && Objects.equals(values[key.index()], snapshot)) return false;
        values[key.index()] = snapshot;
        dirty.set(key.index());
        return true;
    }

    public boolean isDirty() {
        return !dirty.isEmpty();
    }

    /** Does not consume dirty state. Call acknowledge only after a successful broadcast. */
    public List<SynchedEntityData.DataValue<?>> dirtySnapshot() {
        var result = new ArrayList<SynchedEntityData.DataValue<?>>(dirty.cardinality());
        for (int i = dirty.nextSetBit(0); i >= 0; i = dirty.nextSetBit(i + 1)) result.add(layout.key(i).snapshot(values[i]));
        return List.copyOf(result);
    }

    public List<SynchedEntityData.DataValue<?>> fullSnapshot() {
        var result = new ArrayList<SynchedEntityData.DataValue<?>>(values.length);
        for (int i = 0; i < values.length; i++) result.add(layout.key(i).snapshot(values[i]));
        return List.copyOf(result);
    }

    public void acknowledge() {
        dirty.clear();
    }
}
