package com.g4vrk.functionalConfiguration.manager;

import com.g4vrk.functionalConfiguration.Config;
import com.g4vrk.functionalConfiguration.NamedConfigEntry;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class SimpleConfigManager<C extends Config> implements ConfigManager<C> {

    private final Map<String, C> configs = new LinkedHashMap<>();

    @Override
    public void register(
             @NotNull NamedConfigEntry<C> namedConfigEntry
    ) {
        this.configs.put(namedConfigEntry.name(), namedConfigEntry.config());
    }

    @Override
    public void registerAll(@NotNull Iterable<NamedConfigEntry<C>> namedConfigEntries) {
        namedConfigEntries.forEach(this::register);
    }

    @Override
    public void unregister(
            @NotNull String name
    ) {
        this.configs.remove(name);
    }

    @Override
    public boolean hasConfig(
            @NotNull String name
    ) {
        return this.configs.containsKey(name);
    }

    @Override
    public @NotNull Optional<C> getConfig(
            @NotNull String name
    ) {
        return Optional.ofNullable(this.configs.get(name));
    }

    @Override
    public @NotNull Collection<C> getAll() {
        return Collections.unmodifiableCollection(this.configs.values());
    }

    @Override
    public void loadAll() throws IOException {
        for (final C config : this.configs.values()) {
            config.load();
        }
    }

    @Override
    public void saveAll() throws IOException {
        for (final C config : this.configs.values()) {
            config.save();
        }
    }

    @Override
    public @NotNull Iterator<C> iterator() {
        return getAll().iterator();
    }

}