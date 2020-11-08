package net.badbird5907.aetheriacore.spigot.essentialsreplacement.commands;

import net.badbird5907.aetheriacore.spigot.manager.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;

public class heal implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (sender.hasPermission(Permission.HEAL.node)){
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
            player.sendMessage(permissionManager.PermissionMessage);
        }
        return true;
    }
}
