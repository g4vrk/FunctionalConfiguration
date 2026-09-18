package com.g4vrk.config.manager;

import com.g4vrk.config.Config;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

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
            @NotNull String key,
            @NotNull C config
    ) {

        this.configs.put(key, config);

    }

    @Override
    public void registerAll(
            @NotNull Map<String, C> configs
    ) {

        this.configs.putAll(configs);

    }

    @Override
    public @Nullable C remove(@NotNull String key) {

        return this.configs.remove(key);

    }

    @Override
    public boolean contains(@NotNull String key) {

        return this.configs.containsKey(key);

    }

    @Override
    public @NotNull Optional<C> find(@NotNull String key) {
        return Optional.ofNullable(this.findOrNull(key));
    }

    @Override
    public @Nullable C findOrNull(@NotNull String key) {
        return this.configs.get(key);
    }

    @Override
    public @NotNull Collection<C> values() {
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
        return values().iterator();
    }

}