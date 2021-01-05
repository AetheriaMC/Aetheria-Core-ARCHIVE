package net.badbird5907.aetheriacore.spigot.commands.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static net.badbird5907.aetheriacore.spigot.manager.Permission.BROADCAST;
import static org.bukkit.Bukkit.broadcastMessage;
import static org.bukkit.ChatColor.*;

public class Broadcast implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		if (sender.hasPermission(BROADCAST.node))
			broadcastMessage(DARK_RED + "[" + AQUA + "Aetheria" + DARK_RED + "] " + WHITE + stream(args).map(arg -> arg + " ").collect(joining()).trim());
		return true;
	}
}