package net.badbird5907.aetheriacore.spigot.commands.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static net.badbird5907.aetheriacore.spigot.manager.PluginManager.prefix;
import static net.badbird5907.aetheriacore.spigot.util.NPC.createNPC;

public class CreateNPC implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String strings, String[] args) {
		if (command.getName().equalsIgnoreCase("createnpc")) {
			if (!(sender instanceof Player)) return true;
			Player player = (Player) sender;
			if (args.length == 1) {
				createNPC(player, stream(args).map(arg -> arg + " ").collect(joining()).trim().replace("&", "§"));
				player.sendMessage("NPC Created!");
			} else player.sendMessage(prefix + "Must use a name!");
		}
		return true;
	}
}