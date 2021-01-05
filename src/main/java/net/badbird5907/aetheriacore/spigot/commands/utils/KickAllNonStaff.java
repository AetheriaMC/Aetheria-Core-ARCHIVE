package net.badbird5907.aetheriacore.spigot.commands.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static net.badbird5907.aetheriacore.spigot.manager.Permission.KICK_ALL;
import static net.badbird5907.aetheriacore.spigot.util.KickAllNonStaff.KickAll;

public class KickAllNonStaff implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		if (sender.hasPermission(KICK_ALL.node) && (args.length < 1))
			KickAll(stream(args).map(arg -> arg + " ").collect(joining()).trim());
		return true;
	}
}