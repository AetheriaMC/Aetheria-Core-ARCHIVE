package net.badbird5907.aetheriacore.bungee.manager;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.requireNonNull;
import static java.util.UUID.fromString;
import static net.badbird5907.aetheriacore.bungee.util.DataFile.getData;

public class PlayerManager {
	private static final Configuration data = getData("bungeedata");
	public static List<UUID> current_sc_players = new ArrayList<>();

	public static boolean IsOnSc(ProxiedPlayer player) {
		return requireNonNull(data).contains("Data." + player.getUniqueId());
	}

	public static void PutInSc(ProxiedPlayer player) {
		//get list of current sc players
		current_sc_players.clear();
		List<String> current_players1 = requireNonNull(data).getStringList("StaffChat");
		current_players1.forEach(n -> current_sc_players.add(fromString(n)));
		data.set("StaffChat", current_sc_players);
	}
}