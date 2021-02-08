package net.badbird5907.aetheriacore.spigot.util;

import org.bukkit.Bukkit;

public class TpsUtils {
    public static double getCurrentTPS(){
        return Bukkit.getServer().getTPS()[0];
    }
    public static double[] getTps(){
        return Bukkit.getServer().getTPS();
    }
}
