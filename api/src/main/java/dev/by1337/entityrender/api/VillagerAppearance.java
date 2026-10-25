package dev.by1337.entityrender.api;

import org.bukkit.entity.Villager;
import java.util.Objects;

public record VillagerAppearance(Villager.Type type, Villager.Profession profession, int level) {
    public VillagerAppearance {
        Objects.requireNonNull(type);
        Objects.requireNonNull(profession);
        if (level < 1) throw new IllegalArgumentException("Villager level must be positive");
    }
}
