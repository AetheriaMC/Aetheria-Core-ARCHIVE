package net.badbird5907.aetheriacore.spigot.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class getUUID implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        Player target = Bukkit.getPlayerExact(args[0]);
        if(target instanceof Player){
            player.sendMessage(ChatColor.GREEN + target.getDisplayName() + "'s UUID is:" + ChatColor.BLUE + target.getUniqueId());

        }
        else{
            player.sendMessage(ChatColor.RED + "Error: Invalid Arguments. Use: /getuuid <Player>" + ChatColor.DARK_GRAY + " " + ChatColor.ITALIC + "INVALID_ARGUMENTS");
        }
        return true;
    }
}
