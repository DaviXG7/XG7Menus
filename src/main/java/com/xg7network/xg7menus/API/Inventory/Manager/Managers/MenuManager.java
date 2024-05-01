package com.xg7network.xg7menus.API.Inventory.Manager.Managers;
import com.xg7network.xg7menus.API.Inventory.Manager.Events.MenuListener;
import com.xg7network.xg7menus.API.Inventory.Menus.Menu;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class MenuManager {

    @Getter
    private static JavaPlugin javaPlugin;
    public static final boolean placeholderAPI = Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;
    private static HashMap<UUID, Menu> activeInventories = new HashMap<>();

    public static void register(Player player, Menu menu) {
        activeInventories.put(player.getUniqueId(), menu);
    }
    public static void unregister(Player player) {
        activeInventories.remove(player.getUniqueId());
    }
    public static void inicialize(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new MenuListener(), plugin);
        javaPlugin = plugin;
    }
    public static Menu getMenu(UUID uuid) {
        return activeInventories.get(uuid);
    }

}
