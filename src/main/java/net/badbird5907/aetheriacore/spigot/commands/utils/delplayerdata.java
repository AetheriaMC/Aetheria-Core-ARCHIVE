package net.badbird5907.aetheriacore.spigot.commands.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.DelPlayerData;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.PermissionMessage;
import static org.bukkit.ChatColor.RED;

public class delplayerdata implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
		if (player.hasPermission(DelPlayerData))
			if (command.getName().equalsIgnoreCase("delplayerdata")) if (args.length <= 1) {
				player.sendMessage(RED + "Error: Command Usage:");
				player.sendMessage(RED + "/delplayerdata <UUID> <Confirmation Code>");
			} else if (args[1].equals("h4e3fy8hu")) return true;
			else player.sendMessage(PermissionMessage);
		return true;
	}
}