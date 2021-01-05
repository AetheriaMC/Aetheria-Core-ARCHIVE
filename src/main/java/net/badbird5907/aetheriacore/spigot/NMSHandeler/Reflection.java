package net.badbird5907.aetheriacore.spigot.NMSHandeler;

import org.bukkit.entity.Player;

import static java.lang.Class.forName;
import static org.bukkit.Bukkit.getServer;

public class Reflection {
	public void sendPacket(Player player, Object packet) {
		try {
			Object handle = player.getClass().getMethod("getHandle").invoke(player);
			Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
			playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Class<?> getNMSClass(String name) {
		try {
			return forName("net.minecraft.server." + getServer().getClass().getPackage().getName().split("\\.")[3] + "." + name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}