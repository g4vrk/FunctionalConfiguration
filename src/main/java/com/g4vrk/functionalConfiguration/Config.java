package com.g4vrk.functionalConfiguration;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.ConfigurationNode;

import java.io.BufferedReader;
import java.io.IOException;

public interface Config {

    @NotNull ConfigurationNode getRoot();

    @NotNull ConfigurationNode node(@NotNull Object... path);

    void load() throws IOException;
    void save() throws IOException;

    void applyDefaults(@NotNull BufferedReader reader) throws IOException;

}
