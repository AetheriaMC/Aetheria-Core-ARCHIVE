package net.badbird5907.aetheriacore.spigot.commands.fun;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

import static net.badbird5907.aetheriacore.spigot.manager.Permission.PING_WARS;
import static net.badbird5907.aetheriacore.spigot.util.GetPlayerPing.getPing;
import static org.bukkit.Bukkit.getOnlinePlayers;

public class PingWars implements CommandExecutor {
	private static final HashMap<Player, Integer> allPings = new HashMap<>();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		if (sender.hasPermission(PING_WARS.node) && (args.length == 1) && (args[0].equalsIgnoreCase("low") || args[0].equalsIgnoreCase("lowest")))
			getOnlinePlayers().forEach(p -> allPings.put(p, getPing(p)));
		return true;
	}
}