package net.badbird5907.aetheriacore.spigot.commands.management.luckperms;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static org.bukkit.ChatColor.RED;

public class addPermission implements CommandExecutor {
	AetheriaCore plugin;

	public addPermission(AetheriaCore plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		if (args.length != 2) {
			sender.sendMessage(RED + "Please specify a player & a group!");
			return true;
		}
		String playerName = args[0], groupName = args[1], target = args[0];
		return true;
	}
}