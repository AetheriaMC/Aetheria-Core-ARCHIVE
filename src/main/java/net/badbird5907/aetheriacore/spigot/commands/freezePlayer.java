package net.badbird5907.aetheriacore.spigot.commands;

import net.badbird5907.aetheriacore.spigot.api.StaffChatMessage;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class freezePlayer implements CommandExecutor{
    public static List<UUID> frozen = new ArrayList<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission(permissionManager.freeze)) {
                Player target = Bukkit.getPlayerExact(args[0]);

                if (args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "Usage: /freeze <Player>");
                    return true;
                } else if (args.length <= 1) {
                    if (target instanceof Player) {
                        if (frozen.contains(target.getUniqueId())) {
                            sender.sendMessage(ChatColor.RED + "Player " + target.getDisplayName() + " is already frozen!");
                            return true;
                        } else {
                            if (target.hasPermission(permissionManager.BypassFreeze)) {
                                sender.sendMessage(ChatColor.RED + "Error: This player can't be frozen!");
                                return true;
                            } else {
                                double playerLocX = player.getLocation().getX();
                                double playerLocY = player.getLocation().getY();
                                double playerLocZ = player.getLocation().getZ();
                                if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.AIR) {
                                    StaffChatMessage.sendmessage("DEBUG", "works");
                                }
                                frozen.add(target.getUniqueId());
                                sender.sendMessage(ChatColor.GREEN + target.getDisplayName() + "Is now frozen!");
                                sender.sendMessage(ChatColor.DARK_GRAY + "The player will be messaged that they are frozen");
                                sender.sendMessage(ChatColor.DARK_GRAY + "If they are in the air, they will be spammed.");
                                StaffChatMessage.sendmessage("Aetheria Core ", ((Player) sender).getDisplayName() + " Has frozen " + target.getDisplayName() + " at coords: X: " + target.getLocation().getX() + " Y: " + target.getLocation().getY() + " Z: " + target.getLocation().getZ());
                                return true;
                            }
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "Error:" + args[0] + "Is Not A Player!");
                    }
                }
            } else {
                player.sendMessage(permissionManager.PermissionMessage);
            }
        }
        else{
            Player target = Bukkit.getPlayerExact(args[0]);

            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Usage: /freeze <Player>");
                return true;
            } else if (args.length <= 1) {
                if (target instanceof Player) {
                    if (frozen.contains(target.getUniqueId())) {
                        sender.sendMessage(ChatColor.RED + "Player " + target.getDisplayName() + " is already frozen!");
                        return true;
                    } else {
                        if (target.hasPermission(permissionManager.BypassFreeze)) {
                            sender.sendMessage(ChatColor.RED + "Error: This player can't be frozen!");
                            return true;
                        } else {
                            frozen.add(target.getUniqueId());
                            sender.sendMessage(ChatColor.GREEN + target.getDisplayName() + "Is now frozen!");
                            sender.sendMessage(ChatColor.DARK_GRAY + "The player will be messaged that they are frozen");
                            sender.sendMessage(ChatColor.DARK_GRAY + "If they are in the air, they will be spammed.");
                            StaffChatMessage.sendmessage("Aetheria Core ", ((Player) sender).getDisplayName() + " Has frozen " + target.getDisplayName() + " at coords: X: " + target.getLocation().getX() + " Y: " + target.getLocation().getY() + " Z: " + target.getLocation().getZ());
                            return true;
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Error:" + args[0] + "Is Not A Player!");
                }
            }
        }
        return true;
    }
}
