package net.badbird5907.aetheriacore.spigot.commands;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import net.md_5.bungee.protocol.packet.Chat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class staffchat implements CommandExecutor {
    public static List <String> staffchatToggle = new ArrayList<String>();

    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        if(!(player instanceof Player)){
            player.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "AEC" + ChatColor.DARK_GRAY + "] " + ChatColor.RED + "You must be a player to toggle this. use /qc <text> instead.");
        }
        if(player.hasPermission(permissionManager.staffchat)){
            if(staffchatToggle.contains(player.getName())){
                if(!StaffMode.StaffModeToggle.contains(player.getName())){
                    player.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "AEC" + ChatColor.DARK_GRAY + "] " + ChatColor.GREEN + "StaffChat Turned Off");
                    staffchatToggle.remove(player.getName());
                }
                else{
                    player.sendMessage(ChatColor.RED + "Error: Staff Mode is active. do /sm to disable." + ChatColor.DARK_GRAY + " " + ChatColor.ITALIC + "STAFF_MODE_ON");
                }

            }
            else{
                player.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "AEC" + ChatColor.DARK_GRAY + "] " + ChatColor.GREEN + "StaffChat Turned On");
                staffchatToggle.add(player.getName());
            }
        }
        else{
            permissionManager.permissionMessage("staffchat");
        }
        return true;
    }
}
