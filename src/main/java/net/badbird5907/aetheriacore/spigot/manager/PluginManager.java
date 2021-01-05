package net.badbird5907.aetheriacore.spigot.manager;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static org.bukkit.Bukkit.*;

public class PluginManager {
	public static final String prefix = "§8[§6AEC§8] ";
	public static List<String> OnlinePlayers = new ArrayList<>();
	public static List<String> OnlineVisiblePlayers = new ArrayList<>();
	public static List<String> VanishedPlayers = new ArrayList<>();
	public static String Debugprefix = "§8[§cDEBUG§8] ";
	public static String ServerType = requireNonNull(getServer().getPluginManager().getPlugin("AetheriaCore")).getConfig().getString("Server-Type");
	final AetheriaCore plugin;

	public PluginManager(AetheriaCore plugin) {
		this.plugin = plugin;
	}

	public static void log(final String string) {
		getLogger().info(prefix + string);
	}

	public static void warn(final String string) {
		getLogger().warning(prefix + string);
	}

	public static void critical_error(final String string) {
		getLogger().warning(prefix + string);
	}

	public static boolean is16() {
		return getBukkitVersion().contains("1.16");
	}

	public static boolean is8() {
		return getBukkitVersion().contains("1.8");
	}
}