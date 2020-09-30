package net.badbird5907.aetheriacore.spigot.commands.trolls;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KillAll implements CommandExecutor {
    @Override
    public boolean onCommand( CommandSender sender, Command command, String s, String[] args) {
        if(sender.hasPermission(permissionManager.KillAll)){
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.setHealth(0);
            }
            }
        else{
            sender.sendMessage(permissionManager.PermissionMessage);
        }

        return true;
    }
}
