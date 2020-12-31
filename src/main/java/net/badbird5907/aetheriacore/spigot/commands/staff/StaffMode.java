package net.badbird5907.aetheriacore.spigot.commands.staff;

import de.myzelyam.api.vanish.VanishAPI;
import net.badbird5907.aetheriacore.spigot.commands.utils.hush;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import net.badbird5907.aetheriacore.spigot.manager.PluginManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StaffMode implements CommandExecutor {
    public static List<UUID> StaffModeToggle = new ArrayList<UUID>();

    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        if(player instanceof Player) {
            if (player.hasPermission(permissionManager.StaffMode)) {
                if(StaffModeToggle.contains(((Player) player).getUniqueId())){
                    StaffModeToggle.remove(((Player) player).getUniqueId());
                    VanishAPI.showPlayer((Player) player);
                    player.sendMessage(PluginManager.prefix + ChatColor.WHITE + "Staff Mode Toggled " + ChatColor.RED + "OFF");
                }
                else{
                    player.sendMessage(PluginManager.prefix + ChatColor.WHITE + "Staff Mode Turned " + ChatColor.GREEN + "ON");
                    staffchat.staffchatToggle.add(((Player) player).getUniqueId());
                    StaffModeToggle.add(((Player) player).getUniqueId());
                    hush.hush.remove(((Player) player).getUniqueId());
                    VanishAPI.hidePlayer((Player) player);
                }
                return true;

            } else {
                player.sendMessage(permissionManager.PermissionMessage);
            }
        }
        else{
            player.sendMessage(PluginManager.prefix + ChatColor.RED + "You must be a player to use this.");
        }
        return true;
    }
}
