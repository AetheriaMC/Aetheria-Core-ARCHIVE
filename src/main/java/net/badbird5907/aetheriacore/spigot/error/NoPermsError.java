package net.badbird5907.aetheriacore.spigot.error;

import org.bukkit.entity.Player;

import static net.badbird5907.aetheriacore.spigot.manager.DebugLogger.DebugLog;
import static net.badbird5907.aetheriacore.spigot.manager.PluginManager.log;
import static org.bukkit.ChatColor.RED;

public class NoPermsError extends RuntimeException {
	public NoPermsError(Player player) {
		DebugLog(player.getDisplayName() + "was denied access to a command.");
		log(player.getDisplayName() + "was denied access.");
		player.sendMessage(RED + "You do not have permissions to do this!");
	}

	public NoPermsError(Player player, String command) {
		DebugLog(player.getDisplayName() + "was denied access to /" + command + ".");
		log(player.getDisplayName() + "was denied access to /" + command + ".");
		player.sendMessage(RED + "You do not have permissions to do this!");
	}
}