package com.xg7network.xg7menus.API.Inventory.SuperClasses;

import com.xg7network.xg7menus.API.Inventory.InvAndItems.Items.ActionInventoryItem;
import org.bukkit.Location;

import javax.annotation.Nullable;

@FunctionalInterface
public interface ActionRunnable {
    void run(@Nullable Location location, ActionInventoryItem item);
}
