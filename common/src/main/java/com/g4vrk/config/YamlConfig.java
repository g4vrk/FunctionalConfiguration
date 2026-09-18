package com.g4vrk.config;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.IOException;

public class YamlConfig implements Config {

    private final YamlConfigurationLoader loader;

    private ConfigurationNode root;

    public YamlConfig(
            @NotNull YamlConfigurationLoader loader
    ) {
        this.loader = loader;
    }

    @Override
    public @NotNull ConfigurationNode root() {
        return root;
    }

    @Override
    public @NotNull ConfigurationNode node(@NotNull Object... path) {
        return root.node(path);
    }

    @Override
    public void load() throws IOException {
        this.root = loader.load();
    }

    @Override
    public void save() throws IOException {
        loader.save(root);
    }

}
