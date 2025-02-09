package com.xg7plugins.extension.menus.player;

import com.xg7plugins.boot.Plugin;
import com.xg7plugins.extension.events.MenuEvent;

public abstract class ConfirmationPlayerMenu extends PlayerMenu {

    protected ConfirmationPlayerMenu(Plugin plugin, String id, PlayerMenuMessages messages, boolean storeOldItems) {
        super(plugin, id, messages, storeOldItems);
    }

    public abstract <T extends MenuEvent> void confirm(T event);
    public abstract <T extends MenuEvent> void cancel(T event);

}
