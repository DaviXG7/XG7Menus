package com.xg7network.xg7menus.API.Inventory.Menus;

import com.xg7network.xg7menus.API.Inventory.Manager.MenuManager;
import com.xg7network.xg7menus.API.Utils.Text.TextUtil;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class Menu {

    protected List<InventoryItem> items = new ArrayList<>();
    protected Inventory inventory;
    private int id;

    public Menu(String title, int size, int id) {
        this.inventory = Bukkit.createInventory(null, size, TextUtil.get(title));
        this.id = id;
    }
    public Menu(String title, int size, Player player, int id) {
        this.inventory = Bukkit.createInventory(player, size, TextUtil.get(title, player));
        this.id = id;
    }

    public Menu addItems(InventoryItem... items) {
        for (InventoryItem item : items) {
            if (item.getSlot() < 0) continue;
            this.inventory.setItem(item.getSlot(), item.getItemStack());
            this.items.add(item);
        }
        return this;
    }


    public Menu updateItem(InventoryItem item) {
        InventoryItem itemChose = null;
        for (InventoryItem item1 : this.items) {
            if (item1.getSlot() == item1.getSlot()) {
                itemChose = item1;
                break;
            }
        }

        if (itemChose == null) return this;

        this.inventory.setItem(item.getSlot(), item.getItemStack());
        this.items.remove(itemChose);
        this.items.add(item);

        return this;
    }
    public InventoryItem getItemBySlot(int slot) {
        return items.stream().filter(item -> item.getSlot() == slot).findFirst().orElse(null);
    }
    public InventoryItem getItemById(int id) {
        return items.stream().filter(item -> item.getId() == id).findFirst().orElse(null);
    }

    public void open(Player player) {
        MenuManager.register(this);
        player.openInventory(inventory);
    }


}
