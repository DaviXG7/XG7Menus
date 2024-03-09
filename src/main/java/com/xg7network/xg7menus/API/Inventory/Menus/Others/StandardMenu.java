package com.xg7network.xg7menus.API.Inventory.Menus.Others;

import com.xg7network.xg7menus.API.Inventory.MenuType;
import com.xg7network.xg7menus.API.Inventory.Menus.Menu;
import org.bukkit.entity.Player;

public class StandardMenu extends Menu {
    public StandardMenu(String title, int size) {
        super(MenuType.STANDARD, title, size);
    }

    public StandardMenu(String title, int size, Player player) {
        super(MenuType.STANDARD, title, size, player);
    }
}
