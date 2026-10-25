package dev.by1337.entityrender.core.metadata;

import net.minecraft.network.syncher.SynchedEntityData;
import java.util.*;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.function.UnaryOperator;

/** Concurrent writers; a single publisher drains changes before handing owned values to transport. */
public final class MetadataState {
    private final MetadataLayout layout;
    private final AtomicReferenceArray<Object> values;
    private static final VarHandle WORD = MethodHandles.arrayElementVarHandle(long[].class);
    private final long[] dirty;

    public MetadataState(MetadataLayout layout) {
        this.layout = layout;
        values = new AtomicReferenceArray<>(layout.size());
        dirty = new long[(layout.size() + 63) / 64];
        for (int i = 0; i < values.length(); i++) values.set(i, layout.initialValue(i));
    }

    public <T> T get(MetadataKey<T> key) {
        return key.copy().apply(getOwned(key));
    }

    /** Internal values are immutable by ownership: callers must not mutate or expose them. */
    @SuppressWarnings("unchecked")
    public <T> T getOwned(MetadataKey<T> key) {
        layout.require(key);
        return (T) values.getAcquire(key.index());
    }

    public <T> boolean set(MetadataKey<T> key, T value) {
        return set(key, value, false);
    }

    public <T> boolean set(MetadataKey<T> key, T value, boolean force) {
        return setOwned(key, key.copy().apply(Objects.requireNonNull(value)), force);
    }

    public <T> boolean setOwned(MetadataKey<T> key, T value, boolean force) {
        layout.require(key);
        Objects.requireNonNull(value);
        Object previous = values.getAcquire(key.index());
        if (!force && Objects.equals(previous, value)) return false;
        values.setRelease(key.index(), value);
        mark(key.index());
        return true;
    }

    public <T> boolean update(MetadataKey<T> key, UnaryOperator<T> operation) {
        layout.require(key);
        T before, after;
        do {
            before = getOwned(key);
            after = Objects.requireNonNull(operation.apply(before));
            if (Objects.equals(before, after)) return false;
        } while (!values.compareAndSet(key.index(), before, after));
        mark(key.index());
        return true;
    }

    private void mark(int index) {
        WORD.getAndBitwiseOrRelease(dirty, index >>> 6, 1L << index);
    }

    public boolean isDirty() {
        for (int i = 0; i < dirty.length; i++) if ((long) WORD.getAcquire(dirty, i) != 0) return true;
        return false;
    }

    /** Drain before reading values. Racing writes retain their bit for the next publication. */
    public List<SynchedEntityData.DataValue<?>> drainOwned() {
        return snapshot(true, false);
    }

    public List<SynchedEntityData.DataValue<?>> dirtySnapshot() {
        return snapshot(false, true);
    }

    private List<SynchedEntityData.DataValue<?>> snapshot(boolean drain, boolean copy) {
        var result = new ArrayList<SynchedEntityData.DataValue<?>>();
        for (int word = 0; word < dirty.length; word++) {
            long mask = drain ? (long) WORD.getAndSetAcquire(dirty, word, 0L) : (long) WORD.getAcquire(dirty, word);
            while (mask != 0) {
                int bit = Long.numberOfTrailingZeros(mask), index = word * 64 + bit;
                var key = layout.key(index);
                result.add(copy ? key.snapshot(values.getAcquire(index)) : ownedValue(key, values.getAcquire(index)));
                mask &= mask - 1;
            }
        }
        return List.copyOf(result);
    }

    public List<SynchedEntityData.DataValue<?>> fullSnapshot() {
        var result = new ArrayList<SynchedEntityData.DataValue<?>>(values.length());
        for (int i = 0; i < values.length(); i++) result.add(layout.key(i).snapshot(values.getAcquire(i)));
        return List.copyOf(result);
    }

    public List<SynchedEntityData.DataValue<?>> fullOwnedSnapshot() {
        var result = new ArrayList<SynchedEntityData.DataValue<?>>(values.length());
        for (int i = 0; i < values.length(); i++) result.add(ownedValue(layout.key(i), values.getAcquire(i)));
        return List.copyOf(result);
    }

    @SuppressWarnings("unchecked")
    private static <T> SynchedEntityData.DataValue<T> ownedValue(MetadataKey<T> key, Object value) {
        return new SynchedEntityData.DataValue<>(key.index(), key.serializer(), (T) value);
    }

    /** Only for single-threaded diagnostics. Runtime publishing uses drainOwned(). */
    public void acknowledge() {
        for (int i = 0; i < dirty.length; i++) WORD.getAndSetAcquire(dirty, i, 0L);
    }
}
