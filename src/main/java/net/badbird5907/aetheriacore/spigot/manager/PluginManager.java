package net.badbird5907.aetheriacore.spigot.manager;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class PluginManager {
    AetheriaCore plugin;

    public PluginManager(AetheriaCore plugin) {
        this.plugin = plugin;
    }

    public static List<String> OnlinePlayers = new ArrayList<String>();
    public static List<String> OnlineVisiblePlayers = new ArrayList<String>();
    public static List<String> VanishedPlayers = new ArrayList<String>();
    public static String prefix = "[AEC] ";
    public static String Debugprefix = "[DEBUG] ";
    public static void initLogger(){
        if(!is8()){
            prefix = "§8[§6AEC§8] ";
            Debugprefix = "§8[§cDEBUG§8]";
        }
        else{
            Debugprefix = ChatColor.DARK_GRAY + "[" + ChatColor.RED + "DEBUG" + ChatColor.DARK_GRAY + "] ";
            prefix = ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "AEC" + ChatColor.DARK_GRAY + " ";
        }
    }
    public static void log(final String string) {
        Bukkit.getLogger().info(PluginManager.prefix + string);
    }

    public static void warn(final String string) {
        Bukkit.getLogger().warning(PluginManager.prefix + string        );
    }
    public static void critical_error(final String string) {
        Bukkit.getLogger().warning(PluginManager.prefix + string        );
    }
    public static String ServerType = Bukkit.getServer().getPluginManager().getPlugin("AetheriaCore").getConfig().getString("Server-Type");
    public static boolean is16(){
        return Bukkit.getBukkitVersion().contains("1.16");
    }
    public static boolean is8(){
        return Bukkit.getBukkitVersion().contains("1.8");
    }

}
