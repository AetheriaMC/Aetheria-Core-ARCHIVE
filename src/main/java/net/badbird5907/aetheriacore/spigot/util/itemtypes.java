package net.badbird5907.aetheriacore.spigot.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class itemtypes {
    public static List<String> blocks = new ArrayList<>();
    public static List<String> allitems = new ArrayList<>();
    public static List<String> items = new ArrayList<>();

    public static HashMap<String, ItemStack> customitems = new HashMap<>();

    public static void additems(){
        ItemStack i1 = new ItemStack(Material.STICK);
        ItemMeta i1meta = i1.getItemMeta();
        ArrayList<String> lore1 = new ArrayList<>();
        lore1.add("Item Ability: Strike!!!");
        lore1.add("Aim this weapon at your opponent and right click to strke them with lightning!");
        i1meta.setDisplayName("Lightning Rod");
        i1.setItemMeta(i1meta);
        customitems.put("LIGHTNING_ROD", i1);
        allitems.add("LIGHTNING_ROD");

        ItemStack i2 = new ItemStack(Material.IRON_SWORD);
        ItemMeta i2meta = i1.getItemMeta();
        ArrayList<String> lore2 = new ArrayList<>();
        lore2.add("Item Ability: Throw");
        lore2.add("Right click to throw this at mobs!");
        i2meta.setDisplayName("Dagger");
        i2.setItemMeta(i2meta);
        customitems.put("DAGGER", i2);
        allitems.add("DAGGER");
    }
    
}
