package net.badbird5907.aetheriacore.spigot.events;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.commands.staff.staffchat;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import net.badbird5907.aetheriacore.spigot.manager.pluginManager;
import net.badbird5907.aetheriacore.spigot.commands.utils.hush;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class onChat implements Listener {
    AetheriaCore plugin;
    public onChat(AetheriaCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void ChatListener (AsyncPlayerChatEvent event){

        if(staffchat.staffchatToggle.contains(event.getPlayer().getUniqueId())){
            event.setCancelled(true);
            for (Player player : Bukkit.getOnlinePlayers()) {
                if(player.hasPermission(permissionManager.staffchat)){
                    if(hush.hush.contains(player.getUniqueId())){
                        break;
                    }
                    else{
                        player.sendMessage(ChatColor.GOLD + "StaffChat" + ChatColor.DARK_GRAY + " » " + ChatColor.RESET + event.getPlayer().getDisplayName() + ": " + event.getMessage());
                        pluginManager.log("StaffChat » " + event.getPlayer().getDisplayName() + ": " + event.getMessage());
                    }
                }
                else {
                    break;
                }
            }
        }
        else{
            if(plugin.getDataFile().getBoolean("mutechatstatus"))
                event.setCancelled(true);
            else
                return;
        }
    }
}
