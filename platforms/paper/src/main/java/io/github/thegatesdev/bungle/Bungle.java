package io.github.thegatesdev.bungle;

import io.github.thegatesdev.bungle.config.*;
import io.github.thegatesdev.bungle.listener.*;
import net.kyori.adventure.text.*;
import net.kyori.adventure.text.logger.slf4j.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;
import org.bukkit.plugin.java.*;

public final class Bungle extends JavaPlugin {

    private final ComponentLogger logger = getComponentLogger();
    private final ConfigLoader configLoader = new ConfigLoader(getDataPath().resolve("plugin.conf"));
    private final ReplenishListener replenishListener = new ReplenishListener();


    @Override
    public void onLoad() {
        configLoader.load();
    }

    @Override
    public void onEnable() {
        configLoader.activeConfig().thenAccept(config -> {
            applyConfig(config);
            listen(replenishListener);
        }).exceptionally(throwable -> {
            logger.error(Component.text(throwable.toString()));
            logger.warn(Component.text("The plugin will now be disabled!"));
            getServer().getPluginManager().disablePlugin(this);
            return null;
        }).thenRun(configLoader::save).exceptionally(throwable -> {
            logger.error(Component.text(throwable.toString()));
            return null;
        });
    }

    public void reload() {
        configLoader.load();
        configLoader.activeConfig().thenAccept(this::applyConfig).exceptionally(throwable -> {
            logger.error(Component.text(throwable.toString()));
            return null;
        });
    }

    private void applyConfig(BungleConfig config) {
        replenishListener.setConfig(config.replenish);
    }


    private void listen(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }

    public static boolean isArrow(ItemStack itemStack) {
        return switch (itemStack.getType()) {
            case ARROW, SPECTRAL_ARROW, TIPPED_ARROW -> true;
            default -> false;
        };
    }
}
