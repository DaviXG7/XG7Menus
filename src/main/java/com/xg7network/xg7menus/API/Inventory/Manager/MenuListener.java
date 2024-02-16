package com.xg7network.xg7menus.API.Inventory.Manager;

import com.xg7network.xg7menus.API.Inventory.InvAndItems.Items.ActionInventoryItem;
import com.xg7network.xg7menus.API.Inventory.InvAndItems.Menus.PlayerSelector;
import com.xg7network.xg7menus.API.Inventory.SuperClasses.InventoryItem;
import com.xg7network.xg7menus.API.Inventory.SuperClasses.Menu;
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
        Menu menu = MenuManager.contains(event.getClickedInventory());
        if (menu == null) return;
        event.setCancelled(true);
        switch (menu.getType()) {
            case BASIC:
            case PAGE:

                InventoryItem inventoryItem = menu.getItem(event.getCurrentItem());
                if (inventoryItem == null) return;
                inventoryItem.execute();
                return;

            case PLAYERSELECTOR:
                if (event.getRawSlot() > 9) {
                    InventoryItem inventoryItem1 = menu.getItem(event.getCurrentItem());
                    if (inventoryItem1 == null) return;
                    inventoryItem1.execute();
                }
        }

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.LEFT_CLICK_AIR || event.getAction() != Action.LEFT_CLICK_BLOCK || event.getAction() != Action.RIGHT_CLICK_AIR || event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        Menu menu = MenuManager.contains(event.getPlayer().getInventory());
        if (menu == null) return;
        InventoryItem inventoryItem = menu.getItem(event.getPlayer().getItemInHand());
        if (inventoryItem == null) return;
        event.setCancelled(true);
        if (inventoryItem instanceof ActionInventoryItem) {
            ActionInventoryItem actionInventoryItem = (ActionInventoryItem) inventoryItem;
            if (event.getAction().equals(actionInventoryItem.getAction())) {
                actionInventoryItem.setPlayer(event.getPlayer());
                actionInventoryItem.setLocationClicked(event.getClickedBlock().getLocation());
                actionInventoryItem.execute();
                return;
            }
            if (actionInventoryItem.getSecundaryAction() != null) {
                if (event.getAction().equals(actionInventoryItem.getSecundaryAction())) {
                    actionInventoryItem.setPlayer(event.getPlayer());
                    actionInventoryItem.setLocationClicked(event.getClickedBlock().getLocation());
                    actionInventoryItem.execute();
                    return;
                }
            }
        }
        inventoryItem.execute();
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {

        Menu menu = MenuManager.contains(event.getInventory());
        if (menu == null) return;
        MenuManager.unregister(menu);

    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Menu menu = MenuManager.contains(player.getInventory());
        if (menu == null) return;
        event.setCancelled(!((PlayerSelector) menu).canBreakBlocks());
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Menu menu = MenuManager.contains(player.getInventory());
        if (menu == null) return;
        event.setCancelled(!((PlayerSelector) menu).canPlaceBlocks());
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        Menu menu = MenuManager.contains(player.getInventory());
        if (menu == null) return;
        event.setCancelled(!((PlayerSelector) menu).canDropItems());
    }

}
