package com.xg7network.xg7menus.API.Inventory.Manager;

import com.xg7network.xg7menus.API.Inventory.SuperClasses.InventoryItem;
import com.xg7network.xg7menus.API.Inventory.SuperClasses.Menu;
import org.bukkit.World;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.w3c.dom.css.CSSStyleSheet;

import java.util.*;

public class MenuManager {

    private static List<Menu> inventories = new ArrayList<>();

    public static void register(Menu... menus) {

        for (Menu menu : menus) {

            if (!inventories.contains(menu)) inventories.add(menu);

        }

    }
    public static void inicialize(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new MenuListener(), plugin);
    }

    protected static Menu contains(Inventory inventory) {
        for (Menu menu : inventories) {
            if (menu.getInventory().equals(inventory)) {
                return menu;
            }
        }
        return null;
    }
}
