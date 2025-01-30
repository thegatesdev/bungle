package io.github.thegatesdev.bungle;

import org.bukkit.plugin.java.*;

public final class Bungle extends JavaPlugin {

    private final Listener listener = new Listener();

    @Override
    public void onEnable() {
        registerEvents();
    }


    private void registerEvents() {
        getServer().getPluginManager().registerEvents(listener, this);
    }
}
