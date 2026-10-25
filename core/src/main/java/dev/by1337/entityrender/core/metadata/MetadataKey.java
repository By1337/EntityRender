package dev.by1337.entityrender.core.metadata;

import net.minecraft.network.syncher.*;
import java.util.*;
import java.util.function.UnaryOperator;

/** Shared schema entry, never allocated per virtual entity. */
public record MetadataKey<T>(int index, EntityDataSerializer<T> serializer, UnaryOperator<T> copy) {
    public MetadataKey {
        if (index < 0 || index > 254) throw new IllegalArgumentException("Metadata index outside 0..254");
        Objects.requireNonNull(serializer);
        Objects.requireNonNull(copy);
    }

    public MetadataKey(int index, EntityDataSerializer<T> serializer) {
        this(index, serializer, serializer::copy);
    }

    public SynchedEntityData.DataValue<T> snapshot(Object value) {
        @SuppressWarnings("unchecked")
        T typed = (T) value;
        return new SynchedEntityData.DataValue<>(index, serializer, copy.apply(typed));
    }
}
