package com.xg7network.xg7menus.API.Inventory.InvAndItems.Menus.Kit;

import com.xg7network.xg7menus.API.Inventory.SuperClasses.InventoryItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import java.util.List;

public class Kit extends InventoryItem {

    public Kit(ItemStack itemStack, int slot, Runnable runnable) {
        super(itemStack, slot, runnable);
    }

    public Kit(Material material, String name, List<String> lore, int amount, int slot, Runnable runnable) {
        super(material, name, lore, amount, slot, runnable);
    }

    public Kit(MaterialData materialData, String name, List<String> lore, int amount, int slot, Runnable runnable) {
        super(materialData, name, lore, amount, slot, runnable);
    }
}
