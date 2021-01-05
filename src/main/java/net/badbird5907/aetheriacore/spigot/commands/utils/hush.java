package net.badbird5907.aetheriacore.spigot.commands.utils;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static net.badbird5907.aetheriacore.spigot.commands.staff.StaffMode.StaffModeToggle;
import static net.badbird5907.aetheriacore.spigot.commands.staff.staffchat.staffchatToggle;
import static net.badbird5907.aetheriacore.spigot.manager.PluginManager.prefix;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.PermissionMessage;
import static org.bukkit.ChatColor.*;

public class hush implements CommandExecutor {
	public static List<UUID> hush = new ArrayList<UUID>();

	@Override
	public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
		if (player instanceof Player) {
			if (player.hasPermission(permissionManager.hush)) {
				if (hush.contains(((Player) player).getUniqueId())) {
					hush.remove(((Player) player).getUniqueId());
					player.sendMessage(prefix + GREEN + "You can now see the Staff Chat.");
				} else {
					if (StaffModeToggle.contains(((Player) player).getUniqueId()))
						player.sendMessage(prefix + RED + "Error: Staff Mode is active. do /sm to disable." + DARK_GRAY + " " + ITALIC + "STAFF_MODE_ON");
					else {
						hush.add(((Player) player).getUniqueId());
						staffchatToggle.remove(((Player) player).getUniqueId());
						player.sendMessage(prefix + GREEN + "StaffChat Ignored. Do /hush to turn back on or relog.");
					}
				}
				return true;
			}
			player.sendMessage(PermissionMessage);
		} else player.sendMessage("You Must Be A Player To Execute This Command.");
		return true;
	}
}