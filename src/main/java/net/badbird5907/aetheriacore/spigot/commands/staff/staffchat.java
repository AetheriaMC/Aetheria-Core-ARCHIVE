package net.badbird5907.aetheriacore.spigot.commands.staff;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static net.badbird5907.aetheriacore.spigot.commands.staff.StaffMode.StaffModeToggle;
import static net.badbird5907.aetheriacore.spigot.manager.PluginManager.prefix;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.PermissionMessage;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.staffchat;
import static org.bukkit.ChatColor.*;


public class staffchat implements CommandExecutor {
	public static List<UUID> staffchatToggle = new ArrayList<UUID>();

	@Override
	public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
		if (!(player instanceof Player))
			player.sendMessage(prefix + RED + "You must be a player to toggle this. use /qc <text> instead.");
		else if (player.hasPermission(staffchat)) {
			if (staffchatToggle.contains(((Player) player).getUniqueId()))
				if (!StaffModeToggle.contains(((Player) player).getUniqueId())) {
					player.sendMessage(prefix + WHITE + "StaffChat turned " + RED + "OFF");
					staffchatToggle.remove(((Player) player).getUniqueId());
				} else
					player.sendMessage(prefix + RED + "Error: Staff Mode is active. do /sm to disable." + DARK_GRAY + " " + ITALIC + "STAFF_MODE_ON");
			else {
				player.sendMessage(prefix + WHITE + "StaffChat turned " + GREEN + "ON");
				staffchatToggle.add(((Player) player).getUniqueId());
			}
		} else {
			if (staffchatToggle.contains(((Player) player).getUniqueId()))
				staffchatToggle.remove(((Player) player).getUniqueId());
			else player.sendMessage(PermissionMessage);
			return true;
		}/*
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
		return true;
	}
}