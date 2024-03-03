package com.xg7network.xg7menus.API.Inventory.InvAndItems.Items;

import com.xg7network.xg7menus.API.Inventory.SuperClasses.ActionRunnable;
import com.xg7network.xg7menus.API.Inventory.SuperClasses.InventoryItem;
import com.xg7network.xg7menus.API.Utils.Text.TextUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.w3c.dom.Text;

import java.util.List;

public class ActionInventoryItem extends InventoryItem {
    private Action action;
    private Action secundaryAction;
    private ActionRunnable actionRunnable;
    protected long cooldown;
    protected long currentCooldown;
    protected String cooldownMessage;

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

    public void updateRunnable(ActionRunnable runnable) {
        this.actionRunnable = runnable;
    }

    public Action getAction() {
        return action;
    }

    public ActionRunnable getActionRunnable() {
        return actionRunnable;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public long getCurrentCooldown() {
        return currentCooldown;
    }

    public void setCurrentCooldown(long currentCooldown) {
        this.currentCooldown = currentCooldown;
    }

    public void setCooldownMessage(String message) {
        this.cooldownMessage = message;
    }
    public Action getSecundaryAction() {
        return secundaryAction;
    }
    public void setSecundaryAction(Action secundaryAction) {
        this.secundaryAction = secundaryAction;
    }

    public void execute(Location location) {

        if (currentCooldown > System.currentTimeMillis()) {
            if (cooldownMessage != null) TextUtil.send(cooldownMessage.replace("SECONDS", (currentCooldown - System.currentTimeMillis()) / 1000 + ""), player);
            return;
        }

        if (cooldown != 0L) {
            this.currentCooldown = System.currentTimeMillis() + cooldown;
        }

        this.actionRunnable.run(location, this);
    }
    public Player getPlayer() {
        return player;
    }
}
