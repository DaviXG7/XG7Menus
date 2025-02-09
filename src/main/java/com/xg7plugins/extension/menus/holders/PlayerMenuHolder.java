package com.xg7plugins.extension.menus.holders;

import com.xg7plugins.boot.Plugin;
import com.xg7plugins.extension.menus.BaseMenu;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PlayerMenuHolder extends MenuHolder {

    private final Player player;

    public PlayerMenuHolder(String id, Plugin plugin, BaseMenu menu, Player player) {
        super(id, plugin, null,9,null, menu, player);
        this.player = player;
    }

    @Override
    public Inventory getInventory() {
        return player.getInventory();
    }
}
