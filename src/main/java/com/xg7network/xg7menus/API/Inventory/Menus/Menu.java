package com.xg7network.xg7menus.API.Inventory.Menus;

import com.xg7network.xg7menus.API.Inventory.Manager.MenuManager;
import com.xg7network.xg7menus.API.Inventory.MenuType;
import com.xg7network.xg7menus.API.Inventory.Items.InventoryItem;
import com.xg7network.xg7menus.API.Utils.Text.TextUtil;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class Menu {

    protected List<InventoryItem> items = new ArrayList<>();
    protected Inventory inventory;
    private MenuType type;

    public Menu(MenuType type, String title, int size) {
        this.type = type;
        this.inventory = Bukkit.createInventory(null, size, TextUtil.get(title));
    }
    public Menu(MenuType type, String title, int size, Player player) {
        this.type = type;
        this.inventory = Bukkit.createInventory(player, size, TextUtil.get(title, player));
    }

    public Inventory getInventory() {
        return inventory;
    }

    public List<InventoryItem> getItems() {
        return items;
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
        for (InventoryItem items : this.items) {
            if (items.getSlot() == item.getSlot()) {
                itemChose = items;
            }
        }
        
        if (itemChose == null) return this;

        this.inventory.setItem(item.getSlot(), item.getItemStack());
        this.items.remove(itemChose);
        this.items.add(item);
        
        return this;
    }

    public InventoryItem getItem(ItemStack itemStack) {
        if (itemStack == null || itemStack.getType() == Material.AIR) return null;
        for (InventoryItem item : items) {
            if (new NBTItem(itemStack).getString("xg7mid").equals(item.getId())) {
                return item;
            }
        }
        return null;
    }
    public InventoryItem getItem(int slot) {
        return items.stream().filter(item -> item.getSlot() == slot).findFirst().orElse(null);
    }

    public void open(Player player) {
        MenuManager.register(this);
        player.openInventory(inventory);
    }

    public MenuType getType() {
        return this.type;
    }

}
