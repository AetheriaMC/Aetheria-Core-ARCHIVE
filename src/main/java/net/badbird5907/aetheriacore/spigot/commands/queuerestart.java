package net.badbird5907.aetheriacore.spigot.commands;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class queuerestart implements CommandExecutor {

    AetheriaCore plugin;
    public queuerestart(AetheriaCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ConsoleCommandSender console = getServer().getConsoleSender();
        if (sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission("aetheriacore.restart")){
                Bukkit.broadcastMessage("§c§lSERVER REBOOT IN §a§l15§c§l SECONDS");
                //wait 15 seconds
                Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                    public void run() {
                        Bukkit.broadcastMessage("§c§lSERVER REBOOT IN §e§l10§c§l SECONDS");
                        Bukkit.dispatchCommand(console, "title @a title {\"text\":\"SERVER REBOOT\",\"bold\":true,\"color\":\"red\"}");
                        Bukkit.dispatchCommand(console, "title @a subtitle [\"\",{\"text\":\"IN\",\"bold\":true,\"color\":\"red\"},{\"text\":\" 15\",\"bold\":true,\"color\":\"green\"},{\"text\":\" SECONDS\",\"bold\":true,\"color\":\"red\"}]");
                    }
                }, 300, 20);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {

                    }
                });


            }
            else{
                player.sendMessage(permissionManager.PermissionMessage);
            }
        }
        else{

        }
        return true;
    }

}
