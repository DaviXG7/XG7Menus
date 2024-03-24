package com.xg7network.xg7menus.API.Inventory.Menus.Others;

import com.xg7network.xg7menus.API.Inventory.Manager.MenuManager;
import com.xg7network.xg7menus.API.Inventory.Items.InventoryItem;
import com.xg7network.xg7menus.API.Inventory.Menus.Menu;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class PlayerSelector extends Menu {

    private ItemStack[] playerItemsStack;
    private boolean breakBlocks = true;
    private boolean placeBlocks = true;
    private boolean dropItems = false;
    private boolean cancelEvents = false;

    public PlayerSelector(int id) {
        super("", 9, id);
        this.inventory = null;
    }

    @Override
    public PlayerSelector addItems(InventoryItem... items) {
        this.items.addAll(Arrays.asList(items));
        return this;
    }

    @Override
    public void open(@NotNull Player player) {

        this.playerItemsStack = player.getInventory().getContents();
        player.getInventory().clear();

        for (InventoryItem inventoryItem : this.items) {
            if (inventoryItem.getSlot() < 0) continue;
            player.getInventory().setItem(inventoryItem.getSlot(), inventoryItem.getItemStack());
        }

        this.inventory = player.getInventory();
        MenuManager.register(this);
    }

    public void giveBackItems(@NotNull Player player) {
        player.getInventory().setContents(playerItemsStack);
    }

    public void removeItems(@NotNull Player player) {
        player.getInventory().clear();
        this.inventory = null;
        MenuManager.unregister(this);
    }

    public boolean canBreakBlocks() {
        return breakBlocks;
    }

    public void setBreakBlocks(boolean breakBlocks) {
        this.breakBlocks = breakBlocks;
    }

    public boolean canPlaceBlocks() {
        return placeBlocks;
    }

    public void setPlaceBlocks(boolean placeBlocks) {
        this.placeBlocks = placeBlocks;
    }

    public boolean canDropItems() {
        return dropItems;
    }

    public void setDropItems(boolean dropItems) {
        this.dropItems = dropItems;
    }

    public boolean isCancelEvents() {
        return cancelEvents;
    }

    public void setCancelEvents(boolean cancelEvents) {
        this.cancelEvents = cancelEvents;
    }
}
