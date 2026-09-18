package com.g4vrk.config;

import org.jetbrains.annotations.NotNull;

public record NamedConfigEntry<C extends Config>(
        @NotNull String name,
        @NotNull C config
) {}
