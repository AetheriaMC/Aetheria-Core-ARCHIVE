package net.badbird5907.aetheriacore.spigot.commands.utils;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import net.badbird5907.aetheriacore.spigot.util.inventories.itemmenuinv;
import net.badbird5907.aetheriacore.spigot.util.itemtypes;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
                if(args.length == 0){
                    sender.sendMessage(ChatColor.GREEN + "Attempting To Open The GUI...");
                    inv.openInventory((Player) sender);
                }
            }
            else
                sender.sendMessage(ChatColor.RED + "ERROR: You need to be a player to execute this");

        }
        return true;
    }
}
