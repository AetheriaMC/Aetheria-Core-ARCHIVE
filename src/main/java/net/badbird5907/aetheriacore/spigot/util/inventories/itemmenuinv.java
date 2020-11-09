package net.badbird5907.aetheriacore.spigot.util.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class itemmenuinv {
    public static Inventory inv1 = Bukkit.getServer().createInventory(null, 54, "Item Menu");

    public static void a(){
        ItemStack ref1 = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta metaref1 = ref1.getItemMeta();
        ArrayList<String> lore1= new ArrayList<String>();
        lore1.add("");
        metaref1.setDisplayName("§r§aCategory:§b Weapons");
        ref1.getItemMeta().addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        ref1.setItemMeta(metaref1);
        inv1.setItem(10, ref1);

        ItemStack ref2 = new ItemStack(Material.NETHERITE_CHESTPLATE);
        ItemMeta metaref2 = ref2.getItemMeta();
        ArrayList<String> lore2= new ArrayList<String>();
        lore2.add("");
        metaref2.setDisplayName("§r§aCategory:§b Armour");
        ref2.getItemMeta().addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        ref2.setItemMeta(metaref2);
        inv1.setItem(12, ref2);

        ItemStack ref3 = new ItemStack(Material.COBBLESTONE);
        ItemMeta metaref3 = ref1.getItemMeta();
        ArrayList<String> lore3= new ArrayList<String>();
        lore3.add("");
        metaref3.setDisplayName("§r§aCategory:§b Blocks");
        ref3.getItemMeta().addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        ref3.setItemMeta(metaref3);
        inv1.setItem(14, ref3);

        ItemStack ref4 = new ItemStack(Material.PIG_SPAWN_EGG);
        ItemMeta metaref4 = ref4.getItemMeta();
        ArrayList<String> lore4= new ArrayList<String>();
        lore4.add("");
        metaref4.setDisplayName("§r§aCategory:§b Spawn Eggs");
        ref4.getItemMeta().addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        ref4.setItemMeta(metaref4);
        inv1.setItem(16, ref4);

        ItemStack ref5 = new ItemStack(Material.NAME_TAG);
        ItemMeta metaref5 = ref4.getItemMeta();
        ArrayList<String> lore5= new ArrayList<String>();
        lore5.add("");
        metaref5.setDisplayName("§r§b§lSEARCH");
        ref5.getItemMeta().addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        ref5.setItemMeta(metaref5);
        inv1.setItem(31, ref5);
    }
}
