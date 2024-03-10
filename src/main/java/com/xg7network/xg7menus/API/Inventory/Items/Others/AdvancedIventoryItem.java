package com.xg7network.xg7menus.API.Inventory.Items.Others;

import com.xg7network.xg7menus.API.Inventory.Items.InventoryItem;
import com.xg7network.xg7menus.API.Inventory.Menus.Others.AdvancedMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AdvancedIventoryItem extends InventoryItem {

    private AdvancedMenu menu;
    private boolean updatingItem = false;
    private AdvancedAction advancedAction;
    private ClickType clickType;

    public AdvancedIventoryItem(ItemStack itemStack, int slot, AdvancedAction advancedAction, AdvancedMenu menu) {
        super(itemStack, slot, null);
        this.menu = menu;
        this.advancedAction = advancedAction;

    }

    public AdvancedIventoryItem(Material material, String name, List<String> lore, int amount, int slot, AdvancedAction advancedAction, AdvancedMenu menu) {
        super(material, name, lore, amount, slot, null);
        this.menu = menu;
        this.advancedAction = advancedAction;
    }

    public AdvancedIventoryItem(MaterialData materialData, String name, List<String> lore, int amount, int slot, AdvancedAction advancedAction, AdvancedMenu menu) {
        super(materialData, name, lore, amount, slot, null);
        this.menu = menu;
        this.advancedAction = advancedAction;
    }

    public void execute(ClickType type) {

        if (this.advancedAction != null) advancedAction.execute(type, this.player);

    }

    public void setUpdatingItemConstantly(List<ItemStack> items, long mills) {

    }

    @FunctionalInterface
    private interface AdvancedAction {
        void execute(@NotNull ClickType type, @NotNull Player player);
    }



}
