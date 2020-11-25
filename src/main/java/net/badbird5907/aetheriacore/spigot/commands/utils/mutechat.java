package net.badbird5907.aetheriacore.spigot.commands.utils;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.manager.DebugLogger;
import net.badbird5907.aetheriacore.spigot.manager.Permission;
import net.badbird5907.aetheriacore.spigot.setup.Noteblock;
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
        if(sender.hasPermission(Permission.MUTE_CHAT.node)){
            if(args.length == 0){

                if(plugin.getDataFile().getBoolean("mutechatstatus")) {
                    try{
                        plugin.getDataFile().set("mutechatstatus", false);
                        plugin.getDataFile().save(plugin.customConfigFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                        DebugLogger.DebugLog(e.toString());
                    }
                }
                if(!plugin.getDataFile().getBoolean("mutechatstatus")) {
                    try{
                        plugin.getDataFile().set("mutechatstatus", true);
                        plugin.getDataFile().save(plugin.customConfigFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                        DebugLogger.DebugLog(e.toString());
                    }
                }


            }
        }
        return true;
    }
    public static boolean IsChatMuted(){
        if(Noteblock.getInstance().getDataFile().getBoolean("mutechatstatus"))
            return true;
        else
            return false;
    }
}
