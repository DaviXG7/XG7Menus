package com.xg7network.xg7menus.API.Inventory.Menus.Others.Page;


import com.xg7network.xg7menus.API.Inventory.MenuType;
import com.xg7network.xg7menus.API.Inventory.Items.InventoryItem;
import com.xg7network.xg7menus.API.Inventory.Menus.Menu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Page extends Menu {

    private PagesMenu menu;
    private int index;

    public Page(String title, PagesMenu menu, int index) {
        super(MenuType.PAGE, title.replace("%page%", index + 1 + ""), 54);
        this.menu = menu;
        this.index = index;
    }

    public Page(String title, Player player, PagesMenu menu, int index) {
        super(MenuType.PAGE, title.replace("%page%", index + 1 + ""), 54, player);
        this.menu = menu;
        this.index = index;
    }

    public List<ItemStack> addListOfItems(List<ItemStack> items) {

        List<ItemStack> itemStackList = items;

        for (int i = 0; i < 45; i++) {
            if (!itemStackList.isEmpty()) {
                addItems(new InventoryItem(itemStackList.get(0), i, null));
                itemStackList.remove(0);
            } else break;

        }
        return itemStackList;

    }

    @Override
    public Menu addItems(InventoryItem... items) {
        for (InventoryItem item : items) {
            if (-1 < item.getSlot() && item.getSlot() <= 8) {
                item.setSlot(item.getSlot() + 45);
                this.inventory.setItem(item.getSlot(), item.getItemStack());
                this.items.add(item);
            } else {
                Bukkit.getLogger().severe("Items of page inventory only sopports slots 0 to 8 in the bottom of the page, the list of items will be on top.");
            }
        }
        return this;
    }

    public int getIndex() {
        return index;
    }

    public PagesMenu getPageMenu() {
        return menu;
    }
}
