package io.github.thegatesdev.bungle.config;

import org.slf4j.*;
import org.spongepowered.configurate.*;
import org.spongepowered.configurate.hocon.*;

import java.nio.file.*;
import java.util.concurrent.*;

public final class ConfigLoader {

    private static final Logger log = LoggerFactory.getLogger(ConfigLoader.class);
    private final HoconConfigurationLoader loader;
    private CompletableFuture<BungleConfig> activeConfig = CompletableFuture.completedFuture(new BungleConfig());

    public ConfigLoader(Path configPath) {
        this.loader = HoconConfigurationLoader.builder().path(configPath).build();
    }


    public CompletableFuture<BungleConfig> activeConfig() {
        return activeConfig;
    }

    public void load() {
        activeConfig = CompletableFuture.supplyAsync(this::loadNow);
    }

    public void save() throws ConfigurateException {
        final var newNode = loader.createNode(value -> value.set(BungleConfig.class, activeConfig));
        loader.save(newNode);
    }


    private BungleConfig loadNow() {
        try {
            return loader.load().get(BungleConfig.class);
        } catch (ConfigurateException e) {
            throw new RuntimeException(e);
        }
    }
}
