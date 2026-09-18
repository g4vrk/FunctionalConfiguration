package com.g4vrk.config.loader;

import com.g4vrk.config.Config;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;

public interface ConfigLoader<C extends Config> {

    @NotNull C from(final @NotNull Path path) throws IOException;

    @NotNull Iterable<C> from(final @NotNull Iterable<Path> paths) throws IOException;

}
