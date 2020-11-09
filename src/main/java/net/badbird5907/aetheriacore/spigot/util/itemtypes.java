package net.badbird5907.aetheriacore.spigot.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class itemtypes {
    public static List<String> blocks = new ArrayList<>();
    public static List<String> allitems = new ArrayList<>();
    public static List<String> items = new ArrayList<>();
    public static List<Material> blacklisted_items = new ArrayList<>();
    public static HashMap<String, ItemStack> customitems = new HashMap<>();

    public static void additems(){


        ItemStack i2 = new ItemStack(Material.IRON_SWORD);
        ItemMeta i2meta = i2.getItemMeta();
        ArrayList<String> lore2 = new ArrayList<String>();
        lore2.add("Item Ability: Throw");
        lore2.add("Right click to throw this at mobs!");
        i2meta.setDisplayName(ChatColor.YELLOW + "Dagger");
        i2.setLore(lore2);
        i2.setItemMeta(i2meta);
        customitems.put("DAGGER", i2);
        allitems.add("DAGGER");
    }
    
}
