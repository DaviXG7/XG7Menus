package com.xg7plugins.extension.menus.player;

import org.bukkit.entity.Player;

public interface PlayerMenuMessages {

    default String getOnDropMessage(Player player) {
        return "";
    }
    default String getOnPickupMessage(Player player) {
        return "";
    }
    default String getOnBreakMessage(Player player) {
        return "";
    }
    default String getOnPlaceMessage(Player player) {
        return "";
    }
    default String getOnClickMessage(Player player) {
        return "";
    }
    default String getOnDragMessage(Player player) {
        return "";
    }
    default String getOnInteractMessage(Player player) {
        return "";
    }

}
