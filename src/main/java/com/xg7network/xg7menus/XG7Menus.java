package com.xg7network.xg7menus;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class XG7Menus extends JavaPlugin {

    public static boolean placeholderapi = false;

    @Override
    public void onEnable() {

        placeholderapi = Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;

        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
