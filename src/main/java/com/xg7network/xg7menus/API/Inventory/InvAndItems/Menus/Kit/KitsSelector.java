package com.xg7network.xg7menus.API.Inventory.InvAndItems.Menus.Kit;

import com.xg7network.xg7menus.API.Inventory.MenuType;
import com.xg7network.xg7menus.API.Inventory.SuperClasses.Menu;
import org.bukkit.entity.Player;

public class KitsSelector extends Menu {
    private boolean selector = true;
    public KitsSelector(MenuType type, String title, int size) {
        super(type, title, size);
    }

    public KitsSelector(MenuType type, String title, int size, Player player) {
        super(type, title, size, player);
    }

    public boolean isSelector() {
        return selector;
    }

    public void setSelector(boolean selector) {
        this.selector = selector;
    }

    public void addKits(Kit... kits) {
        for (Kit kit : kits) {

        }
    }
}
