package net.badbird5907.aetheriacore.spigot.commands.utils;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import net.badbird5907.aetheriacore.spigot.util.IsInt;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class item implements CommandExecutor {
    public static List<String> names = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender,  Command command,  String s,  String[] args) {
        for (Material material : Material.values()) {
            names.add(material.name().toString());
        }
        if(sender.hasPermission(permissionManager.item)){
            if (args.length == 1){
                String item1 = args[0].toUpperCase();
                if (names.contains(item1)){
                    Player player = Bukkit.getPlayerExact(sender.getName());
                    Material m = Material.matchMaterial(item1);
                    ItemStack item = new ItemStack(m, 1);
                    player.getInventory().addItem(item);
                }
                else
                    sender.sendMessage(ChatColor.RED + args[0] + " is not a valid itemstack.");
            }
            if (args.length == 2){
                String item1 = args[0].toUpperCase();
                if (names.contains(item1)){
                    if(IsInt.Check(args[1])){
                        int amount = Integer.parseInt(args[1]);
                        Player player = Bukkit.getPlayerExact(sender.getName());
                        Material m = Material.matchMaterial(item1);
                        ItemStack item = new ItemStack(m, amount);
                        player.getInventory().addItem(item);
                    }
                    else
                        sender.sendMessage(ChatColor.RED + args[1] + " is not a integer.");

                }
                else
                    sender.sendMessage(ChatColor.RED + args[0] + " is not a valid itemstack.");
            }
        }
        return true;
    }
}
