package com.xg7plugins.extension.menus.gui;

import com.xg7plugins.boot.Plugin;
import com.xg7plugins.extension.menus.BaseMenu;
import com.xg7plugins.extension.menus.holders.MenuHolder;
import com.xg7plugins.utils.text.Text;
import com.xg7plugins.utils.text.TextCentralizer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

public abstract class Menu extends BaseMenu {

    protected final InventoryType type;
    protected final String title;
    protected final int size;

    public Menu(Plugin plugin, String id, String title, InventoryType type) {
        super(plugin, id);
        this.type = type;
        this.size = 0;
        this.title = title;
    }
    public Menu(Plugin plugin, String id, String title, int size) {
        super(plugin, id);
        this.type = null;
        this.size = size;
        this.title = title;
    }

    @Override
    public void open(Player player) {
        player.closeInventory();
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            MenuHolder holder = new MenuHolder(id, plugin, Text.detectLangs(player, plugin,title).join().verifyCentralized(TextCentralizer.PixelsSize.INV).getText(), size, type, this, player);
            player.openInventory(holder.getInventory());
            putItems(player, holder);
        }, 1L);
    }
}
