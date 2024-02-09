package com.xg7network.xg7menus.API.Inventory.InvAndItems;

import com.xg7network.xg7menus.API.Inventory.MenuType;
import com.xg7network.xg7menus.API.Inventory.SuperClasses.Menu;
import org.bukkit.entity.Player;

//Coming soon

public class SelectorMenu extends Menu {
    public SelectorMenu(MenuType type, String title, int size) {
        super(type, title, size);
    }

    public SelectorMenu(MenuType type, String title, int size, Player player) {
        super(type, title, size, player);
    }
}
