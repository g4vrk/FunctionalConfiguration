package com.g4vrk.config.loader.mapped.namer;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class PrimaryFileNamer implements FileNamer {

    public static final PrimaryFileNamer INSTANCE = new PrimaryFileNamer();

    private PrimaryFileNamer() {
    }

    @Override
    public @NotNull String nameFor(@NotNull File file) {
        return file.getName();
    }

}
