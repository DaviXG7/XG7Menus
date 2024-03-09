package com.xg7network.xg7menus.API.Inventory.Items;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@FunctionalInterface
public interface ActionRunnable {
    void run(@Nullable Location location, @NotNull Player player);
}
