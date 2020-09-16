package net.badbird5907.aetheriacore.spigot.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;


public class staffchat implements CommandExecutor {
    public static List <String> staffchatToggle = new ArrayList<String>();

    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        if(player.hasPermission("aetheriacore.staffchat")){
            if(staffchatToggle.contains(player.getName())){
                player.sendMessage(ChatColor.GREEN + "StaffChat Turned Off");
                staffchatToggle.remove(player.getName());
            }
            else{
                player.sendMessage(ChatColor.GREEN + "StaffChat Turned On");
                staffchatToggle.add(player.getName());
            }
        }
        return true;
    }
}
