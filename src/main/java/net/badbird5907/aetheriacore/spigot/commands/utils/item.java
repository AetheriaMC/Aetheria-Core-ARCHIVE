package net.badbird5907.aetheriacore.spigot.commands.utils;

import net.badbird5907.aetheriacore.spigot.manager.DebugLogger;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import net.badbird5907.aetheriacore.spigot.manager.pluginManager;
import net.badbird5907.aetheriacore.spigot.util.IsInt;
import net.badbird5907.aetheriacore.spigot.util.itemtypes;
import net.md_5.bungee.protocol.packet.Chat;
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

    @Override
    public boolean onCommand(CommandSender sender,  Command command,  String s,  String[] args) {
        if(sender.hasPermission(permissionManager.item)){
            if(sender instanceof  Player){
                Player player = ((Player) sender).getPlayer();
                if(args.length == 0)
                    sender.sendMessage(ChatColor.RED + "USAGE: /item <ITEM> <AMMOUNT> \n " + ChatColor.GREEN + "You can also do /itemmenu");

                if (args.length == 1){
                    giveitem(player, args[0], "1", player);
                }
                if (args.length == 2){
                    giveitem(player, args[0], args[1], player);
                }
                else
                    sender.sendMessage(ChatColor.RED + "You must be a player to execute this!");
            }
        }
        return true;
    }
    public static void giveitem(Player sender, String item, String ammount, Player player){
        String item1 = item.toUpperCase();
        DebugLogger.DebugLog("Checking if " + item1 + " is an valid item");
        if (itemtypes.allitems.contains(item1)){
            DebugLogger.DebugLog(item1 + " is a valid item. Checking if custom");
            if(iscustom(item1)){
                DebugLogger.DebugLog(item1 + " is an custom item. ");
                player.getInventory().addItem(itemtypes.customitems.get(item1));
            }
            else{
                if(IsInt.Check(ammount)){
                    int amount = Integer.parseInt(ammount);
                    Player player1 = Bukkit.getPlayerExact(sender.getName());
                    Material m = Material.matchMaterial(item1);
                    ItemStack itemStack = new ItemStack(m, amount);
                    player1.getInventory().addItem(itemStack);
                    sender.sendMessage(ChatColor.GREEN + "Gave " + player.getName() + " " + item + " of " + ammount );
                }
                else
                    sender.sendMessage(ChatColor.RED + ammount + " is not a integer.");
            }

        }
        else
            sender.sendMessage(ChatColor.RED + item + " is not a valid itemstack.");
    }
    public static boolean iscustom(String item){
        if(itemtypes.customitems.containsKey(item.toUpperCase()))
            return true;
        else
            return false;
    }

}
