package net.badbird5907.aetheriacore.spigot.commands.utils;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import net.badbird5907.aetheriacore.spigot.manager.PluginManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

public class ClearFloorDrops implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        if(player.hasPermission(permissionManager.ClearDrops)){
            if(args.length == 0){
                player.sendMessage(PluginManager.prefix + ChatColor.RED + "Usage: /clearfloordrops <World> (For World: This, All)");
                return true;
            }
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("all")){
                    int removed_entities = 0;
                    for (World w : Bukkit.getWorlds()) {
                        // Iterate through every entity in that world
                        for (Entity e : w.getEntities()) {
                            if(e.getType().equals("Item")){
                                e.remove();
                                removed_entities++;
                            }
                        }
                    }
                    Bukkit.broadcastMessage(PluginManager.prefix + removed_entities + " Entites Removed.");
                    player.sendMessage(PluginManager.prefix + ChatColor.WHITE + "Drops Cleared!");
                }
                if(args[0].equalsIgnoreCase("this")){
                    int removed_entities = 0;

                    Bukkit.broadcastMessage(PluginManager.prefix + removed_entities + " Entites Removed.");
                    player.sendMessage(PluginManager.prefix + ChatColor.WHITE + "Drops Cleared!");
                }
            }
        }
        else{
            player.sendMessage(permissionManager.PermissionMessage);
        }
        return true;
    }
}
