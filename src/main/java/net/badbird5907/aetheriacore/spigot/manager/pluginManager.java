package net.badbird5907.aetheriacore.spigot.manager;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class pluginManager {
    public static List<String> OnlinePlayers = new ArrayList<String>();
    public static List<String> OnlineVisiblePlayers = new ArrayList<String>();
    public static List<String> VanishedPlayers = new ArrayList<String>();
    public static void log(String string){
        Bukkit.getLogger().info(string);
    }
    public static void warn(String string){
        Bukkit.getLogger().warning(string);
    }
    public static String prefix = "§8[§6AEC§8] ";

}
