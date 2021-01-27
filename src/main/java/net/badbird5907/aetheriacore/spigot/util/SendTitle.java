package net.badbird5907.aetheriacore.spigot.util;

import net.badbird5907.aetheriacore.spigot.versionutils.Reflection;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class SendTitle extends Reflection {
	public void send(Player player, String title, String subtitle, int fadeInTime, int showTime, int fadeOutTime) {
		try {
			Object chatTitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a",
					String.class).invoke(null,
					"{\"text\": \"" + title + "\"}");
			Constructor<?> titleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0],
					getNMSClass("IChatBaseComponent"),
					int.class,
					int.class,
					int.class);
			Object packet = titleConstructor.newInstance(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null),
					chatTitle,
					fadeInTime,
					showTime,
					fadeOutTime);
			Object timingPacket = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0],
					getNMSClass("IChatBaseComponent"),
					int.class,
					int.class,
					int.class).newInstance(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null),
					getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a",
							String.class).invoke(null,
							"{\"text\": \"" + subtitle + "\"}"),
					fadeInTime,
					showTime,
					fadeOutTime);
			sendPacket(player,
					packet);
			sendPacket(player,
					timingPacket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}