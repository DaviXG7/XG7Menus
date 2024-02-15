package com.xg7network.xg7menus.API.Inventory.InvAndItems.Menus;

import com.xg7network.xg7menus.API.Inventory.MenuType;
import com.xg7network.xg7menus.API.Inventory.SuperClasses.Menu;
import org.bukkit.entity.Player;

public class BasicMenu extends Menu {
    public BasicMenu(String title, int size) {
        super(MenuType.BASIC, title, size);
    }

    public BasicMenu(String title, int size, Player player) {
        super(MenuType.BASIC, title, size, player);
    }
}
