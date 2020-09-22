package net.badbird5907.aetheriacore.spigot.commands;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import net.badbird5907.aetheriacore.spigot.manager.pluginManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ClearFloorDrops implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        if(player.hasPermission(permissionManager.ClearDrops)){
            player.sendMessage(pluginManager.prefix + ChatColor.WHITE + "Drops Cleared!");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minecraft:kill @e[type=item]");
        }
        return true;
    }
}
