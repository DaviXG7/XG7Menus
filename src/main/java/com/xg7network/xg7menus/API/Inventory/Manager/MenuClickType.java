package com.xg7network.xg7menus.API.Inventory.Manager;

import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;

public enum MenuClickType {

    LEFT,
    SHIFT_LEFT,
    RIGHT,
    SHIFT_RIGHT,
    WINDOW_BORDER_LEFT,
    WINDOW_BORDER_RIGHT,
    MIDDLE,
    NUMBER_KEY,
    DOUBLE_CLICK,
    DROP,
    CONTROL_DROP,
    CREATIVE,
    UNKNOWN,
    LEFT_CLICK_BLOCK,
    RIGHT_CLICK_BLOCK,
    LEFT_CLICK_AIR,
    RIGHT_CLICK_AIR,
    PHYSICAL;

    MenuClickType() {
    }

}
