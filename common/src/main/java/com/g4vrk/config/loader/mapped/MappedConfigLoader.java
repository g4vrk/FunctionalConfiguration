package com.g4vrk.config.loader.mapped;

import com.g4vrk.config.Config;
import com.g4vrk.config.NamedConfigEntry;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public interface MappedConfigLoader<C extends Config> {

    @NotNull NamedConfigEntry<C> from(final @NotNull File file) throws IOException;

    @NotNull Iterable<NamedConfigEntry<C>> from(final @NotNull Iterable<File> files) throws IOException;

}
