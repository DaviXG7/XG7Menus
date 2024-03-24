package com.xg7network.xg7menus;

import com.xg7network.xg7menus.API.Inventory.Items.Others.SkullInventoryItem;
import com.xg7network.xg7menus.API.Inventory.Manager.MenuClickEvent;
import com.xg7network.xg7menus.API.Inventory.Menus.Others.Page.InventoryPages;
import com.xg7network.xg7menus.API.Inventory.Menus.Others.Page.PagesMenu;
import com.xg7network.xg7menus.API.Inventory.Menus.Others.PlayerSelector;
import com.xg7network.xg7menus.API.Inventory.Manager.MenuManager;
import com.xg7network.xg7menus.API.Inventory.Items.InventoryItem;
import com.xg7network.xg7menus.API.Inventory.Menus.Others.StandardMenu;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class XG7Menus extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {

        MenuManager.inicialize(this);
        this.getServer().getPluginManager().registerEvents(this, this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onMenuClick(MenuClickEvent event) {
        if (event.getMenu().getId() == 0) {
            if (event.getSlot() == 0) {
                event.getPlayer().sendMessage(event.getLocation().toString());
            }
        }

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        PlayerSelector menu = new PlayerSelector(0);
        menu.addItems(new InventoryItem(new ItemStack(Material.PAPER), 0));
        menu.open(event.getPlayer());

    }
    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {

    }
}
