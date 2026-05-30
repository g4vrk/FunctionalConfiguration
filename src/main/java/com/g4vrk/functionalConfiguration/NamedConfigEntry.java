package com.g4vrk.functionalConfiguration;

import org.jetbrains.annotations.NotNull;

public record NamedConfigEntry<C extends Config>(
        @NotNull String name,
        @NotNull C config
) {}
