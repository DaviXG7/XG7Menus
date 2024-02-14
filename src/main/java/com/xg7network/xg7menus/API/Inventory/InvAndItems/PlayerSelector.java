package com.xg7network.xg7menus.API.Inventory.InvAndItems;

import com.xg7network.xg7menus.API.Inventory.MenuType;
import com.xg7network.xg7menus.API.Inventory.SuperClasses.InventoryItem;
import com.xg7network.xg7menus.API.Inventory.SuperClasses.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
public class PlayerSelector extends Menu {

    public PlayerSelector() {
        super(MenuType.PLAYERSELECTOR, "", 9);
        this.inventory = null;
    }

    @Override
    public void open(Player player) {
        for (InventoryItem inventoryItem : this.items) {
            player.getInventory().setItem(inventoryItem.getSlot(), inventoryItem.getItemStack());
        }
    }

    public void removeItems(Player player) {
        for (int i = 0 ; i < player.getInventory().getSize(); i++) {
            if (player.getInventory().getItem(i) != null) {
                InventoryItem inventoryItem = this.getItem(player.getInventory().getItem(i));
                if (inventoryItem != null) player.getInventory().getItem(i).setType(Material.AIR);
            }

        }
    }
}
