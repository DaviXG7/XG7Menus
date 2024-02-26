package com.xg7network.xg7menus.API.Inventory.SuperClasses;

import com.xg7network.xg7menus.API.Inventory.InvAndItems.Items.ActionInventoryItem;
import org.bukkit.Location;

@FunctionalInterface
public interface ActionRunnable {
    void run(Location location, ActionInventoryItem item);
}
