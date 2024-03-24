package com.xg7network.xg7menus.API.Inventory.Menus.Others;

import com.xg7network.xg7menus.API.Inventory.Menus.Items.InventoryItem;
import com.xg7network.xg7menus.API.Inventory.Menus.Menu;
import org.bukkit.entity.Player;

public class StandardMenu extends Menu {


    public StandardMenu(String title, int size, int id) {
        super(title, size, id);
    }

    public StandardMenu(String title, int size, Player player, int id) {
        super(title, size, player, id);
    }

    public void solidRectangle(InventoryCoordinate initialCoord, InventoryCoordinate finalCoord, InventoryItem item) {

        for (int y = initialCoord.getY() - 1; y < finalCoord.getX(); y++) for (int x = initialCoord.getX() - 1; x < finalCoord.getX(); x++) this.inventory.setItem(InventoryCoordinate.toSlot(x, y), item.getItemStack());
        this.items.add(item);
    }

    public void borderRectangle(InventoryCoordinate initialCoord, InventoryCoordinate finalCoord, InventoryItem item) {
        for (int x = initialCoord.getX() - 1; x < finalCoord.getX(); x++) {
            this.inventory.setItem(InventoryCoordinate.toSlot(x, initialCoord.getY()), item.getItemStack());
            this.inventory.setItem(InventoryCoordinate.toSlot(x, finalCoord.getY()), item.getItemStack());
        }
        for (int y = initialCoord.getY() - 2; y < finalCoord.getY() - 1;  y++) {
            this.inventory.setItem(InventoryCoordinate.toSlot(initialCoord.getX(), y), item.getItemStack());
            this.inventory.setItem(InventoryCoordinate.toSlot(finalCoord.getX(), y), item.getItemStack());
        }
        this.items.add(item);
    }

    public void setFillItem(InventoryItem item) {

        for (int i = 0; i < this.getInventory().getSize(); i++) {
            if (this.getInventory().getItem(i) == null) {
                this.inventory.setItem(i, item.getItemStack());
            }
        }

        this.items.add(item);
    }

    public static class InventoryCoordinate {
        private int x;
        private int y;

        public InventoryCoordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public static int toSlot(int x, int y) {
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
