package net.badbird5907.aetheriacore.spigot.commands;

import de.myzelyam.api.vanish.VanishAPI;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import net.badbird5907.aetheriacore.spigot.manager.pluginManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class StaffMode implements CommandExecutor {
    public static List<String> StaffModeToggle = new ArrayList<String>();

    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        if(player instanceof Player) {
            if (player.hasPermission(permissionManager.StaffMode)) {
                if(StaffModeToggle.contains(player.getName())){
                    StaffModeToggle.remove(player.getName());
                    VanishAPI.showPlayer((Player) player);
                    player.sendMessage(pluginManager.prefix + ChatColor.WHITE + "Staff Mode Toggled " + ChatColor.RED + "OFF");
                    return true;
                }
                else{
                    player.sendMessage(pluginManager.prefix + ChatColor.WHITE + "Staff Mode Turned " + ChatColor.GREEN + "ON");
                    staffchat.staffchatToggle.add(player.getName());
                    StaffModeToggle.add(player.getName());
                    hush.hush.remove(player.getName());
                    VanishAPI.hidePlayer((Player) player);
                    return true;
                }

            } else {
                player.sendMessage(permissionManager.PermissionMessage);
            }
        }
        else{
            player.sendMessage(pluginManager.prefix + ChatColor.RED + "You must be a player to use this.");
        }
        return true;
    }
}
