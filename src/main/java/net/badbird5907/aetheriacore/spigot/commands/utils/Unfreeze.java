package net.badbird5907.aetheriacore.spigot.commands.utils;

import net.badbird5907.aetheriacore.spigot.api.StaffChatMessage;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Unfreeze implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission(permissionManager.freeze)) {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "Usage: /unfreeze <Player>");
                    return true;
                } else if (args.length <= 1) {
                    Player target = Bukkit.getPlayerExact(args[0]);

                    if (target instanceof Player) {
                        if (!freezePlayer.frozen.contains(target.getUniqueId())) {
                            sender.sendMessage(ChatColor.RED + "Player " + target.getDisplayName() + " is not frozen!");
                            return true;
                        } else {
                            freezePlayer.frozen.remove(target.getUniqueId());
                            StaffChatMessage.sendMessage("Aetheria Core ", ((Player) sender).getDisplayName() + " Has un-frozen " + target.getDisplayName() + " at coords: X: " + target.getLocation().getX() + " Y: " + target.getLocation().getY() + " Z: " + target.getLocation().getZ());
                            return true;
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "Error:" + args[0] + "Is Not A Player!");
                    }
                }
            } else {
                player.sendMessage(permissionManager.PermissionMessage);
            }
            return true;
        } else {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Usage: /unfreeze <Player>");
                return true;
            } else if (args.length <= 1) {
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target instanceof Player) {
                    if (!freezePlayer.frozen.contains(target.getUniqueId())) {
                        sender.sendMessage(ChatColor.RED + "Player " + target.getDisplayName() + " is not frozen!");
                        return true;
                    } else {
                        freezePlayer.frozen.remove(target.getUniqueId());
                        StaffChatMessage.sendMessage("Aetheria Core ", ("CONSOLE" + " Has un-frozen " + target.getDisplayName() + " at coords: X: " + target.getLocation().getX() + " Y: " + target.getLocation().getY() + " Z: " + target.getLocation().getZ()));
                        return true;
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Error:" + args[0] + "Is Not A Player!");
                }
            }
        }
        return true;
    }
}
