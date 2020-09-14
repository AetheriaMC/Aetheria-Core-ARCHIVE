package net.badbird5907.aetheriacore.spigot.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class delplayerdata implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        if (player.hasPermission("aetheriacore.delplayerdata")){
            if (command.getName().equalsIgnoreCase("delplayerdata")) {
                if (args.length <= 1) {
                    player.sendMessage(ChatColor.RED + "Error: Command Usage:");
                    player.sendMessage(ChatColor.RED + "/delplayerdata <UUID> <Confirmation Code>");
                } else {
                    if (args.equals("h4e3fy8hu")) {
                        return true;

                    }


                }

            }
            else{
                player.sendMessage(ChatColor.RED + "You don't have the required permission node 'aetheriacore.delplayerdata' to execute this command.");
            }

        }
        return true;
    }
}
