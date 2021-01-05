package net.badbird5907.aetheriacore.spigot.NMSHandeler;

import static java.lang.Class.forName;
import static org.bukkit.Bukkit.getServer;

public class GetCraftPlayer {
	public static Class<?> getcraftplayer() throws ClassNotFoundException {
		try {
			String version = getServer().getClass().getPackage().getName().split("\\.")[3];
			return forName("org.bukkit.craftbukkit." + version + ".entity.CraftPlayer");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}