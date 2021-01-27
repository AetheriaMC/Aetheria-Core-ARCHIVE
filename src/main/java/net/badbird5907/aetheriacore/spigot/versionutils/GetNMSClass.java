package net.badbird5907.aetheriacore.spigot.versionutils;

import org.bukkit.Bukkit;

public class GetNMSClass {
    public static Class<?> getNMSClass(String name) {
        try {
            return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
