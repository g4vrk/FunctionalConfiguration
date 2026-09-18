package com.g4vrk.config;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.ConfigurationNode;

import java.io.IOException;

public interface Config {

    @NotNull ConfigurationNode root();

    @NotNull ConfigurationNode node(final @NotNull Object... path);

    void load() throws IOException;
    void save() throws IOException;

}
