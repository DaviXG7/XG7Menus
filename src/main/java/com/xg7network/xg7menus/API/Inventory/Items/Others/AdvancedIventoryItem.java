package com.xg7network.xg7menus.API.Inventory.Items.Others;

import com.xg7network.xg7menus.API.Inventory.Items.InventoryItem;
import com.xg7network.xg7menus.API.Inventory.Manager.MenuManager;
import com.xg7network.xg7menus.API.Inventory.Menus.Others.AdvancedMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdvancedIventoryItem extends InventoryItem {

    private AdvancedMenu menu;
    private AdvancedAction advancedAction;
    private List<ItemStack> itemsUpdating;
    private long ticks;

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

    public void setUpdatingItemConstantly(List<ItemStack> items, long ticks) {
        this.ticks = ticks;
        this.itemsUpdating = items;
    }

    public int inicialize() {

        if (itemsUpdating == null) return -1;

        return Bukkit.getScheduler().runTaskTimerAsynchronously(MenuManager.getJavaPlugin(), () -> {
            for (ItemStack itemStack1 : this.itemsUpdating) {
                this.menu.getInventory().getItem(this.slot).setItemMeta(itemStack1.getItemMeta());
                this.menu.getInventory().getItem(this.slot).setType(itemStack1.getType());
                this.menu.getInventory().getItem(this.slot).setData(itemStack1.getData());
                this.menu.getInventory().getItem(this.slot).setAmount(itemStack1.getAmount());
            }

        },0,this.ticks).getTaskId();

    }

    @FunctionalInterface
    public interface AdvancedAction {
        void execute(@NotNull ClickType type, @NotNull Player player);
    }



}
