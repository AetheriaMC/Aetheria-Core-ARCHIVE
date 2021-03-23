package net.badbird5907.aetheriacore.spigot.commands.impl.utils;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class nick implements CommandExecutor {
    @Override
    public boolean onCommand( CommandSender sender,  Command command, String s, String[] args) {
        Player player = Bukkit.getPlayerExact(sender.getName());
        if(sender.hasPermission(permissionManager.nick)){
            player.setDisplayName(args[0]);
            player.sendMessage(ChatColor.GREEN + "Nick changed to " + args[0]);
        }
        else
            player.sendMessage(permissionManager.PermissionMessage);
        return true;
    }
}
