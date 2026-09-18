package com.g4vrk.config.manager;

import com.g4vrk.config.Config;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface ConfigManager<C extends Config> extends Iterable<C> {

    void register(
            @NotNull String key,
            @NotNull C config
    );

    void registerAll(
            @NotNull Map<String, C> configs
    );

    @Nullable C remove(
            @NotNull String key
    );

    boolean contains(
            @NotNull String key
    );

    @NotNull Optional<C> find(
            @NotNull String key
    );

    @Nullable C findOrNull(
            @NotNull String key
    );

    @NotNull Collection<C> values();

    void loadAll() throws IOException;

    void saveAll() throws IOException;

    default void reloadAll() throws IOException {
        loadAll();
    }

}
