package io.github.thegatesdev.bungle;

import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.*;

public final class Listener implements org.bukkit.event.Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
    public void handlePlayerInteract(PlayerInteractEvent event) {
        // Cannot make bundle a valid arrow source
        // Cannot take arrows out of bundle because we can't know if an arrow is already found at this point
        // Can replenish existing arrow stacks after firing ;]
    }

    private static boolean shouldUseArrow(Player player, ItemStack bow, ItemStack arrow, ItemStack bundle) {
        var event = new PlayerReadyBundleArrowEvent(player, bow, arrow, bundle);
        event.callEvent();
        return !event.isCancelled();
    }

    private static boolean isArrow(ItemStack itemStack) {
        return switch (itemStack.getType()) {
            case ARROW, SPECTRAL_ARROW, TIPPED_ARROW -> true;
            default -> false;
        };
    }
}
