package net.badbird5907.aetheriacore.spigot.commands.utils;

import net.badbird5907.aetheriacore.spigot.manager.Permission;
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
    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String s,  String[] args) {
        if(Bukkit.getPluginManager().isPluginEnabled("AetheriaItems")){
            if(sender instanceof Player){
                ((Player) sender).getPlayer().chat("/aetheriaitems:itemmenu");
            }
        } else if(sender.hasPermission(Permission.ITEM.node)){
            if(sender instanceof Player){
                if(args.length == 0){
                    Player player = ((Player) sender);
                    sender.sendMessage(ChatColor.GREEN + "Attempting To Open The GUI...");
                    new itemmenuinv().open(player);
                }
            }
            else
                sender.sendMessage(ChatColor.RED + "ERROR: You need to be a player to execute this");
        }
        else sender.sendMessage(ChatColor.RED + "Error: You do not have permission to do this!");
        return true;
    }
}
