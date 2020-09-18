package net.badbird5907.aetheriacore.spigot.commands;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class delplayerdata implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        if (player.hasPermission(permissionManager.DelPlayerData)){
            if (command.getName().equalsIgnoreCase("delplayerdata")) {
                if (args.length <= 1) {
                    player.sendMessage(ChatColor.RED + "Error: Command Usage:");
                    player.sendMessage(ChatColor.RED + "/delplayerdata <UUID> <Confirmation Code>");
                } else {
                    if (args[1].equals("h4e3fy8hu")) {
                        return true;
                    }
                }
            }
            else{
                player.sendMessage(permissionManager.PermissionMessage);
            }

        }
        return true;
    }
}
