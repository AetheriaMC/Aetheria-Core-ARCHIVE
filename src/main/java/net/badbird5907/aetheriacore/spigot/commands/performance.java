package net.badbird5907.aetheriacore.spigot.commands;

import net.badbird5907.aetheriacore.spigot.other.Lag;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class performance implements CommandExecutor {
    Runtime r = Runtime.getRuntime();
    long memUsed = (r.totalMemory() - r.freeMemory()) / 1048576;
    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        if(player.hasPermission("aetheriacore.performance")){
            player.sendMessage(ChatColor.GREEN + "Server: " + Bukkit.getServer().getName());
            player.sendMessage(ChatColor.GOLD + "Tps: " + Lag.getTPS());
            player.sendMessage(ChatColor.GOLD + "Cpu Usage: " + "NOT CURRENTLY SUPPORTED");
            player.sendMessage(ChatColor.GOLD + "RAM Usage: " + memUsed + "/" + r.totalMemory() / 1048576);
            player.sendMessage(ChatColor.GOLD + "Players: " + Bukkit.getServer().getOnlinePlayers());
            player.sendMessage(ChatColor.GREEN + "NOTE: If the tps (" + Lag.getTPS() + ") is over 20, round it down to 20");
        }
        else{
            player.sendMessage(ChatColor.RED + "You don't have the required permission node 'aetheriacore.performance' to execute this command.");
        }
        return true;
    }
}
