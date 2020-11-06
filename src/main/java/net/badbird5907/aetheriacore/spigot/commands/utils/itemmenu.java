package net.badbird5907.aetheriacore.spigot.commands.utils;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import net.badbird5907.aetheriacore.spigot.util.inventories.itemmenuinv;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class itemmenu implements CommandExecutor {
    itemmenuinv inv;
/*
    public itemmenu(itemmenuinv inv) {
        this.inv = inv;
    }
 */

    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String s,  String[] args) {
        /*
        for (Material material : Material.values()) {
            if(material.isBlock())
                itemtypes.blocks.add(material.name().toString());
        }
         */
        if(sender.hasPermission(permissionManager.item)){
            if(sender instanceof Player){
                /*
                ItemStack ref1 = new ItemStack(Material.DIAMOND_SWORD);
                ItemMeta metaref1 = ref1.getItemMeta();
                ArrayList<String> lore= new ArrayList<String>();

                lore.add(" ");
                lore.add("§for visit our site");
                lore.add(" ");
                lore.add("§atest.net/help");

                metaref1.setLore(lore);
                metaref1.setDisplayName("§6§lClick to get help");
                 */



                if(args.length == 0){
                    sender.sendMessage(ChatColor.GREEN + "Attempting To Open The GUI...");
                    itemmenuinv.a();
                    ((Player) sender).openInventory(itemmenuinv.inv1);
                    // itemmenuinv.openinv(player);
                }
            }
            else
                sender.sendMessage(ChatColor.RED + "ERROR: You need to be a player to execute this");

        }
        return true;
    }
}
