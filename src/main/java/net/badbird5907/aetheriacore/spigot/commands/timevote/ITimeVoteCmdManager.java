package net.badbird5907.aetheriacore.spigot.commands.timevote;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.ChatColor.RED;

public class ITimeVoteCmdManager {
	public static void Process(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
		if (!(sender instanceof Player)) sender.sendMessage(RED + "This is player only! use time set instead.");
		if (args.length == 0 && sender instanceof Player) new TimeVoteGUI().open((Player) sender, "main");
	}

	public static String page(String[] args) {
		return args[0].equalsIgnoreCase("vote") ? "vote" : (args[0].equalsIgnoreCase("yes") ? "yes" : (args[0].equalsIgnoreCase("no") ? "no" : (args[0].equalsIgnoreCase("create") ? "create" : "main")));
	}
}