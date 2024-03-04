package com.xg7network.xg7menus;

import com.xg7network.xg7menus.API.Inventory.Items.Others.ActionInventoryItem;
import com.xg7network.xg7menus.API.Inventory.Items.Others.SkullInventoryItem;
import com.xg7network.xg7menus.API.Inventory.Menus.Others.PlayerSelector;
import com.xg7network.xg7menus.API.Inventory.Manager.MenuManager;
import com.xg7network.xg7menus.API.Inventory.Items.InventoryItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class XG7Menus extends JavaPlugin implements Listener {

    private static PlayerSelector selector;

    @Override
    public void onEnable() {

        MenuManager.inicialize(this);

        selector = new PlayerSelector();
        ActionInventoryItem actionInventoryItem = new ActionInventoryItem(Material.STICK, "Test2", new ArrayList<>(), 1, 1, (location, item) -> {
            if (location != null) {
                item.getPlayer().sendMessage(location.toString());
            }
        }, Action.RIGHT_CLICK_BLOCK);

        selector.addItems(
                new InventoryItem(Material.CHEST, "Test", new ArrayList<>(), 1, 0, () -> Bukkit.broadcastMessage("testeeeee")),
                actionInventoryItem

        );

        this.getServer().getPluginManager().registerEvents(this, this);

        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        selector.addItems(
                new SkullInventoryItem("teste3", new ArrayList<>(), 1, 2, null, event.getPlayer())
        );
        selector.open(event.getPlayer());
    }
    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        selector.removeItems(event.getPlayer());
    }
}
