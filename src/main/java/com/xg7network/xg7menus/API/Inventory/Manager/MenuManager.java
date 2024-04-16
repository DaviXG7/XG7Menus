package com.xg7network.xg7menus.API.Inventory.Manager;
import com.xg7network.xg7menus.API.Inventory.Manager.Events.MenuListener;
import com.xg7network.xg7menus.API.Inventory.Menus.Menu;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuManager {

    @Getter
    private static JavaPlugin javaPlugin;
    public static final boolean placeholderAPI = Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;
    private static List<Menu> activeInventories = new ArrayList<>();

    public static void register(Menu... menus) {
        Arrays.stream(menus).filter(menu -> !activeInventories.contains(menu)).forEachOrdered(menu -> activeInventories.add(menu));
    }
    public static void unregister(Menu... menus) {
        Arrays.stream(menus).filter(menu -> activeInventories.contains(menu)).forEachOrdered(menu -> activeInventories.remove(menu));
    }
    public static void inicialize(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new MenuListener(), plugin);
        javaPlugin = plugin;
    }

    public static Menu getMenuByInventory(Inventory inventory) {
        return activeInventories.stream().filter(menu -> menu.getInventory().equals(inventory)).findFirst().orElse(null);
    }

}
