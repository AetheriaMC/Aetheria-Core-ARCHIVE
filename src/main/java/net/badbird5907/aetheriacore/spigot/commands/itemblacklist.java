package net.badbird5907.aetheriacore.spigot.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class itemblacklist implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        if(player.hasPermission("aetheriacore.itemblacklist.list")){
            player.sendMessage(ChatColor.DARK_GRAY + "--------------------------------------------");
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Blacklisted Items:");
            player.sendMessage(ChatColor.GOLD + "Command_Block | Bedrock | Debug_Stick | Command_Block_Minecart");
            player.sendMessage(ChatColor.GOLD + "Repeating_Command_Block | Barrier | Structure_Block ");
            player.sendMessage(ChatColor.GOLD + "Spawner | Jigsaw");
            player.sendMessage(ChatColor.GREEN + "To Bypass This, The Player Must Have OP or The Permission Node:");
            player.sendMessage(ChatColor.RED + "'aetheriacore.bypass.blacklistitems'");
            player.sendMessage(ChatColor.DARK_GRAY + "--------------------------------------------");
            return true;
        }
        else{
            player.sendMessage(ChatColor.RED + "You don't have the required permission node 'aetheriacore.itemblacklist.list' to execute this command.");

        }
        return true;
    }
}
