package io.github.thegatesdev.bungle.event;

import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;
import org.jetbrains.annotations.*;

public final class BungleReplenishEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final Player player;
    private final ItemStack usedItem;
    private final ItemStack extraItem;
    private int amount;
    private boolean cancelled;

    public BungleReplenishEvent(Player player, ItemStack usedItem, ItemStack extraItem, int amount) {
        this.player = player;
        this.usedItem = usedItem;
        this.extraItem = extraItem;
        this.amount = amount;
    }


    public Player getPlayer() {
        return player;
    }

    public ItemStack getUsedItem() {
        return usedItem;
    }

    public ItemStack getExtraItem() {
        return extraItem;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public static @NotNull HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
