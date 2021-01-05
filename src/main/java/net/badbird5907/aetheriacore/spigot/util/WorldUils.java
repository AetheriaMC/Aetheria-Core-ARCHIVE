package net.badbird5907.aetheriacore.spigot.util;

import org.bukkit.entity.Player;

import java.time.LocalTime;

import static java.time.LocalTime.ofSecondOfDay;

public class WorldUils {
	public static String current12hTime(Player player) {
		long timeticks = player.getWorld().getTime() / 20L;
		LocalTime timeOfDay = ofSecondOfDay(timeticks);
		return timeOfDay.toString();
	}

	public static String current12hTime(Long time) {
		long timeticks = time / 20L;
		LocalTime timeOfDay = ofSecondOfDay(timeticks);
		return timeOfDay.toString();
	}
}