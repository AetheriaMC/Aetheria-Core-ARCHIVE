package net.badbird5907.aetheriacore.spigot.commands.impl.utils;

import net.badbird5907.aetheriacore.spigot.error.NoPermsError;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import net.badbird5907.aetheriacore.spigot.util.TpsUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.management.ManagementFactory;

public class performance implements CommandExecutor {
    Runtime r = Runtime.getRuntime();
    long memUsed = (r.totalMemory() - r.freeMemory()) / 1048576;

    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {

        if (player.hasPermission(permissionManager.Performance)) {
            player.sendMessage(ChatColor.GREEN + "Server: " + Bukkit.getServer().getName());
            player.sendMessage(ChatColor.GREEN + "OS: " + System.getProperty("os.name"));
            double tps = TpsUtils.getCurrentTPS();
            if(tps > 19)
                player.sendMessage(ChatColor.GOLD + "TPS:" + ChatColor.GREEN + tps);
            else if(tps > 15){
                player.sendMessage(ChatColor.GOLD + "TPS:" + ChatColor.YELLOW + tps);
            }
            else{
                player.sendMessage(ChatColor.GOLD + "TPS:" + ChatColor.RED + tps);
            }
            if(cpuUsageBoolean() == true){
                player.sendMessage(ChatColor.GOLD + "Cpu Usage: " + ManagementFactory.getOperatingSystemMXBean().getSystemLoadAverage());
            }
            else{
                player.sendMessage(ChatColor.GOLD + "Cpu Usage: " + ChatColor.RED + "Not Supported");
            }
            //player.sendMessage(ChatColor.GOLD + "Cpu Usage: " + cpuUsageBoolean());
            player.sendMessage(ChatColor.GOLD + "RAM Usage: " + memUsed + "/" + r.totalMemory() / 1048576);
            player.sendMessage(ChatColor.GOLD + "Players: " + Bukkit.getOnlinePlayers().size());
            //player.sendMessage(ChatColor.GREEN + "NOTE: If the tps (" + Lag.getTPS() + ") is over 20, round it down to 20");
        } else {
            throw new NoPermsError((Player) player, "Performance");
        }
        return true;
    }

    private boolean cpuUsageBoolean() {
        if (ManagementFactory.getOperatingSystemMXBean().getSystemLoadAverage() == -1) {
            return false;
        } else {
            return true;
        }
    }
}
