package net.badbird5907.aetheriacore.spigot.commands.utils;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GetViewDist implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length == 1){
            Player target = Bukkit.getPlayerExact(args[0]);
            if(sender.hasPermission(permissionManager.getviewdist))
                sender.sendMessage(target.getDisplayName() + "'s render distance is " + String.valueOf(target.getViewDistance()));
            else
                sender.sendMessage(permissionManager.PermissionMessage);
            return true;
        }
        else{
            sender.sendMessage(ChatColor.RED + "USAGE: /getviewdistance <player>");
            return true;
        }

    }
}
