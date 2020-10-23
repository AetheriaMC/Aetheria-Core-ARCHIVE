package net.badbird5907.aetheriacore.spigot.commands.management;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class togglePvp implements CommandExecutor {
    AetheriaCore plugin;
    public togglePvp(AetheriaCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        if(player.hasPermission(permissionManager.togglepvp)){
            if(plugin.getConfig().getBoolean("pvp")){
                plugin.getConfig().set("pvp", false);
                plugin.saveConfig();
                plugin.reloadConfig();
                player.sendMessage("PVP off");
            }
            else{
                plugin.getConfig().set("pvp", true);
                plugin.saveConfig();
                plugin.reloadConfig();
                player.sendMessage("PVP on");
            }
        }
        return true;
    }
}
