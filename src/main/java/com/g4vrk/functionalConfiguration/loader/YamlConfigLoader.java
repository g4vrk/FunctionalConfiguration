package com.g4vrk.functionalConfiguration.loader;

import com.g4vrk.functionalConfiguration.YamlConfig;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

public class YamlConfigLoader extends AbstractConfigLoader<YamlConfig> {
    @Override
    protected @NotNull YamlConfig from(@NotNull YamlConfigurationLoader loader) {
        return new YamlConfig(loader);
    }
}
