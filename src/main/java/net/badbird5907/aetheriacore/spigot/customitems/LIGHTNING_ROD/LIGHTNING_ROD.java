package net.badbird5907.aetheriacore.spigot.customitems.LIGHTNING_ROD;

import net.badbird5907.aetheriacore.spigot.util.itemtypes;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class LIGHTNING_ROD {
    public static void itemstack(){
        ItemStack i1 = new ItemStack(Material.BLAZE_ROD);
        ItemMeta i1meta = i1.getItemMeta();
        ArrayList<String> lore1 = new ArrayList<String>();
        lore1.add(ChatColor.GOLD + "Item Ability:" + ChatColor.YELLOW + " " + ChatColor.BOLD + "Strike!!");
        lore1.add(ChatColor.WHITE + "Aim this weapon at your opponent and right click\n");
        lore1.add(ChatColor.WHITE + "to strike them with lightning!");
        lore1.add("");
        lore1.add(ChatColor.DARK_GRAY + "Cooldown of X seconds (undecided)");
        lore1.add(ChatColor.DARK_GRAY + " aetheria:LIGHTNING_ROD");
        i1meta.setDisplayName(ChatColor.YELLOW + "Lightning Rod");
        i1meta.setLore(lore1);
        i1.setItemMeta(i1meta);
        i1.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 10);
        itemtypes.customitems.put("LIGHTNING_ROD", i1);
        itemtypes.allitems.add("LIGHTNING_ROD");
    }
    public static void strike(Player player){

    }
}
