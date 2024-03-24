package com.xg7network.xg7menus.API.Inventory.Manager;

import com.xg7network.xg7menus.API.Inventory.Menus.Others.PlayerSelector;
import com.xg7network.xg7menus.API.Inventory.Items.InventoryItem;
import com.xg7network.xg7menus.API.Inventory.Menus.Menu;
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
        Menu menu = MenuManager.getMenuByInventory(event.getClickedInventory());
        if (menu == null) return;
        event.setCancelled(true);

        InventoryItem inventoryItem = menu.getItem(event.getSlot());
        if (inventoryItem == null) return;


        Bukkit.getPluginManager().callEvent(new MenuClickEvent(menu, inventoryItem, MenuClickType.valueOf(event.getClick().name()), event.getRawSlot(), (Player) event.getWhoClicked()));

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Menu menu = MenuManager.getMenuByInventory(player.getInventory());
        if (menu == null) return;
        event.setCancelled(true);

        InventoryItem inventoryItem = menu.getItem(player.getInventory().getHeldItemSlot());
        if (inventoryItem == null) return;

        Bukkit.getPluginManager().callEvent(new MenuClickEvent(menu, inventoryItem, MenuClickType.valueOf(event.getAction().name()), event.getClickedBlock().getLocation(), player.getInventory().getHeldItemSlot(), event.getPlayer()));
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {

        Menu menu = MenuManager.getMenuByInventory(event.getInventory());

        if (menu == null) return;

        MenuManager.unregister(menu);

    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Menu menu = MenuManager.getMenuByInventory(player.getInventory());

        if (menu == null) return;
        if (((PlayerSelector) menu).isCancelEvents()) return;

        event.setCancelled(!((PlayerSelector) menu).canBreakBlocks());
        InventoryItem inventoryItem = menu.getItem(player.getInventory().getHeldItemSlot());
        event.setCancelled(!((PlayerSelector) menu).canBreakBlocks() || inventoryItem != null);
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Menu menu = MenuManager.getMenuByInventory(player.getInventory());

        if (menu == null) return;
        if (((PlayerSelector) menu).isCancelEvents()) return;

        InventoryItem inventoryItem = menu.getItem(player.getInventory().getHeldItemSlot());
        event.setCancelled(!((PlayerSelector) menu).canPlaceBlocks() || inventoryItem != null);
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        Menu menu = MenuManager.getMenuByInventory(player.getInventory());

        if (menu == null) return;
        if (((PlayerSelector) menu).isCancelEvents()) return;

        InventoryItem inventoryItem = menu.getItem(player.getInventory().getHeldItemSlot());
        event.setCancelled(!((PlayerSelector) menu).canDropItems() || inventoryItem != null);
    }

}
