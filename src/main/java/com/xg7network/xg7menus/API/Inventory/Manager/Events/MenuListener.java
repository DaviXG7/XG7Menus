package com.xg7network.xg7menus.API.Inventory.Manager.Events;

import com.xg7network.xg7menus.API.Inventory.Manager.Managers.MenuManager;
import com.xg7network.xg7menus.API.Inventory.Menus.InventoryItem;
import com.xg7network.xg7menus.API.Inventory.Menus.Menu;
import com.xg7network.xg7menus.API.Inventory.Menus.Others.PlayerSelector;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class MenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Menu menu = MenuManager.getMenu(event.getWhoClicked().getUniqueId());
        if (menu == null) return;
        event.setCancelled(true);

        InventoryItem inventoryItem = menu.getItemBySlot(event.getSlot());
        if (inventoryItem == null) return;


        Bukkit.getPluginManager().callEvent(new MenuClickEvent(menu, inventoryItem, MenuClickType.valueOf(event.getClick().name()), null, event.getRawSlot(), (Player) event.getWhoClicked()));

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Menu menu = MenuManager.getMenu(player.getUniqueId());
        if (menu == null) return;
        event.setCancelled(true);

        InventoryItem inventoryItem = menu.getItemBySlot(player.getInventory().getHeldItemSlot());
        if (inventoryItem == null) return;

        Bukkit.getPluginManager().callEvent(new MenuClickEvent(menu, inventoryItem, MenuClickType.valueOf(event.getAction().name()), event.getClickedBlock().getLocation(), player.getInventory().getHeldItemSlot(), event.getPlayer()));
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {

        if (MenuManager.getMenu(event.getPlayer().getUniqueId())== null) return;

        MenuManager.unregister((Player) event.getPlayer());

    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Menu menu = MenuManager.getMenu(player.getUniqueId());

        if (menu == null) return;

        InventoryItem inventoryItem = menu.getItemBySlot(player.getInventory().getHeldItemSlot());
        event.setCancelled(!((PlayerSelector) menu).isBreakBlocksEnabled() || inventoryItem != null);
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Menu menu = MenuManager.getMenu(player.getUniqueId());
        if (menu == null) return;

        InventoryItem inventoryItem = menu.getItemBySlot(player.getInventory().getHeldItemSlot());
        event.setCancelled(!((PlayerSelector) menu).isPlaceBlocksEnabled() || inventoryItem != null);
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        Menu menu = MenuManager.getMenu(player.getUniqueId());
        if (menu == null) return;
        InventoryItem inventoryItem = menu.getItemBySlot(player.getInventory().getHeldItemSlot());
        event.setCancelled(!((PlayerSelector) menu).isDropItemsEnabled() || inventoryItem != null);
    }

}
