package net.badbird5907.aetheriacore.spigot.commands.fun;

import net.badbird5907.aetheriacore.spigot.manager.Permission;
import net.badbird5907.aetheriacore.spigot.util.GetPlayerPing;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PingWars implements CommandExecutor {
    private static HashMap<Player, Integer> allPings = new HashMap<Player, Integer>();
    @Override
    public boolean onCommand( CommandSender sender,  Command command,  String s,  String[] args) {
        if(sender.hasPermission(Permission.PING_WARS.node)) {
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("low") || args[0].equalsIgnoreCase("lowest")){
                    for(Player p : Bukkit.getOnlinePlayers()){
                        int ping = GetPlayerPing.getPing(p);
                        allPings.put(p, ping);
                    }
                }
            }
        }
        return true;
    }
}
