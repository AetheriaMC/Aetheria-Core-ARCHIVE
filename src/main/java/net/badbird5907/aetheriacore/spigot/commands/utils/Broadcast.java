package net.badbird5907.aetheriacore.spigot.commands.utils;

import net.badbird5907.aetheriacore.spigot.manager.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Broadcast implements CommandExecutor {
    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String s,  String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++){
            sb.append(args[i]).append(" ");
        }
        String allArgs = sb.toString().trim();
        if(sender.hasPermission(Permission.BROADCAST.node)){
            Bukkit.broadcastMessage(ChatColor.DARK_RED + "[" + ChatColor.AQUA + "Aetheria" + ChatColor.DARK_RED + "] " + ChatColor.WHITE + allArgs);
        }
        return true;
    }
}
