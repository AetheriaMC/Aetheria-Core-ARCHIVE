package net.badbird5907.aetheriacore.spigot.events;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import net.md_5.bungee.protocol.packet.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import net.badbird5907.aetheriacore.spigot.commands.staffchat;

public class onChat implements Listener {
    public void ChatListener (AsyncPlayerChatEvent event){
        if(staffchat.staffchatToggle.contains(event.getPlayer().getName())){
            event.setCancelled(true);
            for (Player player : Bukkit.getOnlinePlayers()) {
                if(player.hasPermission(permissionManager.staffchat)){
                    player.sendMessage(ChatColor.GOLD + "StaffChat" + ChatColor.DARK_GRAY + " » " + ChatColor.RESET + event.getPlayer().getDisplayName() + ": " + event.getMessage());
                }
                else {
                    break;
                }
            }
        }
        else{
            return;
        }
    }
}
