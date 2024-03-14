package com.xg7network.xg7menus;

import com.xg7network.xg7menus.API.Inventory.Items.Others.ActionInventoryItem;
import com.xg7network.xg7menus.API.Inventory.Items.Others.SkullInventoryItem;
import com.xg7network.xg7menus.API.Inventory.Menus.Others.Page.InventoryPages;
import com.xg7network.xg7menus.API.Inventory.Menus.Others.Page.PagesMenu;
import com.xg7network.xg7menus.API.Inventory.Menus.Others.PlayerSelector;
import com.xg7network.xg7menus.API.Inventory.Manager.MenuManager;
import com.xg7network.xg7menus.API.Inventory.Items.InventoryItem;
import com.xg7network.xg7menus.API.Inventory.Menus.Others.StandardMenu;
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

        StandardMenu menu = new StandardMenu("Titulo", 54);
        menu.addItems(new InventoryItem(new ItemStack(Material.PAPER), 0, () -> {
            //faz oq vc quiser
        }
        ));

        List<ItemStack> itemParaPaginas = new ArrayList<>();

        itemParaPaginas.add(new ItemStack(Material.PAPER));

        PagesMenu pagesMenu = new PagesMenu(itemParaPaginas, "Titulo do inventario, placeholder %page%");

        //hotbar nos slots 44 ao 53
        pagesMenu.addItems(/*item1, item2*/);

        //São páginas de inventário, tipo uma lista de menus
        InventoryPages inventoryPages = new InventoryPages("Titulo", 54);

        //O menu inicial se pega com getInitialMenu
        inventoryPages.getPageByName("name");


        //Seletor do inventario do jogador, com items de ações
        PlayerSelector selector1 = new PlayerSelector();



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
