package net.badbird5907.aetheriacore.spigot.api;

import net.badbird5907.aetheriacore.spigot.commands.hush;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import net.badbird5907.aetheriacore.spigot.manager.pluginManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class StaffChatMessage{
    public static void sendmessage(String string){
        for (Player player : Bukkit.getOnlinePlayers()) {
            if(player.hasPermission(permissionManager.staffchat)){
                if(hush.hush.contains(player.getName())){
                    break;
                }
                else{
                    player.sendMessage(ChatColor.GOLD + "StaffChat" + ChatColor.DARK_GRAY + " » " + ChatColor.RESET + "Alert: " + string);
                    pluginManager.log("StaffChat » " + "Alert: " + string);
                }
            }
            else {
                break;
            }
        }
    }
}
