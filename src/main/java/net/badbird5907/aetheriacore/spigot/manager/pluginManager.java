package net.badbird5907.aetheriacore.spigot.manager;

import org.bukkit.Bukkit;

public class pluginManager {
    public static void log(String string){
        Bukkit.getLogger().info(string);
    }
    public static void warn(String string){
        Bukkit.getLogger().warning(string);
    }
}
