package com.xg7network.xg7menus.API.Inventory.Menus.Others.Page;

import com.xg7network.xg7menus.API.Inventory.Menus.Others.StandardMenu;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class InventoryPages {

    private HashMap<String, StandardMenu> menus = new HashMap<>();
    private StandardMenu inicialMenu;

    public InventoryPages(String initialMenuTitle, int initialMenuSize, int initialMenuId) {
        this.inicialMenu = new StandardMenu(initialMenuTitle, initialMenuSize, initialMenuId);
    }

    public InventoryPages(String initialMenuTitle, int initialMenuSize, Player player, int initialMenuId) {
        this.inicialMenu = new StandardMenu(initialMenuTitle, initialMenuSize, player, initialMenuId);
    }

    public InventoryPages addPage(String name, StandardMenu menu) {
        menus.put(name, menu);
        return this;
    }

    public StandardMenu getPageByName(String name) {
        return menus.get(name);
    }

    public void goTo(String name, Player player) {
        menus.get(name).open(player);
    }

    public StandardMenu getInicialMenu() {
        return inicialMenu;
    }
}
