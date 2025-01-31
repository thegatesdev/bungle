package io.github.thegatesdev.bungle;

import io.github.thegatesdev.bungle.listener.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;
import org.bukkit.plugin.java.*;

public final class Bungle extends JavaPlugin {

    private final ListenerReplenish listenerReplenish = new ListenerReplenish();


    @Override
    public void onEnable() {
        listen(listenerReplenish);
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
