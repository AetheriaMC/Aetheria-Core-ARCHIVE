package net.badbird5907.aetheriacore.spigot.commands;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import net.badbird5907.aetheriacore.spigot.manager.pluginManager;
import net.md_5.bungee.protocol.packet.Chat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class staffchat implements CommandExecutor {
    public static List <String> staffchatToggle = new ArrayList<String>();

    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        if(!(player instanceof Player)){
            player.sendMessage(pluginManager.prefix + ChatColor.RED + "You must be a player to toggle this. use /qc <text> instead.");
        }
        else {
            if (player.hasPermission(permissionManager.staffchat)) {
                if (staffchatToggle.contains(player.getName())) {
                    if (!StaffMode.StaffModeToggle.contains(player.getName())) {
                        player.sendMessage(pluginManager.prefix + ChatColor.WHITE + "StaffChat turned " + ChatColor.RED + "OFF");
                        staffchatToggle.remove(player.getName());
                    } else {
                        player.sendMessage(pluginManager.prefix + ChatColor.RED + "Error: Staff Mode is active. do /sm to disable." + ChatColor.DARK_GRAY + " " + ChatColor.ITALIC + "STAFF_MODE_ON");
                    }

                } else {
                    player.sendMessage(pluginManager.prefix + ChatColor.WHITE + "StaffChat turned " + ChatColor.GREEN + "ON");
                    staffchatToggle.add(player.getName());
                }
            } else {
                if(staffchatToggle.contains(player.getName())){
                    staffchatToggle.remove(player.getName());
                    return true;
                }
                else {
                    player.sendMessage(permissionManager.PermissionMessage);
                    return true;
                }
            }
            /*
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("on")){
                    if(player.hasPermission(permissionManager.staffchat)){
                        if(StaffMode.StaffModeToggle.contains(player.getName())){
                            player.sendMessage(pluginManager.prefix + ChatColor.RED + "Error: Staff Mode is active. do /sm to disable." + ChatColor.DARK_GRAY + " " + ChatColor.ITALIC + "STAFF_MODE_ON");
                            return true;
                        }else {
                            if (staffchatToggle.contains(player.getName())) {
                                player.sendMessage(pluginManager.prefix + ChatColor.RED + "Staff Chat is already on!" + ChatColor.DARK_GRAY + " " + ChatColor.ITALIC + "STAFFCHAT_ALREADY_ENABLED");
                                return true;
                            } else {
                                staffchatToggle.add(player.getName());
                                player.sendMessage(pluginManager.prefix + ChatColor.WHITE + "StaffChat turned " + ChatColor.GREEN + "ON");
                                return true;
                            }
                        }
                    }
                    else{
                        player.sendMessage(permissionManager.PermissionMessage);
                    }
                }
                if(args[0].equalsIgnoreCase("off")){
                    if(player.hasPermission(permissionManager.staffchat)){
                        if(StaffMode.StaffModeToggle.contains(player.getName())){
                            player.sendMessage(pluginManager.prefix + ChatColor.RED + "Error: Staff Mode is active. do /sm to disable." + ChatColor.DARK_GRAY + " " + ChatColor.ITALIC + "STAFF_MODE_ON");
                            return true;
                        }else {
                            if (!staffchatToggle.contains(player.getName())) {
                                player.sendMessage(pluginManager.prefix + ChatColor.RED + "Staff Chat is already off!" + ChatColor.DARK_GRAY + " " + ChatColor.ITALIC + "STAFFCHAT_ALREADY_ENABLED");
                                return true;
                            } else {
                                staffchatToggle.remove(player.getName());
                                player.sendMessage(pluginManager.prefix + ChatColor.WHITE + "StaffChat turned " + ChatColor.RED + "OFF");
                                return true;
                            }
                        }
                    }
                    else{
                        player.sendMessage(permissionManager.PermissionMessage);
                    }
                }
            }
             */
        }
        return true;
    }

}
