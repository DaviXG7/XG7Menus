package com.xg7network.xg7menus.API.Inventory.Items;

import com.xg7network.xg7menus.API.Inventory.Menus.Menu;
import com.xg7network.xg7menus.API.Utils.Text.TextUtil;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class InventoryItem {

    protected String id;
    protected Player player;
    protected Menu inventory;
    protected Runnable runnable;
    protected int slot;
    protected ItemStack itemStack;

    public InventoryItem(ItemStack itemStack, int slot, Runnable runnable) {

        this.itemStack = itemStack;
        this.slot = slot;
        this.runnable = runnable;
        this.id = UUID.randomUUID().toString();

        NBTItem item = new NBTItem(this.itemStack);
        item.setString("xg7mid", this.id);
        this.itemStack = item.getItem();


    }

    public InventoryItem(Material material, String name, List<String> lore, int amount, int slot, Runnable runnable) {

        ItemStack itemStack = new ItemStack(material, amount);
        this.slot = slot;
        this.runnable = runnable;
        this.id = UUID.randomUUID().toString();

        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(TextUtil.get(name));
        meta.setLore(lore.stream().map(TextUtil::get).collect(Collectors.toList()));
        itemStack.setItemMeta(meta);

        NBTItem item = new NBTItem(itemStack);
        item.setString("xg7mid", this.id);
        this.itemStack = item.getItem();


    }

    public InventoryItem(MaterialData materialData, String name, List<String> lore, int amount, int slot, Runnable runnable) {

        ItemStack itemStack = materialData.toItemStack(amount);
        this.slot = slot;
        this.runnable = runnable;
        this.id = UUID.randomUUID().toString();

        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(TextUtil.get(name));
        meta.setLore(lore.stream().map(TextUtil::get).collect(Collectors.toList()));
        itemStack.setItemMeta(meta);

        NBTItem item = new NBTItem(itemStack);
        item.setString("xg7mid", this.id);
        this.itemStack = item.getItem();


    }

    public InventoryItem addEnchant(Enchantment enchantment, int level) {
        ItemMeta meta = this.itemStack.getItemMeta();
        meta.addEnchant(enchantment, level, true);
        this.itemStack.setItemMeta(meta);
        return this;
    }
    public String getId() {
        return id;
    }

    public Menu getInventory() {
        return inventory;
    }

    public int getSlot() {
        return slot;
    }
    public void setSlot(int slot) {
        this.slot = slot;
    }

    public void execute() {
        if (this.runnable != null) this.runnable.run();
    }
    public void update(InventoryItem item) {
        this.itemStack = item.itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void update(Material newMaterial, String newName, List<String> newLore, Runnable newRunable, Menu menu) {
        this.itemStack.setType(newMaterial);
        this.runnable = newRunable;

        ItemMeta meta = this.itemStack.getItemMeta();
        meta.setDisplayName(TextUtil.get(newName));
        meta.setLore(newLore.stream().map(TextUtil::get).collect(Collectors.toList()));
        this.itemStack.setItemMeta(meta);

        menu.updateItem(this);
    }
    public void updateRunnable(Runnable newRunnable) {
        this.runnable = newRunnable;
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
    public void setRunnable(Runnable newRunable) {
        this.runnable = newRunable;
    }
    public void updateNameAndLore(String newName, List<String> newLore, Menu menu) {

        ItemMeta meta = this.itemStack.getItemMeta();
        meta.setDisplayName(TextUtil.get(newName));
        meta.setLore(newLore.stream().map(TextUtil::get).collect(Collectors.toList()));
        this.itemStack.setItemMeta(meta);
        menu.updateItem(this);
    }

    // For placeholders!!
    public InventoryItem setPlayer(Player player) {
        this.player = player;
        ItemMeta meta = this.itemStack.getItemMeta();
        if (meta.getDisplayName() != null) meta.setDisplayName(TextUtil.get(meta.getDisplayName(), player));
        if (meta.getLore() != null)  meta.setLore(meta.getLore().stream().map(l -> TextUtil.get(l, player)).collect(Collectors.toList()));
        this.itemStack.setItemMeta(meta);
        return this;
    }

    public static ItemStack getFormattedItem(Material material, String name, List<String> lore, int amount) {
        return new InventoryItem(material, name, lore, amount, 0, null).getItemStack();
    }
    public static ItemStack getFormattedItem(MaterialData material, String name, List<String> lore, int amount) {
        return new InventoryItem(material, name, lore, amount, 0, null).getItemStack();
    }

}