package net.badbird5907.aetheriacore.spigot.commands.utilcommands;

import net.badbird5907.aetheriacore.spigot.manager.Permission;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class gma implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length == 0){
            if(sender.hasPermission(Permission.GMA.node)){
                if(sender instanceof Player){
                    ((Player) sender).setGameMode(GameMode.ADVENTURE);
                    sender.sendMessage(ChatColor.GREEN + "Set your game mode to:" + ChatColor.AQUA + " Adventure");
                    return true;
                }
            }else sender.sendMessage(permissionManager.PermissionMessage);
        }else if(args.length == 1){
            if(sender.hasPermission(Permission.GMA.node)){
                Player target = null;
                boolean pass;
                try{
                    target = Bukkit.getPlayerExact(args[0]);
                    pass = true;
                } catch (Exception e) {
                    pass = false;
                }
                if(pass){
                    target.setGameMode(GameMode.ADVENTURE);
                    sender.sendMessage(ChatColor.GREEN + "Set " + target.getDisplayName() + "'s game mode to:" + ChatColor.AQUA + " Adventure");
                    target.sendMessage(ChatColor.GREEN + "Set your game mode to:" + ChatColor.AQUA + " Adventure");
                    return true;
                }
            }else sender.sendMessage(permissionManager.PermissionMessage);
        }
        return true;
    }
}
