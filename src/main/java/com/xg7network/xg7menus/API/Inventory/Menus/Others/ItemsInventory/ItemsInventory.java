package com.xg7network.xg7menus.API.Inventory.Menus.Others.ItemsInventory;

import com.xg7network.xg7menus.API.Inventory.Items.InventoryItem;
import com.xg7network.xg7menus.API.Utils.Text.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ItemsInventory {

    private List<InventoryItem> items = new ArrayList<>();

    public ItemsInventory() {}
    public ItemsInventory(List<InventoryItem> items) {
        this.items = items;
    }

    public ItemsInventory addItems(InventoryItem... items) {
        this.items.addAll(Arrays.asList(items));
        return this;
    }

    public Inventory open(Player player, String title, int size) {

        Inventory inventory = Bukkit.createInventory(player, size, TextUtil.get(title, player));

        for (InventoryItem item : this.items) {
            inventory.setItem(item.getSlot(), item.getItemStack());
        }

        ItemsInventoryManager.register(player.getUniqueId(), inventory);

        player.openInventory(inventory);

        return inventory;
    }

    public List<InventoryItem> getItems() {
        return this.items;
    }

    public static ItemsInventory fromInventory(Inventory inventory) {

        ItemsInventory itemsInventory = new ItemsInventory();
        IntStream.range(0, inventory.getSize()).filter(i -> inventory.getItem(i) != null).mapToObj(i -> new InventoryItem(inventory.getItem(i), i)).forEachOrdered(itemsInventory::addItems);
        return itemsInventory;
    }


}
