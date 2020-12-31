package net.badbird5907.aetheriacore.spigot.error;

import net.badbird5907.aetheriacore.spigot.manager.DebugLogger;
import net.badbird5907.aetheriacore.spigot.manager.PluginManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class BadArgsError extends RuntimeException{
    public BadArgsError(Player player) {
        DebugLogger.DebugLog("ERROR");
        PluginManager.warn("ERROR");
        player.sendMessage(ChatColor.RED + "ERROR: Bad arguments");
    }
    public BadArgsError(Player player, String reason) {
        DebugLogger.DebugLog("ERROR: " + reason);
        PluginManager.warn("ERROR: " + reason);
        player.sendMessage(ChatColor.RED + "ERROR: Bad arguments: " + reason);
    }
}