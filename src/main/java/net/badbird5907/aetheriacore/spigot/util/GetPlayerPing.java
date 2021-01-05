package net.badbird5907.aetheriacore.spigot.util;

import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

import static java.lang.Class.forName;
import static org.bukkit.Bukkit.getServer;

public class GetPlayerPing {
	public static int getPing(Player player) {
		try {
			// Building the version of the server in such a form we can use it
			// in NMS code.
			String bukkitVersion = getServer().getClass().getPackage().getName().substring(23);
			// Getting craftplayer
			Class<?> craftPlayer = forName("org.bukkit.craftbukkit." + bukkitVersion + ".entity.CraftPlayer");
			// Invoking method getHandle() for the player
			Object handle = craftPlayer.getMethod("getHandle").invoke(player);
			// Getting and returning field "ping" that holds player's ping obviously
			return (Integer) handle.getClass().getDeclaredField("ping").get(handle);
		} catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException e) {
			// Handle exceptions however you like, i chose to return value of
			// -1; since player's ping can't be -1.
			return -1;
		}
	}
}