package com.xg7network.xg7menus.API.Inventory.Menus.Others;


import com.xg7network.xg7menus.API.Inventory.Manager.Managers.MenuManager;
import com.xg7network.xg7menus.API.Inventory.Menus.InventoryItem;
import com.xg7network.xg7menus.API.Inventory.Menus.Menu;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

@Getter
@Setter
public class PlayerSelector extends Menu {

    private boolean breakBlocksEnabled, placeBlocksEnabled, dropItemsEnabled;

    public PlayerSelector(String id) {
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

        player.getInventory().clear();

        for (InventoryItem inventoryItem : this.items) {
            if (inventoryItem.getSlot() < 0) continue;
            player.getInventory().setItem(inventoryItem.getSlot(), inventoryItem.getItemStack());
        }

        this.inventory = player.getInventory();
        MenuManager.register(player, this);
    }

    public void removeItems(@NotNull Player player) {
        player.getInventory().clear();
        this.inventory = null;
        MenuManager.unregister(player);
    }
}
