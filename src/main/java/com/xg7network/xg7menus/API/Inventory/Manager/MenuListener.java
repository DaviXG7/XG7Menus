package com.xg7network.xg7menus.API.Inventory.Manager;

import com.xg7network.xg7menus.API.Inventory.Items.Others.ActionInventoryItem;
import com.xg7network.xg7menus.API.Inventory.Menus.Others.PlayerSelector;
import com.xg7network.xg7menus.API.Inventory.Items.InventoryItem;
import com.xg7network.xg7menus.API.Inventory.Menus.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
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
        switch (menu.getType()) {
            case BASIC:
            case PAGE:
            case PLAYERSELECTOR:

                InventoryItem inventoryItem = menu.getItem(event.getCurrentItem());
                if (inventoryItem == null) return;
                inventoryItem.execute();
        }

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.LEFT_CLICK_AIR && event.getAction() != Action.LEFT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        Menu menu = MenuManager.getMenuByInventory(event.getPlayer().getInventory());
        if (menu == null) return;
        InventoryItem inventoryItem = menu.getItem(event.getPlayer().getItemInHand());
        if (inventoryItem == null) return;
        if (inventoryItem instanceof ActionInventoryItem) {
            ActionInventoryItem actionInventoryItem = (ActionInventoryItem) inventoryItem;
            if (event.getAction().equals(actionInventoryItem.getAction())) {
                actionInventoryItem.setPlayer(event.getPlayer());
                actionInventoryItem.execute(event.getClickedBlock() != null ? event.getClickedBlock().getLocation() : null);
            }
            if (actionInventoryItem.getSecundaryAction() != null) {
                if (event.getAction().equals(actionInventoryItem.getSecundaryAction())) {
                    actionInventoryItem.setPlayer(event.getPlayer());
                    actionInventoryItem.execute(event.getClickedBlock() != null ? event.getClickedBlock().getLocation() : null);
                }
            }
            return;
        }
        inventoryItem.execute();
        event.setCancelled(true);
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
        InventoryItem inventoryItem = menu.getItem(event.getPlayer().getItemInHand());
        event.setCancelled(!((PlayerSelector) menu).canBreakBlocks() || inventoryItem != null);
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Menu menu = MenuManager.getMenuByInventory(player.getInventory());
        if (menu == null) return;
        if (((PlayerSelector) menu).isCancelEvents()) return;
        InventoryItem inventoryItem = menu.getItem(event.getPlayer().getItemInHand());
        event.setCancelled(!((PlayerSelector) menu).canPlaceBlocks() || inventoryItem != null);
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        Menu menu = MenuManager.getMenuByInventory(player.getInventory());
        if (menu == null) return;
        if (((PlayerSelector) menu).isCancelEvents()) return;
        InventoryItem inventoryItem = menu.getItem(event.getPlayer().getItemInHand());
        event.setCancelled(!((PlayerSelector) menu).canDropItems() || inventoryItem != null);
    }

}
