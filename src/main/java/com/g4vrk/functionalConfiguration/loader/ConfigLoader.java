package com.g4vrk.functionalConfiguration.loader;

import com.g4vrk.functionalConfiguration.Config;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public interface ConfigLoader<C extends Config> {

    @NotNull C from(@NotNull Path path);

    @NotNull Iterable<C> from(@NotNull Iterable<Path> paths);

}
