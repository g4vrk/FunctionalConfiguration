package com.g4vrk.config.loader;

import com.g4vrk.config.Config;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractConfigLoader<C extends Config> implements ConfigLoader<C> {

    @Override
    public @NotNull C from(@NotNull Path path) throws IOException {

        final YamlConfigurationLoader loader = YamlConfigurationLoader.builder()
                .path(path)
                .build();

        return from(loader);

    }

    @Override
    public @NotNull Iterable<C> from(@NotNull Iterable<Path> paths) throws IOException {

        final List<C> list = new ArrayList<>();

        for (final Path path : paths) {
            list.add(this.from(path));
        }

        return list;
    }

    protected abstract @NotNull C from(@NotNull YamlConfigurationLoader loader) throws IOException;

}
