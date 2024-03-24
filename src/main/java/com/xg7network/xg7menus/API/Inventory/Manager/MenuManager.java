package com.xg7network.xg7menus.API.Inventory.Manager;

import com.xg7network.xg7menus.API.Inventory.Menus.Menu;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuManager {

    public static boolean placeholderapi = false;

    private static JavaPlugin javaPlugin;
    private static List<Menu> inventories = new ArrayList<>();
    private static List<Integer> itemsUpdatingTask = new ArrayList<>();

    public static void register(Menu... menus) {

        Arrays.stream(menus).filter(menu -> !inventories.contains(menu)).forEachOrdered(menu -> inventories.add(menu));

    }
    public static void unregister(Menu... menus) {

        Arrays.stream(menus).filter(menu -> inventories.contains(menu)).forEachOrdered(menu -> inventories.remove(menu));

    }
    public static void inicialize(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new MenuListener(), plugin);
        placeholderapi = Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;
        javaPlugin = plugin;
    }

    public static Menu getMenuByInventory(Inventory inventory) {
        return inventories.stream().filter(menu -> menu.getInventory().equals(inventory)).findFirst().orElse(null);
    }

    public static JavaPlugin getJavaPlugin() {
        return javaPlugin;
    }
}
