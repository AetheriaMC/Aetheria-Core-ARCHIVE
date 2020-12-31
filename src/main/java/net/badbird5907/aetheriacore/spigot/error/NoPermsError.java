package net.badbird5907.aetheriacore.spigot.error;

import net.badbird5907.aetheriacore.spigot.manager.DebugLogger;
import net.badbird5907.aetheriacore.spigot.manager.PluginManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class NoPermsError extends RuntimeException{
    public NoPermsError(Player player) {
        DebugLogger.DebugLog(player.getDisplayName() + "was denied access to a command.");
        PluginManager.log(player.getDisplayName() + "was denied access.");
        player.sendMessage(ChatColor.RED + "You do not have permissions to do this!");
    }
    public NoPermsError(Player player, String command) {
        DebugLogger.DebugLog(player.getDisplayName() + "was denied access to /" + command + ".");
        PluginManager.log(player.getDisplayName() + "was denied access to /" + command + ".");
        player.sendMessage(ChatColor.RED + "You do not have permissions to do this!");
    }
}