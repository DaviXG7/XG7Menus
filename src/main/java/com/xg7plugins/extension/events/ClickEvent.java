package com.xg7plugins.extension.events;

import com.xg7plugins.modules.xg7menus.item.Item;
import com.xg7plugins.extension.menus.holders.MenuHolder;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.HumanEntity;


@Getter
public class ClickEvent extends MenuEvent {

    private final int clickedSlot;
    private final int clickedRawSlot;
    private final Item clickedItem;

    public ClickEvent(HumanEntity whoClicked, ClickAction clickAction, MenuHolder menu, int clickedSlot, int clickedRawSlot, Item clickedItem, Location locationClicked) {
        super(whoClicked, clickAction, menu, locationClicked);
        this.clickedSlot = clickedSlot;
        this.clickedItem = clickedItem;
        this.clickedRawSlot = clickedRawSlot;
    }
}
