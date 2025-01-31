package io.github.thegatesdev.bungle.listener;

import io.github.thegatesdev.bungle.event.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.inventory.*;

import java.util.*;

public final class ListenerReplenish implements Listener {

    public static final char REPLENISH_THRESHOLD = 50;


    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void handleBlockPlace(BlockPlaceEvent event) {
        if (!event.canBuild()) return;
        ItemStack usedItem = event.getItemInHand();
        if (usedItem.getAmount() > REPLENISH_THRESHOLD) return;
        Player player = event.getPlayer();
        PlayerInventory inventory = player.getInventory();
        ListIterator<ItemStack> contents = inventory.iterator();

        while (contents.hasNext()) {
            ItemStack itemContent = contents.next();
            if (itemContent == null) continue;
            if (usedItem.getType() != itemContent.getType()) continue;
            if (itemContent.equals(usedItem)) continue;
            if (!usedItem.isSimilar(itemContent)) continue;

            int usedItemAmount = usedItem.getAmount();
            int availableAmount = itemContent.getAmount();
            int transfer = Math.min(usedItem.getMaxStackSize() - usedItemAmount + 1, availableAmount);

            var replenishEvent = new BungleReplenishEvent(player, usedItem, itemContent, transfer);
            if (!replenishEvent.callEvent()) continue;
            transfer = replenishEvent.getAmount();

            usedItem.setAmount(usedItemAmount + transfer - 1);
            itemContent.setAmount(availableAmount - transfer);
            inventory.setItem(event.getHand(), usedItem);
            contents.set(itemContent);
            break;
        }
    }
}
