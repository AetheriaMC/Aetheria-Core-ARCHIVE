package net.badbird5907.aetheriacore.spigot.commands.management;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.manager.DebugLogger;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import sun.security.util.Debug;

public class togglePvp implements CommandExecutor {
    AetheriaCore plugin;
    public togglePvp(AetheriaCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        if(player.hasPermission(permissionManager.togglepvp)){
            if (args.length == 0){
                if(plugin.getDataFile().getBoolean("pvp")){
                    try{
                        plugin.getDataFile().set("pvp", false);
                    } catch (Exception e) {
                        e.printStackTrace();
                        DebugLogger.DebugLog("Error: Could not change pvp value of data file. Stacktrace:");
                        DebugLogger.DebugLog(e.getStackTrace().toString());
                    }
                }
                else{

                }
            }
        }
        return true;
    }
}
