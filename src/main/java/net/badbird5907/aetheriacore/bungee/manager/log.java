package net.badbird5907.aetheriacore.bungee.manager;

import net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee;

public class log {
    public static AetheriaCoreBungee plugin;
    public log(AetheriaCoreBungee plugin) {
        this.plugin = plugin;
    }
    public static String prefix = "§8[§6AEC§8] ";
    public static void Log(String s){
        plugin.getLogger().info(prefix + s);
    }
    public static void Warn(String s){
        plugin.getLogger().warning(prefix + s);
    }
    public static String permissionmessage = "§cYou do not have permissions to do this!§8 §oNO_PERMISSIONS";
}
