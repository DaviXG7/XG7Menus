package com.xg7network.xg7menus.API.Inventory.InvAndItems.Items;

import com.xg7network.xg7menus.API.Inventory.SuperClasses.InventoryItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KitInventoryItem extends InventoryItem {
    private List<InventoryItem> items = new ArrayList<>();

    public KitInventoryItem(ItemStack iconItem, int slot, Runnable runnable) {
        super(iconItem, slot, runnable);
    }

    public KitInventoryItem(Material material, String name, List<String> lore, int amount, int slot, Runnable runnable) {
        super(material, name, lore, amount, slot, runnable);
    }

    public KitInventoryItem(MaterialData materialData, String name, List<String> lore, int amount, int slot, Runnable runnable) {
        super(materialData, name, lore, amount, slot, runnable);
    }

    public KitInventoryItem addItems(InventoryItem... items) {
        Collections.addAll(this.items, items);
        return this;
    }


}
