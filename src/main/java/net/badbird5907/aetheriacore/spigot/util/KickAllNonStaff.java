package net.badbird5907.aetheriacore.spigot.util;

import org.bukkit.entity.Player;

import static net.badbird5907.aetheriacore.spigot.manager.Permission.STAFF;
import static org.bukkit.Bukkit.getOnlinePlayers;

public class KickAllNonStaff {
	public static void KickAll(String reason) {
		for (Player p : getOnlinePlayers())
			if (!p.hasPermission(STAFF.node)) p.kickPlayer(reason);
			else break;
	}
}
