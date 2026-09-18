# FunctionalConfiguration

**Compact configuration library for Java applications, built on top of Configurate.**

[![Build](https://github.com/g4vrk/FunctionalConfiguration/actions/workflows/build.yml/badge.svg)](https://github.com/g4vrk/FunctionalConfiguration/actions/workflows/build.yml)
[![Java](https://img.shields.io/badge/Java-17+-orange)](.)
[![Configurate](https://img.shields.io/badge/Configurate-4.2.0-blue)](.)
[![Version](https://img.shields.io/badge/version-1.1.0-informational)](.)
[![JitPack](https://jitpack.io/v/g4vrk/FunctionalConfiguration.svg)](https://jitpack.io/#g4vrk/FunctionalConfiguration)
[![License](https://img.shields.io/github/license/g4vrk/FunctionalConfiguration)](LICENSE)

---

## Что это

FunctionalConfiguration — небольшая abstraction layer над Configurate для работы с конфигурационными файлами.

Основной API разделён на:

* `Config` — конфигурация;
* `ConfigLoader` — создание конфигураций;
* `ConfigManager` — регистрация и управление несколькими конфигурациями;
* `MappedConfigLoader` — загрузка конфигураций вместе с их именами;
* `YamlConfig` — готовая YAML-реализация.

---

## Подключение через JitPack

```kotlin
repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.g4vrk:FunctionalConfiguration:1.1.0")
}
```

Для Configurate:

```kotlin
dependencies {
    implementation("org.spongepowered:configurate-yaml:4.2.0")
}
```

<details>
<summary><b>Gradle Groovy</b></summary>

```groovy
repositories {
    mavenCentral()
    maven { url = uri('https://jitpack.io') }
}

dependencies {
    implementation 'com.github.g4vrk:FunctionalConfiguration:1.1.0'
    implementation 'org.spongepowered:configurate-yaml:4.2.0'
}
```

</details>

<details>
<summary><b>Maven</b></summary>

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.g4vrk.FunctionalConfiguration</groupId>
        <artifactId>common</artifactId>
        <version>1.1.0</version>
    </dependency>

    <dependency>
        <groupId>org.spongepowered</groupId>
        <artifactId>configurate-yaml</artifactId>
        <version>4.2.0</version>
    </dependency>
</dependencies>
```

</details>

---

## YAML

Создайте `YamlConfig` из Configurate loader:

```java
YamlConfigurationLoader loader = YamlConfigurationLoader.builder()
        .path(path)
        .build();

YamlConfig config = new YamlConfig(loader);

config.load();
```

Доступ к данным:

```java
config.root();
config.node("database", "host");
config.node("database", "port");
```

Сохранение:

```java
config.save();
```

---

## ConfigLoader

Вместо создания Configurate loader вручную можно использовать `YamlConfigLoader`:

```java
YamlConfigLoader loader = new YamlConfigLoader();

YamlConfig config = loader.from(path);
```

По умолчанию конфигурация загружается сразу.

Для отложенной загрузки:

```java
YamlConfigLoader loader = new YamlConfigLoader(false);

YamlConfig config = loader.from(path);

config.load();
```

Несколько файлов:

```java
Iterable<YamlConfig> configs = loader.from(paths);
```

---

## ConfigManager

`ConfigManager` хранит конфигурации по ключу:

```java
ConfigManager<YamlConfig> manager = new YamlConfigManager<>();

manager.register("config", config);
manager.register("messages", messages);
```

Массовая регистрация:

```java
manager.registerAll(Map.of(
        "config", config,
        "messages", messages
));
```

Поиск:

```java
manager.find("messages");
```

Без `Optional`:

```java
YamlConfig messages = manager.findOrNull("messages");
```

Проверка:

```java
if (manager.contains("messages")) {
    // ...
}
```

Удаление:

```java
YamlConfig removed = manager.remove("messages");
```

Все конфигурации:

```java
for (YamlConfig config : manager.values()) {
    // ...
}
```

---

## Load & Save

Все зарегистрированные конфигурации можно обработать одним вызовом:

```java
manager.loadAll();
```

```java
manager.saveAll();
```

```java
manager.reloadAll();
```

---

## Mapped configs

`MappedConfigLoader` связывает конфигурацию с именем файла.

```java
MappedConfigLoader<YamlConfig> loader =
        new SimpleMappedConfigLoader<>(
                new YamlConfigLoader(),
                PrimaryFileNamer.INSTANCE
        );
```

Загрузка:

```java
NamedConfigEntry<YamlConfig> entry = loader.from(file);
```

Получение имени и конфигурации:

```java
entry.name();
entry.config();
```

Собственный способ именования:

```java
FileNamer namer = file -> file.getName()
        .replace(".yml", "");

MappedConfigLoader<YamlConfig> loader =
        new SimpleMappedConfigLoader<>(
                new YamlConfigLoader(),
                namer
        );
```

---

## Custom Config

API не ограничивается YAML.

Основные abstraction'ы используют generics:

```java
Config
ConfigLoader<C>
ConfigManager<C>
MappedConfigLoader<C>
```

Это позволяет создавать собственные реализации `Config` и использовать с ними существующие manager/loader abstractions.

---

## Архитектура

```text
Config
 │
 ├── YamlConfig
 │
 └── custom implementations

ConfigLoader
 │
 └── YamlConfigLoader

ConfigManager
 │
 └── YamlConfigManager

MappedConfigLoader
 │
 └── SimpleMappedConfigLoader
```

---

## Сборка

Требуется Java 17+.

```bash
./gradlew build
```

Для Windows:

```powershell
.\gradlew.bat build
```

Готовый JAR:

```text
common/build/libs/
```

---

## Лицензия

FunctionalConfiguration распространяется под MIT — см. [LICENSE](LICENSE).
