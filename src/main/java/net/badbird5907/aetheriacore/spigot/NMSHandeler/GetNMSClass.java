package net.badbird5907.aetheriacore.spigot.NMSHandeler;

import static java.lang.Class.forName;
import static org.bukkit.Bukkit.getServer;

public class GetNMSClass {
	public static Class<?> getNMSClass(String name) {
		try {
			return forName("net.minecraft.server." + getServer().getClass().getPackage().getName().split("\\.")[3] + "." + name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}