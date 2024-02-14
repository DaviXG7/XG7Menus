package com.xg7network.xg7menus.API.Inventory.Manager;

import com.xg7network.xg7menus.API.Inventory.SuperClasses.InventoryItem;
import com.xg7network.xg7menus.API.Inventory.SuperClasses.Menu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
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
                if (event.getRawSlot() < 10) {
                    InventoryItem inventoryItem1 = menu.getItem(event.getCurrentItem());
                    if (inventoryItem1 == null) return;
                    inventoryItem1.execute();
                }


        }

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Menu menu = MenuManager.contains(event.getPlayer().getInventory());
        if (menu == null) return;
        InventoryItem inventoryItem = menu.getItem(event.getPlayer().getItemInHand());
        if (inventoryItem == null) return;
        inventoryItem.execute();
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {

        Menu menu = MenuManager.contains(event.getInventory());
        if (menu == null) return;
        MenuManager.unregister(menu);


    }

}
