package com.g4vrk.functionalConfiguration;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.BufferedReader;
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
    public @NotNull ConfigurationNode getRoot() {
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

    @Override
    public void applyDefaults(final @NotNull BufferedReader reader) throws IOException {
        final ConfigurationNode defaults = YamlConfigurationLoader.builder()
                .source(() -> reader)
                .build()
                .load();

        merge(this.root, defaults);
    }

    private void merge(
            final @NotNull ConfigurationNode target,
            final @NotNull ConfigurationNode defaults
    ) {
        defaults.childrenMap().forEach((key, defChild) -> {
            final ConfigurationNode targetChild = target.node(key);

            if (targetChild.virtual()) {
                try {
                    target.node(key).set(defChild.raw());
                } catch (SerializationException ignored) {
                }
            } else {
                merge(targetChild, defChild);
            }
        });
    }
}
