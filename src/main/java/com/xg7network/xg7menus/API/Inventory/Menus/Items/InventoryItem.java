package com.xg7network.xg7menus.API.Inventory.Menus.Items;

import com.xg7network.xg7menus.API.Inventory.Menus.Menu;
import com.xg7network.xg7menus.API.Utils.Text.TextUtil;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryItem {
    private int slot;
    protected ItemStack itemStack;
    private int id;

    // Constructor
    public InventoryItem(ItemStack itemStack, int slot) {
        this.itemStack = itemStack;
        this.slot = slot;
    }
    public InventoryItem(Material material, String name, List<String> lore, int amount, int slot) {
        ItemStack itemStack = new ItemStack(material, amount);
        this.slot = slot;
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(TextUtil.get(name));
        meta.setLore(lore.stream().map(TextUtil::get).collect(Collectors.toList()));
        itemStack.setItemMeta(meta);
        this.itemStack = itemStack;
    }

    public InventoryItem(MaterialData materialData, String name, List<String> lore, int amount, int slot) {
        ItemStack itemStack = materialData.toItemStack(amount);
        this.slot = slot;
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(TextUtil.get(name));
        meta.setLore(lore.stream().map(TextUtil::get).collect(Collectors.toList()));
        itemStack.setItemMeta(meta);
        this.itemStack = itemStack;
    }
    public InventoryItem addEnchant(Enchantment enchantment, int level) {
        ItemMeta meta = this.itemStack.getItemMeta();
        meta.addEnchant(enchantment, level, true);
        this.itemStack.setItemMeta(meta);
        return this;
    }

    public int getId() {
        return id;
    }

    public InventoryItem setId(int id) {
        this.id = id;
        return this;
    }

    public int getSlot() {
        return slot;
    }
    public void setSlot(int slot) {
        this.slot = slot;
    }
    public void updateStack(InventoryItem item, Menu menu) {
        this.itemStack = item.itemStack;
        menu.updateItem(this);
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void update(Material newMaterial, String newName, List<String> newLore, Menu menu) {
        this.itemStack.setType(newMaterial);

        ItemMeta meta = this.itemStack.getItemMeta();
        meta.setDisplayName(TextUtil.get(newName));
        meta.setLore(newLore.stream().map(TextUtil::get).collect(Collectors.toList()));
        this.itemStack.setItemMeta(meta);
        menu.updateItem(this);
    }
    public void updateName(String newName, Menu menu) {
        ItemMeta meta = this.itemStack.getItemMeta();
        meta.setDisplayName(TextUtil.get(newName));
        this.itemStack.setItemMeta(meta);

        menu.updateItem(this);
    }
    public void updateMaterial(Material newMaterial, Menu menu) {
        this.itemStack.setType(newMaterial);
        menu.updateItem(this);
    }
    public void updateMaterial(MaterialData newMaterial, Menu menu) {
        this.itemStack.setData(newMaterial);
        menu.updateItem(this);
    }
    public void updateLore(List<String> newLore, Menu menu) {
        ItemMeta meta = this.itemStack.getItemMeta();
        meta.setLore(newLore.stream().map(TextUtil::get).collect(Collectors.toList()));
        this.itemStack.setItemMeta(meta);
        menu.updateItem(this);
    }
    public void updateNameAndLore(String newName, List<String> newLore, Menu menu) {

        ItemMeta meta = this.itemStack.getItemMeta();
        meta.setDisplayName(TextUtil.get(newName));
        meta.setLore(newLore.stream().map(TextUtil::get).collect(Collectors.toList()));
        this.itemStack.setItemMeta(meta);
        menu.updateItem(this);
    }

    // For placeholders!!
    public InventoryItem setPlaceholders(Player player) {
        ItemMeta meta = this.itemStack.getItemMeta();
        if (meta.getDisplayName() != null) meta.setDisplayName(TextUtil.get(meta.getDisplayName(), player));
        if (meta.getLore() != null)  meta.setLore(meta.getLore().stream().map(l -> TextUtil.get(l, player)).collect(Collectors.toList()));
        this.itemStack.setItemMeta(meta);
        return this;
    }

    public static ItemStack getFormattedItem(Material material, String name, List<String> lore, int amount) {
        return new InventoryItem(material, name, lore, amount, 0).getItemStack();
    }
    public static ItemStack getFormattedItem(MaterialData material, String name, List<String> lore, int amount) {
        return new InventoryItem(material, name, lore, amount, 0).getItemStack();
    }

}
