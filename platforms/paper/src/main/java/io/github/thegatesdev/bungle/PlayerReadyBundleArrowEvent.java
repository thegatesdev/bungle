package io.github.thegatesdev.bungle;

import com.destroystokyo.paper.event.player.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;
import org.jetbrains.annotations.*;

public final class PlayerReadyBundleArrowEvent extends PlayerReadyArrowEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final ItemStack bundle;

    public PlayerReadyBundleArrowEvent(Player player, ItemStack bow, ItemStack arrow, ItemStack bundle) {
        super(player, bow, arrow);
        this.bundle = bundle;
    }

    public ItemStack bundle() {
        return bundle;
    }


    public static @NotNull HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
