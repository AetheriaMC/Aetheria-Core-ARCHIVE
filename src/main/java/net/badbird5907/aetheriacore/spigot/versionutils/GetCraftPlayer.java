package net.badbird5907.aetheriacore.spigot.versionutils;

import org.bukkit.Bukkit;

public class GetCraftPlayer {
    public static Class<?> getcraftplayer() throws ClassNotFoundException {
        try{
            String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
            return Class.forName("org.bukkit.craftbukkit." + version + ".entity.CraftPlayer");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
