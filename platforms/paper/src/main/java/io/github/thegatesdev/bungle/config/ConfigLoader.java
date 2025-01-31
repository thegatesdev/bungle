package io.github.thegatesdev.bungle.config;

import org.slf4j.*;
import org.spongepowered.configurate.*;
import org.spongepowered.configurate.hocon.*;

import java.io.*;
import java.nio.file.*;
import java.util.concurrent.*;

public final class ConfigLoader {

    private static final Logger log = LoggerFactory.getLogger(ConfigLoader.class);
    private final HoconConfigurationLoader loader;
    private final Path configPath;
    private CompletableFuture<BungleConfig> activeConfig = CompletableFuture.completedFuture(new BungleConfig());

    public ConfigLoader(Path configPath) {
        this.loader = HoconConfigurationLoader.builder().path(configPath).build();
        this.configPath = configPath;
    }


    public CompletableFuture<BungleConfig> activeConfig() {
        return activeConfig;
    }

    public void load() {
        activeConfig = CompletableFuture.supplyAsync(this::loadNow);
    }

    public void save() {
        try {
            var node = loader.createNode(value -> value.set(BungleConfig.class, activeConfig.get()));
            loader.save(node);
        } catch (Exception e) {
            throw new RuntimeException("Could not save config file", e);
        }
    }


    private BungleConfig loadNow() {
        if (Files.notExists(configPath)) {
            try {
                Files.createDirectories(configPath.getParent());
                Files.createFile(configPath);
            } catch (IOException e) {
                throw new RuntimeException("Could not create config file", e);
            }
        }
        try {
            return loader.load().get(BungleConfig.class);
        } catch (ConfigurateException e) {
            throw new RuntimeException("Could not load bungle config", e);
        }
    }
}
