package com.xg7network.xg7menus.API.Inventory.Menus.Others.Page;

import com.xg7network.xg7menus.API.Inventory.Items.InventoryItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PagesMenu {
    private List<Page> pages = new ArrayList<>();

    public PagesMenu(List<ItemStack> itemStacks, String title) {
        List<ItemStack> stackList = new ArrayList<>(itemStacks);
        int index = 0;
        while (!stackList.isEmpty()) {
            Page page = new Page(title, this, index);
            stackList = page.addListOfItems(stackList);
            index++;
            pages.add(page);
        }

        if (pages.isEmpty()) {
            pages.add(new Page(title, this, 0));
        }
    }

    public void addItems(InventoryItem... items) {
        for (Page page : pages) {
            page.addItems(items);
        }
    }

    public void goNext(Player player, Inventory inventory) {
        for (Page page : pages) {
            if (page.getInventory().equals(inventory)) {
                if (page.getIndex() + 1 < pages.size()) {
                    pages.get(page.getIndex() + 1).open(player);
                }
                return;
            }
        }
    }

    public void goBack(Player player, Inventory inventory) {
        for (Page page : pages) {
            if (page.getInventory().equals(inventory)) {
                if (page.getIndex() - 1 >= 0) {
                    pages.get(page.getIndex() - 1).open(player);
                }
                return;
            }
        }
    }

    public void open(Player player) {
        pages.get(0).open(player);
    }

    public List<Page> getPages() {
        return pages;
    }
}
