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
        for (int i = 0 ; i < player.getInventory().getSize(); i++) {
            if (player.getInventory().getItem(i) != null) {
                InventoryItem inventoryItem = this.getItem(player.getInventory().getItem(i));
                if (inventoryItem != null) player.getInventory().getItem(i).setType(Material.AIR);
            }

        }
        this.inventory = null;
        MenuManager.unregister(this);
    }
}
