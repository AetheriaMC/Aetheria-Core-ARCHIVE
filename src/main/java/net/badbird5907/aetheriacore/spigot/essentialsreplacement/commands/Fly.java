package net.badbird5907.aetheriacore.spigot.essentialsreplacement.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.badbird5907.aetheriacore.spigot.manager.Permission.FLY;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.PermissionMessage;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.flyothers;
import static org.bukkit.Bukkit.getPlayerExact;
import static org.bukkit.ChatColor.*;

public class Fly implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		if (args.length == 0) {
			Player target = getPlayerExact(args[0]);
			if (sender.hasPermission(FLY.node)) if (!player.isFlying()) {
				player.setFlying(true);
				player.sendMessage(GOLD + "Flight: Enabled");
			} else {
				player.setFlying(false);
				player.sendMessage(GOLD + "Flight: " + RED + "Disabled");
			}
			else sender.sendMessage(PermissionMessage);
			if (sender.hasPermission(flyothers)) {
				if (target instanceof Player) {
					target.setFlying(!target.isFlying());
					return true;
				}
				player.sendMessage(RED + "Error: " + target + "is not a player." + DARK_GRAY + " " + ITALIC + "INVALID_ARGUMENTS");
			} else {
				sender.sendMessage(PermissionMessage);
			}
		}
		return true;
	}
}