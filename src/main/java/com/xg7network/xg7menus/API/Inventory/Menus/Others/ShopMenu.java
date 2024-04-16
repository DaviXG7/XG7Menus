package com.xg7network.xg7menus.API.Inventory.Menus.Others;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class ShopMenu extends StandardMenu {
    private HashMap<Integer, List<Object>> requeriments = new HashMap<>();
    public ShopMenu(String title, int size, int id) {
        super(title, size, id);
    }
    public ShopMenu(String title, int size, Player player, int id) {
        super(title, size, player, id);
    }

    public void addRequeriments(int slot, List<Object> requeriments) {
        this.requeriments.put(slot, requeriments);
    }
    public List<Object> getRequeriments(int slot) {
        return requeriments.get(slot);
    }
    public void removeRequeriments(int slot) {
        requeriments.remove(slot);
    }
}
