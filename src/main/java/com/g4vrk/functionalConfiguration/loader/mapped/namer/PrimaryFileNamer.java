package com.g4vrk.functionalConfiguration.loader.mapped.namer;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class PrimaryFileNamer implements FileNamer {
    @Override
    public @NotNull String nameFor(@NotNull File file) {
        return file.getName();
    }
}
