package com.xg7network.xg7menus.API.Inventory.InvAndItems;

import com.xg7network.xg7menus.API.Inventory.SuperClasses.InventoryItem;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import java.util.List;

public class ActionInventoryItem extends InventoryItem {
    private Action action;
    public ActionInventoryItem(ItemStack itemStack, int slot, Runnable runnable, Action actionToUse) {
        super(itemStack, slot, runnable);
        this.action = actionToUse;
    }

    public ActionInventoryItem(Material material, String name, List<String> lore, int amount, int slot, Runnable runnable, Action actionToUse) {
        super(material, name, lore, amount, slot, runnable);
        this.action = actionToUse;
    }

    public ActionInventoryItem(MaterialData materialData, String name, List<String> lore, int amount, int slot, Runnable runnable, Action actionToUse) {
        super(materialData, name, lore, amount, slot, runnable);
        this.action = actionToUse;
    }

    public Action getAction() {
        return action;
    }
}
