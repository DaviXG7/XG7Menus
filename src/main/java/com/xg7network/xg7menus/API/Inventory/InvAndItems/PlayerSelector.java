package com.xg7network.xg7menus.API.Inventory.InvAndItems;

import com.xg7network.xg7menus.API.Inventory.Manager.MenuManager;
import com.xg7network.xg7menus.API.Inventory.MenuType;
import com.xg7network.xg7menus.API.Inventory.SuperClasses.InventoryItem;
import com.xg7network.xg7menus.API.Inventory.SuperClasses.Menu;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class PlayerSelector extends Menu {

    private ItemStack[] playerItemsStack;
    private boolean breakBlocks = true;
    private boolean placeBlocks = true;
    private boolean dropItems = false;

    public PlayerSelector() {
        super(MenuType.PLAYERSELECTOR, "", 9);
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
}
