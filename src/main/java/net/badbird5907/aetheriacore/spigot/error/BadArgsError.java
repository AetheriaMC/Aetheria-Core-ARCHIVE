package net.badbird5907.aetheriacore.spigot.error;

import org.bukkit.entity.Player;

import static net.badbird5907.aetheriacore.spigot.manager.DebugLogger.DebugLog;
import static net.badbird5907.aetheriacore.spigot.manager.PluginManager.warn;
import static org.bukkit.ChatColor.RED;

public class BadArgsError extends RuntimeException {
	public BadArgsError(Player player) {
		DebugLog("ERROR");
		warn("ERROR");
		player.sendMessage(RED + "ERROR: Bad arguments");
	}

	public BadArgsError(Player player, String reason) {
		DebugLog("ERROR: " + reason);
		warn("ERROR: " + reason);
		player.sendMessage(RED + "ERROR: Bad arguments: " + reason);
	}
}