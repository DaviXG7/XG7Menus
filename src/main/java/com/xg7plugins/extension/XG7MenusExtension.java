package com.xg7plugins.extension;

import com.xg7plugins.XG7Plugins;
import com.xg7plugins.boot.Plugin;
import com.xg7plugins.events.Listener;
import com.xg7plugins.extension.menus.BaseMenu;
import com.xg7plugins.extension.menus.holders.PlayerMenuHolder;
import com.xg7plugins.extension.menus.player.PlayerMenu;
import com.xg7plugins.extensions.Extension;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class XG7Menus implements Extension {

    @Getter
    private static XG7Menus instance;

    private final HashMap<UUID, PlayerMenuHolder> playerMenusMap = new HashMap<>();
    private final HashMap<String, BaseMenu> registeredMenus = new HashMap<>();


    @Override
    public void onInit() {

        instance = this;

        getPlugin().getDebug().loading("XG7Menus initialized");

    }

    public List<Listener> loadListeners() {
        return Collections.emptyList();
    }

    @Override
    public void onDisable() {
        playerMenusMap.forEach((id, menu) -> {
            Player player = Bukkit.getPlayer(id);
            ((PlayerMenu) menu.getMenu()).close(player);
        });
    }

    @Override
    public Plugin getPlugin() {
        return XG7Plugins.getInstance();
    }

    @Override
    public String getName() {
        return "XG7Menus";
    }

    public void registerMenus(BaseMenu... menus) {
        if (menus == null) return;
        for (BaseMenu menu : menus) {
            registeredMenus.put(menu.getPlugin().getName() + ":" + menu.getId(), menu);
        }
    }
    public <T extends BaseMenu> T getMenu(Plugin plugin, String id) {
        return (T) registeredMenus.get(plugin.getName() + ":" + id);
    }

    public void registerPlayerMenuHolder(UUID playerId, PlayerMenuHolder holder) {
        playerMenusMap.put(playerId, holder);
    }

    public void removePlayerMenuHolder(UUID playerId) {
        playerMenusMap.remove(playerId);
    }
    public <T extends PlayerMenuHolder> T getPlayerMenuHolder(UUID playerId) {
        return (T) playerMenusMap.get(playerId);
    }

    public boolean hasPlayerMenuHolder(UUID playerId) {
        return playerMenusMap.containsKey(playerId);
    }

}