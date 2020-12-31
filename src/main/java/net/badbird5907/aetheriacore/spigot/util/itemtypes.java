package net.badbird5907.aetheriacore.spigot.util;

import net.badbird5907.aetheriacore.spigot.manager.DebugLogger;
import net.badbird5907.aetheriaitems.api.GetCustomItems;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static org.bukkit.Bukkit.*;

public class itemtypes {
	public static List<String> blocks = new ArrayList<>();
	public static List<String> allitems = new ArrayList<>();
	public static List<String> items = new ArrayList<>();
	public static List<Material> blacklisted_items = new ArrayList<>();
	public static List<String> alist = new ArrayList<>();

	public static HashMap CustomItems() {
		if (getPluginManager().isPluginEnabled("AetheriaItems")) return GetCustomItems.get();
		else return null;
	}

	public static void addToAllItems() {
		if (getPluginManager().isPluginEnabled("AetheriaItems")) {
			Set<String> keys = GetCustomItems.get().keySet();
			int n = keys.size();
			keys.forEach(x -> {
				allitems.add(x);
				alist.add(x);
			});
			alist.forEach(DebugLogger::DebugLog);
		}
	}
}
