package net.badbird5907.aetheriacore.spigot.essentialsreplacement.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class heal implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (sender.hasPermission("aetheriacore.heal")){
            Player target = Bukkit.getPlayerExact(args[0]);
            if(args.length == 0){
                player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
                player.setFireTicks(0);
            }
            else{
                if (target instanceof Player){
                    target.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
                    player.setFireTicks(0);
                }
                else {
                    player.sendMessage(ChatColor.RED + "ERROR: Usage: /heal <Player>");
                }
            }
        }
        else{
            player.sendMessage(ChatColor.RED + "You don't have the required permission node 'aetheriacore.heal' to execute this command.");
        }
        return true;
    }
}
