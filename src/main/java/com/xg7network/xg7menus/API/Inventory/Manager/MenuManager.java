package com.xg7network.xg7menus.API.Inventory.Manager;

import com.xg7network.xg7menus.API.Inventory.SuperClasses.Menu;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class MenuManager {

    public static boolean placeholderapi = false;

    private static List<Menu> inventories = new ArrayList<>();

    public static void register(Menu... menus) {

        for (Menu menu : menus) {

            if (!inventories.contains(menu)) inventories.add(menu);

            System.out.println(inventories.size() + "size of inventories now");

        }

    }
    public static void unregister(Menu... menus) {

        for (Menu menu : menus) {

            if (inventories.contains(menu)) inventories.remove(menu);

            System.out.println(inventories.size() + "size of inventories now");

        }

    }
    public static void inicialize(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new MenuListener(), plugin);
        placeholderapi = Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;
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
