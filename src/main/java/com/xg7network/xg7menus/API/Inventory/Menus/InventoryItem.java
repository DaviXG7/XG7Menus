package com.xg7network.xg7menus.API.Inventory.Menus;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.xg7network.xg7menus.API.Utils.Text.TextUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class InventoryItem {
    @Setter
    private int slot;
    protected ItemStack itemStack;
    private String id;

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

    public InventoryItem setId(String id) {
        this.id = id;
        return this;
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


    @Getter
    public static class SkullInventoryItem extends InventoryItem {
        private String value;
        private String owner;

        public SkullInventoryItem(String name, List<String> lore, int amount, int slot, String skinValue) {
            super(Arrays.asList(Material.values()).stream().map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD")
                            ? new MaterialData(Material.getMaterial("PLAYER_HEAD")) : new MaterialData(Material.getMaterial("SKULL_ITEM"), (byte) 3),
                    name, lore, amount, slot);
            setSkinValue(skinValue);
        }

        public SkullInventoryItem(String name, List<String> lore, int amount, int slot, UUID player) {
            super(Arrays.asList(Material.values()).stream().map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD")
                            ? new MaterialData(Material.getMaterial("PLAYER_HEAD")) : new MaterialData(Material.getMaterial("SKULL_ITEM"), (byte) 3),
                    name, lore, amount, slot);
            setSkin(player);

        }

        public SkullInventoryItem(String name, List<String> lore, int amount, int slot, Player owner) {
            super(Arrays.asList(Material.values()).stream().map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD")
                            ? new MaterialData(Material.getMaterial("PLAYER_HEAD")) : new MaterialData(Material.getMaterial("SKULL_ITEM"), (byte) 3),
                    name, lore, amount, slot);
            boolean skull = Arrays.asList(Material.values())
                    .stream()
                    .map(Material::name)
                    .collect(Collectors.toList())
                    .contains("PLAYER_HEAD");


            SkullMeta skullMeta = (SkullMeta) this.itemStack.getItemMeta();

            if (skull) skullMeta.setOwningPlayer(owner);
            else skullMeta.setOwner(owner.getName());


            this.itemStack.setItemMeta(skullMeta);

        }

        private void setSkin(UUID player) {
            try {
                HttpClient client = HttpClients.createDefault();
                String url = "https://sessionserver.mojang.com/session/minecraft/profile/" + player;
                HttpGet request = new HttpGet(url);
                HttpResponse response = client.execute(request);

                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                JsonObject profileData = new JsonParser().parse(result.toString()).getAsJsonObject();
                JsonObject properties = profileData.getAsJsonArray("properties").get(0).getAsJsonObject();


                GameProfile gameProfile = new GameProfile(UUID.randomUUID(), null);
                gameProfile.getProperties().put("textures", new Property("textures", properties.get("value").getAsString()));

                SkullMeta skullMeta = (SkullMeta) this.itemStack.getItemMeta();


                try {
                    Field profileField = skullMeta.getClass().getDeclaredField("profile");
                    profileField.setAccessible(true);
                    profileField.set(skullMeta, gameProfile);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                this.itemStack.setItemMeta(skullMeta);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void setSkinValue(String value) {

            GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "null");
            gameProfile.getProperties().put("textures", new Property("textures", value));

            SkullMeta skullMeta = (SkullMeta) this.itemStack.getItemMeta();

            try {
                Field profileField = skullMeta.getClass().getDeclaredField("profile");
                profileField.setAccessible(true);
                profileField.set(skullMeta, gameProfile);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

            this.itemStack.setItemMeta(skullMeta);

        }

        public void setOwner(String owner, boolean onlineMode) {
            ((SkullMeta) super.getItemStack().getItemMeta()).setOwner(owner);
            if (onlineMode) setSkin(Bukkit.getOfflinePlayer(owner).getUniqueId());
            this.owner = owner;

        }
    }
}
