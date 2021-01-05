package net.badbird5907.aetheriacore.spigot.commands.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static net.badbird5907.aetheriacore.spigot.manager.DebugLogger.DebugLog;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.*;
import static org.bukkit.Bukkit.getPlayerExact;
import static org.bukkit.ChatColor.*;
import static org.bukkit.Material.AIR;
import static org.bukkit.block.BlockFace.DOWN;

public class freezePlayer implements CommandExecutor {
	public static List<UUID> frozen = new ArrayList<>();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (sender.hasPermission(freeze)) {
				if (args.length == 0) {
					sender.sendMessage(RED + "Usage: /freeze <Player>");
					return true;
				} else if (args.length <= 1) {
					Player target = getPlayerExact(args[0]);
					if (target != null) {
						if (frozen.contains(target.getUniqueId()))
							sender.sendMessage(RED + "Player " + target.getDisplayName() + " is already frozen!");
						else if (target.hasPermission(BypassFreeze))
							sender.sendMessage(RED + "Error: This player can't be frozen!");
						else {
							double playerLocX = player.getLocation().getX();
							double playerLocY = player.getLocation().getY();
							double playerLocZ = player.getLocation().getZ();
							if (player.getLocation().getBlock().getRelative(DOWN).getType() == AIR)
								DebugLog("works");
							frozen.add(target.getUniqueId());
							sender.sendMessage(GREEN + target.getDisplayName() + "Is now frozen!");
							sender.sendMessage(DARK_GRAY + "The player will be messaged that they are frozen");
							sender.sendMessage(DARK_GRAY + "If they are in the air, they will be spammed.");
							//TODO write new alert class
							//StaffChatMessage.sendMessage("Aetheria Core ", ((Player) sender).getDisplayName() + " Has frozen " + target.getDisplayName() + " at coords: X: " + target.getLocation().getX() + " Y: " + target.getLocation().getY() + " Z: " + target.getLocation().getZ());
						}
						return true;
					}
					sender.sendMessage(RED + "Error:" + args[0] + "Is Not A Player!");
				}
			} else player.sendMessage(PermissionMessage);
		} else if (args.length == 0) {
			sender.sendMessage(RED + "Usage: /freeze <Player>");
			return true;
		} else if (args.length <= 1) {
			Player target = getPlayerExact(args[0]);
			if (target != null) {
				if (frozen.contains(target.getUniqueId()))
					sender.sendMessage(RED + "Player " + target.getDisplayName() + " is already frozen!");
				else if (target.hasPermission(BypassFreeze))
					sender.sendMessage(RED + "Error: This player can't be frozen!");
				else {
					frozen.add(target.getUniqueId());
					sender.sendMessage(GREEN + target.getDisplayName() + "Is now frozen!");
					sender.sendMessage(DARK_GRAY + "The player will be messaged that they are frozen");
					sender.sendMessage(DARK_GRAY + "If they are in the air, they will be spammed.");
					//TODO here
					//StaffChatMessage.sendMessage("Aetheria Core ", ("CONSOLE" + " Has frozen " + target.getDisplayName() + " at coords: X: " + target.getLocation().getX() + " Y: " + target.getLocation().getY() + " Z: " + target.getLocation().getZ()));
				}
				return true;
			}
			sender.sendMessage(RED + "Error:" + args[0] + "Is Not A Player!");
		}
		return true;
	}
}