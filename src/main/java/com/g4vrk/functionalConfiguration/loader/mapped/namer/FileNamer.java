package com.g4vrk.functionalConfiguration.loader.mapped.namer;

import org.jetbrains.annotations.NotNull;

import java.io.File;

@FunctionalInterface
public interface FileNamer {
    @NotNull String nameFor(@NotNull File file);
}
