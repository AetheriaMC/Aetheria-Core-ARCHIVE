package net.badbird5907.aetheriacore.spigot.manager;

import org.bukkit.Bukkit;

public class loggingManager {
    public void log(final String string) {
        Bukkit.getLogger().info(string);
    }

    public void warn(final String string) {
        Bukkit.getLogger().warning(string);
    }
}
