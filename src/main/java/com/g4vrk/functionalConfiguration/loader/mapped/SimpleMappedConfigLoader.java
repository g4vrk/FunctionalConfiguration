package com.g4vrk.functionalConfiguration.loader.mapped;

import com.g4vrk.functionalConfiguration.Config;
import com.g4vrk.functionalConfiguration.NamedConfigEntry;
import com.g4vrk.functionalConfiguration.loader.ConfigLoader;
import com.g4vrk.functionalConfiguration.loader.mapped.namer.FileNamer;
import org.jetbrains.annotations.NotNull;

import java.io.File;
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
    public @NotNull NamedConfigEntry<C> from(@NotNull File file) {
        final C config = loader.from(file.toPath());
        final String name = namer.nameFor(file);

        return new NamedConfigEntry<>(name, config);
    }

    @Override
    public @NotNull Iterable<NamedConfigEntry<C>> from(@NotNull Iterable<File> files) {
        final List<NamedConfigEntry<C>> entries = new ArrayList<>();

        files.forEach(file -> entries.add(from(file)));

        return entries;
    }
}
