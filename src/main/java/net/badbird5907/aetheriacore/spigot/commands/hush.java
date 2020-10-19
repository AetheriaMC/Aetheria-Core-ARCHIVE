package net.badbird5907.aetheriacore.spigot.commands;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import net.badbird5907.aetheriacore.spigot.manager.pluginManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class hush implements CommandExecutor {
    public static List<UUID> hush = new ArrayList<UUID>();
    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        if(player instanceof Player){
            if(player.hasPermission(permissionManager.hush)){
                if(hush.contains(((Player) player).getUniqueId())){
                    hush.remove(((Player) player).getUniqueId());
                    player.sendMessage(pluginManager.prefix + ChatColor.GREEN + "You can now see the Staff Chat.");
                    return true;
                }
                else{
                    if(StaffMode.StaffModeToggle.contains(((Player) player).getUniqueId())){
                        player.sendMessage(pluginManager.prefix + ChatColor.RED + "Error: Staff Mode is active. do /sm to disable." + ChatColor.DARK_GRAY + " " + ChatColor.ITALIC + "STAFF_MODE_ON");
                        return true;
                    }
                    else{
                        hush.add(((Player) player).getUniqueId());
                        staffchat.staffchatToggle.remove(((Player) player).getUniqueId());
                        player.sendMessage(pluginManager.prefix + ChatColor.GREEN + "StaffChat Ignored. Do /hush to turn back on or relog.");
                        return true;
                    }
                }

            }
            else{
                player.sendMessage(permissionManager.PermissionMessage);
            }

        }
        else{
            player.sendMessage("You Must Be A Player To Execute This Command.");
        }

        return true;
    }
}
