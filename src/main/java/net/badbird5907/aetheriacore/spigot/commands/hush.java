package net.badbird5907.aetheriacore.spigot.commands;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class hush implements CommandExecutor {
    public static List<String> hush = new ArrayList<String>();
    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        if(player instanceof Player){
            if(player.hasPermission(permissionManager.hush)){
                if(hush.contains(player.getName())){
                    hush.remove(player.getName());
                    player.sendMessage(ChatColor.GREEN + "You can now see the Staff Chat.");
                }
                else{
                    if(StaffMode.StaffModeToggle.contains(player.getName())){
                        player.sendMessage(ChatColor.RED + "Error: Staff Mode is active. do /sm to disable." + ChatColor.DARK_GRAY + " " + ChatColor.ITALIC + "STAFF_MODE_ON");
                    }
                    else{
                        hush.add(player.getName());
                        staffchat.staffchatToggle.remove(player.getName());
                        player.sendMessage(ChatColor.GREEN + "StaffChat Ignored. Do /hush to turn back on or relog.");
                    }
                }

            }
            else{
                permissionManager.permissionMessage("staffchat.hush");
            }

        }
        else{
            player.sendMessage("You Must Be A Player To Execute This Command.");
        }

        return true;
    }
}
