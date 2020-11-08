package net.badbird5907.aetheriacore.spigot.essentialsreplacement.commands;

import net.badbird5907.aetheriacore.spigot.manager.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;


public class gma implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (sender.hasPermission(Permission.GMA.node)){
            Player target = Bukkit.getPlayerExact(args[0]);
            if(args.length == 0){
                player.setGameMode(GameMode.ADVENTURE);
            }
            else{
                if (target instanceof Player){
                    player.setGameMode(GameMode.ADVENTURE);
                }
                else {
                    player.sendMessage(ChatColor.RED + "ERROR: Usage: /gma <Player>");
                }
            }
        }
        else{
            sender.sendMessage(permissionManager.PermissionMessage);
        }
        return true;
    }
}
