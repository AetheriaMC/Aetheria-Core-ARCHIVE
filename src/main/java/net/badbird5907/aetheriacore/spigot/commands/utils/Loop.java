package net.badbird5907.aetheriacore.spigot.commands.utils;

import net.badbird5907.aetheriacore.spigot.manager.DebugLogger;
import net.badbird5907.aetheriacore.spigot.manager.Permission;
import net.badbird5907.aetheriacore.spigot.util.IsInt;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Loop implements CommandExecutor {
    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String s,  String[] args) {
        DebugLogger.DebugLog("Args: " + args.toString());
        if(sender instanceof Player){
            Player player = Bukkit.getPlayerExact(sender.getName());
            if(sender.hasPermission(Permission.LOOP.node)){
                DebugLogger.DebugLog("a1");
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < args.length; i++){
                    sb.append(args[i]).append(" ");
                }
                DebugLogger.DebugLog("a2");
                String allArgs = sb.toString().trim();
                DebugLogger.DebugLog("a3");
                DebugLogger.DebugLog("Args length is " + args.length);
                if(args.length < 1){
                    DebugLogger.DebugLog("a4");
                    if(IsInt.Check(args[0])) {
                        DebugLogger.DebugLog("a5");
                        int num = Integer.parseInt(args[1]);
                        DebugLogger.DebugLog("a6");
                        for(int i = 0; i < num; ++i) {
                            Bukkit.dispatchCommand(player, allArgs);
                        }

                    }
                }
            }
        }
        else{
            if(sender.hasPermission(Permission.LOOP.node)){
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < args.length; i++){
                    sb.append(args[i]).append(" ");
                }
                String allArgs = sb.toString().trim();
                if(args.length < 1){
                    if(IsInt.Check(args[0])) {
                        int num = Integer.parseInt(args[1]);
                        for(int i = 0; i < num; ++i) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), allArgs);
                        }

                    }
                }
            }
        }
        return true;
    }
}
