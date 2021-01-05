package net.badbird5907.aetheriacore.spigot.commands.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static java.util.Objects.requireNonNull;
import static net.badbird5907.aetheriacore.spigot.manager.Permission.PING;
import static net.badbird5907.aetheriacore.spigot.util.GetPlayerPing.getPing;
import static org.bukkit.Bukkit.getPlayerExact;
import static org.bukkit.ChatColor.GREEN;

public class Ping implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		if ((sender instanceof Player) && (args.length == 0))
			sender.sendMessage(GREEN + "Your ping is: " + getPing((Player) sender) + "ms");
		if (args.length == 1) {
			Player target = getPlayerExact(args[0]);
			if (sender.hasPermission(PING.node))
				sender.sendMessage(GREEN + requireNonNull(target).getName() + "'s ping is: " + getPing(target));
		}
		return true;
	}
}