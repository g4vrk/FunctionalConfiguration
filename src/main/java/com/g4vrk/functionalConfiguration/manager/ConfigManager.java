package com.g4vrk.functionalConfiguration.manager;

import com.g4vrk.functionalConfiguration.Config;
import com.g4vrk.functionalConfiguration.NamedConfigEntry;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

public interface ConfigManager<C extends Config> extends Iterable<C> {

    void register(
            @NotNull NamedConfigEntry<C> namedConfigEntry
    );

    void registerAll(
            @NotNull Iterable<NamedConfigEntry<C>> namedConfigEntries
    );

    void unregister(
            @NotNull String name
    );

    boolean hasConfig(
            @NotNull String name
    );

    @NotNull Optional<C> getConfig(
            @NotNull String name
    );

    @NotNull Collection<C> getAll();

    void loadAll() throws IOException;

    void saveAll() throws IOException;

    default void reloadAll() throws IOException {
        this.loadAll();
    }

}
