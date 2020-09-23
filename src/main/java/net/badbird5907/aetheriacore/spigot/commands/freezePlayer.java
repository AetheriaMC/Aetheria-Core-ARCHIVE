package net.badbird5907.aetheriacore.spigot.commands;

import net.badbird5907.aetheriacore.spigot.api.StaffChatMessage;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import net.badbird5907.aetheriacore.spigot.manager.pluginManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;


import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class freezePlayer implements CommandExecutor{
    public static List <String> frozen = new ArrayList<String>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (sender.hasPermission(permissionManager.freeze)) {
            Player target = Bukkit.getPlayerExact(args[0]);

            if (args[0].length() == 0) {
                sender.sendMessage(ChatColor.RED + "Usage: /freeze <Player>");
                return true;
            } else if(args.length == 1){
                if (target instanceof Player) {
                    if (frozen.contains(target.getName())) {
                        sender.sendMessage(ChatColor.RED + "Player" + target.getDisplayName() + "is already frozen!");
                        return true;
                    } else {
                        if(target.hasPermission(permissionManager.BypassFreeze)){
                            sender.sendMessage(ChatColor.RED + "Error: This player can't be frozen!");
                            return true;
                        }
                        else {
                            frozen.add(target.getName());
                            sender.sendMessage(ChatColor.GREEN + target.getDisplayName() + "Is now frozen!");
                            sender.sendMessage(ChatColor.DARK_GRAY + "The player will me messaged that they are frozen");
                            sender.sendMessage(ChatColor.DARK_GRAY + "If they are in the air, they will be spammed.");
                            StaffChatMessage.sendmessage("Aetheria Core", sender + " Has frozen " + target + " at coords: " + target.getLocation().toString());
                            return true;
                        }
                    }
                }
                else {
                    sender.sendMessage(ChatColor.RED + "Error:" + args[0] + "Is Not A Player!");
                }
            }
        } else {
            player.sendMessage(permissionManager.PermissionMessage);
        }
        return true;
    }
}
