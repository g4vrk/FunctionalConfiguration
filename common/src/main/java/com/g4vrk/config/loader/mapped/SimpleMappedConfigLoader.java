package com.g4vrk.config.loader.mapped;

import com.g4vrk.config.Config;
import com.g4vrk.config.NamedConfigEntry;
import com.g4vrk.config.loader.ConfigLoader;
import com.g4vrk.config.loader.mapped.namer.FileNamer;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimpleMappedConfigLoader<C extends Config> implements MappedConfigLoader<C> {

    private final ConfigLoader<C> loader;
    private final FileNamer namer;

    public SimpleMappedConfigLoader(
            @NotNull ConfigLoader<C> loader,
            @NotNull FileNamer namer
    ) {
        this.loader = loader;
        this.namer = namer;
    }

    @Override
    public @NotNull NamedConfigEntry<C> from(@NotNull File file) throws IOException {
        final C config = loader.from(file.toPath());
        final String name = namer.nameFor(file);

        return new NamedConfigEntry<>(name, config);
    }

    @Override
    public @NotNull Iterable<NamedConfigEntry<C>> from(@NotNull Iterable<File> files) throws IOException {
        final List<NamedConfigEntry<C>> entries = new ArrayList<>();

        for (File file : files) {
            entries.add(from(file));
        }

        return entries;
    }
}
