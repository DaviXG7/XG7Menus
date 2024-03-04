package com.xg7network.xg7menus.API.Inventory.Menus.Others.Page;

import com.xg7network.xg7menus.API.Inventory.Menus.Others.BasicMenu;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class InventoryPages {

    private HashMap<String, BasicMenu> menus = new HashMap<>();
    private BasicMenu inicialMenu;

    public InventoryPages(String initialMenuTitle, int initialMenuSize) {
        this.inicialMenu = new BasicMenu(initialMenuTitle, initialMenuSize);
    }

    public InventoryPages(String initialMenuTitle, int initialMenuSize, Player player) {
        this.inicialMenu = new BasicMenu(initialMenuTitle, initialMenuSize, player);
    }

    public InventoryPages addMenu(String name, BasicMenu menu) {
        menus.put(name, menu);
        return this;
    }

    public BasicMenu getMenuByName(String name) {
        return menus.get(name);
    }

    public void goTo(String name, Player player) {
        menus.get(name).open(player);
    }


}
