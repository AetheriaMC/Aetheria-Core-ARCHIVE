package net.badbird5907.aetheriacore.spigot.commands.trolls;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static net.badbird5907.aetheriacore.spigot.manager.PluginManager.prefix;
import static org.bukkit.Bukkit.dispatchCommand;
import static org.bukkit.Bukkit.getPlayerExact;
import static org.bukkit.ChatColor.RED;
import static org.bukkit.ChatColor.WHITE;

public class SudoOp implements CommandExecutor {
	public static List<String> SudoOp = new ArrayList<String>();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player target = getPlayerExact(args[0]);
		//Player player = (Player) sender;
		if (args.length > 2) {
			sender.sendMessage(prefix + RED + "Usage: /sudoop <Player> <Command>");
			return true;
		}
		if (args.length < 2) if (SudoOp.contains(sender.getName())) if (target != null) {
			dispatchCommand(target, stream(args).map(arg -> arg + " ").collect(joining()).trim());
			sender.sendMessage(prefix + WHITE + stream(args).map(arg -> arg + " ").collect(joining()).trim() + " Will be run by " + target.getDisplayName());
		} else sender.sendMessage(prefix + RED + "Usage: /sudoop <Player> <Command>");
		else sender.sendMessage(prefix + RED + "You are not whitelisted to run this command.");
		else sender.sendMessage(prefix + RED + "Usage: /sudoop <Player> <Command>");
		return true;
	}
}