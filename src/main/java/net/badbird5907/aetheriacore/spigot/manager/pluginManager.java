package net.badbird5907.aetheriacore.spigot.manager;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class pluginManager {
    AetheriaCore plugin;

    public pluginManager(AetheriaCore plugin) {
        this.plugin = plugin;
    }

    public static List<String> OnlinePlayers = new ArrayList<String>();
    public static List<String> OnlineVisiblePlayers = new ArrayList<String>();
    public static List<String> VanishedPlayers = new ArrayList<String>();
    public static String prefix = "§8[§6AEC§8] ";
    public static String Debugprefix = "§8[§cDEBUG§8] ";
    public static void log(final String string) {
        Bukkit.getLogger().info(pluginManager.prefix + string);
    }

    public static void warn(final String string) {
        Bukkit.getLogger().warning(pluginManager.prefix + string        );
    }
    public static String ServerType = Bukkit.getServer().getPluginManager().getPlugin("AetheriaCore").getConfig().getString("Server-Type");

}
