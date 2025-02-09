package com.xg7plugins.extension.menus;

import com.xg7plugins.extension.menus.gui.Menu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;

@AllArgsConstructor
@Getter
public class MenuNavigation {

    protected final HashMap<String, Menu> menus;

    public void openMenu(String id, Player player) {
        menus.get(id).open(player);
    }
    public Menu getMenu(String id) {
        return menus.get(id);
    }
}
