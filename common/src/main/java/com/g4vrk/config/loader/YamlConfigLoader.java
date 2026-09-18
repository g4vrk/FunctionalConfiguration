package com.g4vrk.config.loader;

import com.g4vrk.config.YamlConfig;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.IOException;

public class YamlConfigLoader extends AbstractConfigLoader<YamlConfig> {

    private final boolean load;

    public YamlConfigLoader() {
        this(true);
    }

    public YamlConfigLoader(
            boolean load
    ) {
        this.load = load;
    }

    @Override
    protected @NotNull YamlConfig from(@NotNull YamlConfigurationLoader loader) throws IOException {

        final YamlConfig yamlConfig = new YamlConfig(loader);

        if (this.load) yamlConfig.load();

        return yamlConfig;

    }
}
