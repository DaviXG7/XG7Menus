package com.xg7network.xg7menus.API.Inventory.Menus.Others;

import com.xg7network.xg7menus.API.Inventory.Items.Others.AdvancedIventoryItem;
import com.xg7network.xg7menus.API.Inventory.Manager.MenuManager;
import com.xg7network.xg7menus.API.Inventory.MenuType;
import com.xg7network.xg7menus.API.Inventory.Menus.Menu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AdvancedMenu extends Menu {

    private List<Integer> tasks = new ArrayList<>();

    public AdvancedMenu(String title, int size) {
        super(MenuType.ADVANCED, title, size);
    }

    public AdvancedMenu(String title, int size, Player player) {
        super(MenuType.ADVANCED, title, size, player);
    }

    public void fillArea(InventoryCoordinate initialCoord, InventoryCoordinate finalCoord, ItemStack item) {

        for (int y = initialCoord.getY() - 1; y < finalCoord.getX(); y++) for (int x = initialCoord.getX() - 1; x < finalCoord.getX(); x++) this.inventory.setItem(InventoryCoordinate.toSlot(x, y), item);

    }

    public void retangle(InventoryCoordinate initialCoord, InventoryCoordinate finalCoord, ItemStack item) {
        for (int x = initialCoord.getX() - 1; x < finalCoord.getX(); x++) {
            this.inventory.setItem(InventoryCoordinate.toSlot(x, initialCoord.getY()), item);
            this.inventory.setItem(InventoryCoordinate.toSlot(x, finalCoord.getY()), item);
        }
        for (int y = initialCoord.getY() - 2; y < finalCoord.getY() - 1;  y++) {
            this.inventory.setItem(InventoryCoordinate.toSlot(initialCoord.getX(), y), item);
            this.inventory.setItem(InventoryCoordinate.toSlot(finalCoord.getX(), y), item);
        }
    }

    public void open(Player player) {
        MenuManager.register(this);
        player.openInventory(inventory);
        this.items.forEach(item -> {
            tasks.add(((AdvancedIventoryItem)item).inicialize());
        });
    }

    public void teminate() {
        tasks.forEach(task -> Bukkit.getScheduler().cancelTask(task));
    }

    public static class InventoryCoordinate {
        private int x;
        private int y;

        public InventoryCoordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
        protected static int toSlot(int x, int y) {
            return 9 * y - (9 - x) - 1;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }


}
