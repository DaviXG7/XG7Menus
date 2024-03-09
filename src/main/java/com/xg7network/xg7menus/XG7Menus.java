package com.xg7network.xg7menus;

import com.xg7network.xg7menus.API.Inventory.Items.Others.ActionInventoryItem;
import com.xg7network.xg7menus.API.Inventory.Items.Others.SkullInventoryItem;
import com.xg7network.xg7menus.API.Inventory.Menus.Others.ItemsInventory;
import com.xg7network.xg7menus.API.Inventory.Menus.Others.Page.Page;
import com.xg7network.xg7menus.API.Inventory.Menus.Others.Page.PagesMenu;
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
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class XG7Menus extends JavaPlugin implements Listener {

    private static PlayerSelector selector;

    @Override
    public void onEnable() {

        MenuManager.inicialize(this);

        selector = new PlayerSelector();

        List<ItemStack> itemStacks = new ArrayList<>();
        itemStacks.add(new ItemStack(Material.PAPER));
        itemStacks.add(new ItemStack(Material.PAPER));
        itemStacks.add(new ItemStack(Material.PAPER));
        itemStacks.add(new ItemStack(Material.PAPER));


        PagesMenu menu = new PagesMenu(itemStacks, "a");

        menu.addItem(new InventoryItem(Material.PAPER, "a", new ArrayList<>(), 1, 0, null));
        menu.addItem(new InventoryItem(Material.PAPER, "a", new ArrayList<>(), 1, 8, null));

        ActionInventoryItem actionInventoryItem = new ActionInventoryItem(Material.STICK, "Test2", new ArrayList<>(), 1, 1, (location, player) -> {
            if (location != null) {
                player.sendMessage(location.toString());
                menu.open(player);
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
