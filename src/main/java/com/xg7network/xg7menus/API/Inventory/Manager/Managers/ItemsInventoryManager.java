package com.xg7network.xg7menus.API.Inventory.Manager.Managers;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.UUID;

public class ItemsInventoryManager {

    private static HashMap<UUID, Inventory> inventories = new HashMap<>();
    public static void register(UUID id, Inventory inventory) {
        inventories.put(id, inventory);
    }
    public static boolean contains(UUID uuid) {
        return inventories.containsKey(uuid);
    }
    public static Inventory getOldInventory(Player player) {
        return inventories.get(player.getUniqueId());
    }
    public static Inventory unregister(Player player) {
        return inventories.remove(player.getUniqueId());
    }
}
