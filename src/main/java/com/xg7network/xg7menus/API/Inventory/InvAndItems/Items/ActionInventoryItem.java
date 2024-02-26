package com.xg7network.xg7menus.API.Inventory.InvAndItems.Items;

import com.xg7network.xg7menus.API.Inventory.SuperClasses.ActionRunnable;
import com.xg7network.xg7menus.API.Inventory.SuperClasses.InventoryItem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import java.util.List;

public class ActionInventoryItem extends InventoryItem {
    private Action action;
    private Action secundaryAction;
    private ActionRunnable actionRunnable;
    public ActionInventoryItem(ItemStack itemStack, int slot, ActionRunnable runnable, Action actionToUse) {
        super(itemStack, slot, null);
        this.actionRunnable = runnable;
        this.action = actionToUse;
    }

    public ActionInventoryItem(Material material, String name, List<String> lore, int amount, int slot, ActionRunnable runnable, Action actionToUse) {
        super(material, name, lore, amount, slot, null);
        this.actionRunnable = runnable;
        this.action = actionToUse;
    }

    public ActionInventoryItem(MaterialData materialData, String name, List<String> lore, int amount, int slot, ActionRunnable runnable, Action actionToUse) {
        super(materialData, name, lore, amount, slot, null);
        this.actionRunnable = runnable;
        this.action = actionToUse;
    }

    public Action getAction() {
        return action;
    }

    public Action getSecundaryAction() {
        return secundaryAction;
    }
    public void setSecundaryAction(Action secundaryAction) {
        this.secundaryAction = secundaryAction;
    }

    public void execute(Location location) {
        this.actionRunnable.run(location, this);
    }
    public Player getPlayer() {
        return player;
    }
}
