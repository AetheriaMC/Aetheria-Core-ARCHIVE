package net.badbird5907.aetheriacore.spigot.util;

import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

import static de.myzelyam.api.vanish.VanishAPI.*;

public class superVanishAPI {
	public void listVanishedPlayers(Player player, Player player2) {
		// A list of all ONLINE vanished players
		List<UUID> invisiblePlayers = getInvisiblePlayers();
		// Returns a list of the uuids of ALL vanished players, online AND offline
		// MAY INVOLVE A MYSQL-QUERY, so don't use it too often or use it on a different thread!
		List<UUID> allInvisiblePlayers = getAllInvisiblePlayers();
		// Hide a player
		hidePlayer(player);
		// Show a player
		showPlayer(player);
		// Returns true if player is allowed to see player2
		boolean canSee = canSee(player, player2);
		// Returns true if a player is invisible
		boolean isVanished = isInvisible(player);
	}
}