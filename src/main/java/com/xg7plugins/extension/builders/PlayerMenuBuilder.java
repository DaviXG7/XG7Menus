package com.xg7plugins.extension.builders;

import com.xg7plugins.boot.Plugin;
import com.xg7plugins.extension.MenuPermissions;
import com.xg7plugins.extension.events.MenuEvent;
import com.xg7plugins.modules.xg7menus.item.Item;
import com.xg7plugins.extension.menus.player.PlayerMenuMessages;
import com.xg7plugins.extension.menus.simple.SimplePlayerMenu;
import com.xg7plugins.utils.Builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class PlayerMenuBuilder<M extends SimplePlayerMenu> implements Builder<M> {

    private final Plugin plugin;
    private final String id;
    private List<Item> items = new ArrayList<>();
    private Consumer<MenuEvent> onOpen;
    private Consumer<MenuEvent> onClose;
    private Set<MenuPermissions> permissions;
    private boolean keepOldItems;
    private PlayerMenuMessages messages;

    public PlayerMenuBuilder(String id, Plugin plugin) {
        this.id = id;
        this.plugin = plugin;
        this.messages = new PlayerMenuMessages() {};
    }

    public PlayerMenuBuilder messages(PlayerMenuMessages messages) {
        this.messages = messages;
        return this;
    }

    public PlayerMenuBuilder items(List<Item> items) {
        this.items = items;
        return this;
    }
    public PlayerMenuBuilder items(Item... items) {
        this.items.addAll(Arrays.asList(items));
        return this;
    }

    public PlayerMenuBuilder addItem(Item item) {
        this.items.add(item);
        return this;
    }

    public PlayerMenuBuilder onOpen(Consumer<MenuEvent> onOpen) {
        this.onOpen = onOpen;
        return this;
    }

    public PlayerMenuBuilder onClose(Consumer<MenuEvent> onClose) {
        this.onClose = onClose;
        return this;
    }

    public PlayerMenuBuilder prevents(Set<MenuPermissions> permissions) {
        this.permissions = permissions;
        return this;
    }

    public PlayerMenuBuilder prevent(MenuPermissions permission) {
        this.permissions.add(permission);
        return this;
    }

    public PlayerMenuBuilder prevent(MenuPermissions... permissions) {
        this.permissions.addAll(Arrays.asList(permissions));
        return this;
    }

    public PlayerMenuBuilder keepOldItems(boolean keepOldItems) {
        this.keepOldItems = keepOldItems;
        return this;
    }

    public static PlayerMenuBuilder create(String id, Plugin plugin) {
        return new PlayerMenuBuilder(id, plugin);
    }

    @Override
    public M build(Object... args) {
        return (M) new SimplePlayerMenu(plugin, id, keepOldItems, items, onOpen, onClose, permissions,messages);
    }
}
