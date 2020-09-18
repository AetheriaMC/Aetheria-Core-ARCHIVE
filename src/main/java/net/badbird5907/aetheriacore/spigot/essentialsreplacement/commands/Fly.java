package net.badbird5907.aetheriacore.spigot.essentialsreplacement.commands;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;


public class Fly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if(args.length == 0){
            Player target = Bukkit.getPlayerExact(args[0]);
            if (sender.hasPermission(permissionManager.fly)){
                if(player.isFlying() == false){
                    player.setFlying(true);
                    player.sendMessage(ChatColor.GOLD + "Flight: Enabled");
                }
                else{
                    player.setFlying(false);
                    player.sendMessage(ChatColor.GOLD + "Flight: " + ChatColor.RED + "Disabled");
                }
            }
            else{
                sender.sendMessage(permissionManager.PermissionMessage);
            }
            if(sender.hasPermission(permissionManager.flyothers)) {
                if(target instanceof Player){
                    if(target.isFlying() == true){
                        target.setFlying(false);
                        return true;
                    }
                    else{
                        target.setFlying(true);
                        return true;
                    }
                }
                else{
                    player.sendMessage(ChatColor.RED + "Error: " + target + "is not a player." + ChatColor.DARK_GRAY + " " + ChatColor.ITALIC + "INVALID_ARGUMENTS");
                }
            }
            else{
                sender.sendMessage(permissionManager.PermissionMessage);
            }
        }
        return true;
    }
}
