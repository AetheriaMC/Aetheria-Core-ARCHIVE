package net.badbird5907.aetheriacore.spigot.commands.utils;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.manager.DebugLogger;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class mutechat implements CommandExecutor {
    AetheriaCore plugin;
    public mutechat(AetheriaCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String s,  String[] args) {
        if(sender.hasPermission(permissionManager.mutechat)){
            if(args.length == 0){
                if(plugin.getDataFile().getBoolean("mutechatstatus")) {
                    try{
                        plugin.getDataFile().set("mutechatstatus", false);
                    } catch (Exception e) {
                        e.printStackTrace();
                        DebugLogger.DebugLog(e.toString());
                    }
                }
                if(!plugin.getDataFile().getBoolean("mutechatstatus")) {
                    try{
                        plugin.getDataFile().set("mutechatstatus", true);
                    } catch (Exception e) {
                        e.printStackTrace();
                        DebugLogger.DebugLog(e.toString());
                    }
                }


            }
        }
        return true;
    }
}
