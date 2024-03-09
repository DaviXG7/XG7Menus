package com.xg7network.xg7menus.API.Inventory.Menus.Others;

import com.xg7network.xg7menus.API.Inventory.Items.InventoryItem;
import com.xg7network.xg7menus.API.Inventory.MenuType;
import com.xg7network.xg7menus.API.Inventory.Menus.Menu;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AdvancedMenu extends Menu {

    int[][] matrix;

    public AdvancedMenu(String title, int size) {
        super(MenuType.ADVANCED, title, size);
        this.matrix = new int[size / 9][9];
    }

    public AdvancedMenu(String title, int size, Player player) {
        super(MenuType.ADVANCED, title, size, player);
        this.matrix = new int[size / 9][9];
    }

    public void fillArea(InventoryCoordinate initialCoord, InventoryCoordinate finalCoord, ItemStack item) {

        for (int y = initialCoord.getY() - 1; y < finalCoord.getX(); y++)
            for (int x = initialCoord.getX() - 1; x < finalCoord.getX(); x++)
                this.inventory.setItem(InventoryCoordinate.toSlot(x, y), item);

    }

    static class InventoryCoordinate {
        private int x;
        private int y;

        public InventoryCoordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        protected int toSlot() {
            return (x * y - (9 - x)) - 1;
        }
        protected static int toSlot(int x, int y) {
            return (x * y - (9 - x)) - 1;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    enum BorderStyle {

    }


}
