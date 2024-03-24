package com.xg7network.xg7menus;

import com.xg7network.xg7menus.API.Inventory.Manager.MenuClickEvent;
import com.xg7network.xg7menus.API.Inventory.Menus.Others.ItemsInventory.ItemsInventory;
import com.xg7network.xg7menus.API.Inventory.Menus.Others.ItemsInventory.ItemsInventoryManager;
import com.xg7network.xg7menus.API.Inventory.Menus.Others.Page.InventoryPages;
import com.xg7network.xg7menus.API.Inventory.Manager.MenuManager;
import com.xg7network.xg7menus.API.Inventory.Menus.Items.InventoryItem;
import com.xg7network.xg7menus.API.Inventory.Menus.Others.StandardMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class XG7Menus extends JavaPlugin implements Listener {
    Inventory inventory;

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
        if (event.getMenu().getId() == 1) {
            if (event.getSlot() == 0) {
                event.getMenu().updateItem(new InventoryItem(new ItemStack(Material.ACACIA_FENCE), 0));
            }
        }

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        ItemsInventory itemsInventory = new ItemsInventory();
        itemsInventory.addItems(new InventoryItem(new ItemStack(Material.ACACIA_FENCE), 1));
        inventory = itemsInventory.open(event.getPlayer(), "a", 9);

    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (ItemsInventoryManager.contains(event.getPlayer().getUniqueId())) {
            if (ItemsInventoryManager.getOldInventory((Player) event.getPlayer()).equals(inventory)) {
                inventory = ItemsInventoryManager.unregister((Player)event.getPlayer());
            }
        }
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {

        event.getPlayer().openInventory(inventory);

    }
}
