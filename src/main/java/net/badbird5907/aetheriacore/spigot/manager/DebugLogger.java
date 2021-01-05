package net.badbird5907.aetheriacore.spigot.manager;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

import static net.badbird5907.aetheriacore.spigot.manager.PluginManager.Debugprefix;
import static net.badbird5907.aetheriacore.spigot.manager.PluginManager.log;
import static org.bukkit.Bukkit.getOnlinePlayers;

public class DebugLogger {
	public static ArrayList<UUID> Debugplayers = new ArrayList<>();
	static AetheriaCore plugin;

	public DebugLogger(AetheriaCore plugin) {
		this.plugin = plugin;
	}

	public static void DebugLog(String string) {
		for (Player p : getOnlinePlayers())
			if (Debugplayers.contains(p.getUniqueId())) p.sendMessage(Debugprefix + string);
			else break;
		log(Debugprefix + string);
	}
}