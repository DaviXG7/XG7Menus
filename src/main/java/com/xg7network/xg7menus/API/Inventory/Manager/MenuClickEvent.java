package com.xg7network.xg7menus.API.Inventory.Manager;

import com.xg7network.xg7menus.API.Inventory.Menus.Items.InventoryItem;
import com.xg7network.xg7menus.API.Inventory.Menus.Menu;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class MenuClickEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();

    private Menu menu;
    private InventoryItem inventoryItem;
    private MenuClickType type;
    private Location location;
    private int slot;

    public MenuClickEvent(Menu menu, InventoryItem inventoryItem, MenuClickType type, Location location, int slot, Player player) {
        super(player);
        this.menu = menu;
        this.inventoryItem = inventoryItem;
        this.type = type;
        this.location = location;
        this.slot = slot;
    }

    public MenuClickEvent(Menu menu, InventoryItem inventoryItem, MenuClickType type, int slot, Player player) {
        super(player);
        this.menu = menu;
        this.inventoryItem = inventoryItem;
        this.type = type;
        this.slot = slot;
    }

    public Menu getMenu() {
        return menu;
    }

    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }

    public MenuClickType getType() {
        return type;
    }

    public Location getLocation() {
        return location;
    }

    public int getSlot() {
        return slot;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
