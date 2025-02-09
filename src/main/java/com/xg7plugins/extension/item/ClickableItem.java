package com.xg7plugins.extension.item;

import com.xg7plugins.extension.events.ClickEvent;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

@Getter
public class ClickableItem extends Item {

    private Consumer<ClickEvent> onClick;

    public ClickableItem(ItemStack itemStack, int slot) {
        super(itemStack);
        this.slot(slot);
    }

    public ClickableItem onClick(Consumer<ClickEvent> onClick) {
        this.onClick = onClick;
        return this;
    }


}
