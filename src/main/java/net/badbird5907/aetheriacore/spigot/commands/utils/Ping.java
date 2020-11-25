package net.badbird5907.aetheriacore.spigot.commands.utils;

import net.badbird5907.aetheriacore.spigot.manager.Permission;
import net.badbird5907.aetheriacore.spigot.util.GetPlayerPing;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ping implements CommandExecutor {
    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String s,  String[] args) {
        if(sender instanceof Player){
            if(args.length == 0){
                int ping = GetPlayerPing.getPing((Player) sender);
                sender.sendMessage(ChatColor.GREEN + "Your ping is: " + ping + "ms");
            }
        }
        if(args.length == 1){
            Player target = Bukkit.getPlayerExact(args[0]);
            if(sender.hasPermission(Permission.PING.node)){
                int ping = GetPlayerPing.getPing(target);
                sender.sendMessage(ChatColor.GREEN + target.getName() + "'s ping is: " + ping);
            }
        }

        return true;
    }
}
