package com.xg7network.xg7menus.API.Inventory.Items.Others;

import com.xg7network.xg7menus.API.Inventory.Items.InventoryItem;
import com.xg7network.xg7menus.API.Inventory.Items.ItemType;
import com.xg7network.xg7menus.API.Utils.Text.TextUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ActionInventoryItem extends InventoryItem {
    private ActionRunnable actionRunnable;
    protected long cooldown;
    protected long currentCooldown;
    protected String cooldownMessage;

    public ActionInventoryItem(ItemStack itemStack, int slot, ActionRunnable runnable, Action actionToUse) {
        super(itemStack, slot, null);
        this.actionRunnable = runnable;
        this.type = ItemType.ACTION;
    }

    public ActionInventoryItem(Material material, String name, List<String> lore, int amount, int slot, ActionRunnable runnable, Action actionToUse) {
        super(material, name, lore, amount, slot, null);
        this.actionRunnable = runnable;
        this.type = ItemType.ACTION;
    }

    public ActionInventoryItem(MaterialData materialData, String name, List<String> lore, int amount, int slot, ActionRunnable runnable, Action actionToUse) {
        super(materialData, name, lore, amount, slot, null);
        this.actionRunnable = runnable;
        this.type = ItemType.ACTION;
    }


    public void updateRunnable(ActionRunnable runnable) {
        this.actionRunnable = runnable;
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
    public void execute(Action action, Location location) {

        if (currentCooldown > System.currentTimeMillis()) {
            if (cooldownMessage != null) TextUtil.send(cooldownMessage.replace("SECONDS", (currentCooldown - System.currentTimeMillis()) / 1000 + ""), player);
            return;
        }

        if (cooldown != 0L) {
            this.currentCooldown = System.currentTimeMillis() + cooldown;
        }

        this.actionRunnable.run(action, location, player);
    }
    public Player getPlayer() {
        return player;
    }

    @FunctionalInterface
    public interface ActionRunnable {
        void run(@NotNull Action action, @Nullable Location location, @NotNull Player player);
    }
}
