package com.xg7network.xg7menus;

import com.xg7network.xg7menus.API.Inventory.Manager.Managers.ItemsInventoryManager;
import com.xg7network.xg7menus.API.Inventory.Manager.Managers.MenuManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.Inventory;
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
    public void onJoin(PlayerJoinEvent event) {
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
