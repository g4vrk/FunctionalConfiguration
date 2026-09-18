package com.g4vrk.config.loader.mapped.namer;

import org.jetbrains.annotations.NotNull;

import java.io.File;

@FunctionalInterface
public interface FileNamer {

    @NotNull String nameFor(final @NotNull File file);

}
