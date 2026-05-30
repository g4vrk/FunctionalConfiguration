package com.g4vrk.functionalConfiguration.loader.mapped;

import com.g4vrk.functionalConfiguration.Config;
import com.g4vrk.functionalConfiguration.NamedConfigEntry;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public interface MappedConfigLoader<C extends Config> {

    @NotNull NamedConfigEntry<C> from(@NotNull File file);

    @NotNull Iterable<NamedConfigEntry<C>> from(@NotNull Iterable<File> files);

}
