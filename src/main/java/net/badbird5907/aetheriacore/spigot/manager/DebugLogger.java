package net.badbird5907.aetheriacore.spigot.manager;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class DebugLogger {
    static AetheriaCore plugin;
    public DebugLogger(AetheriaCore plugin) {
        this.plugin = plugin;
    }

    public static ArrayList<UUID> Debugplayers = new ArrayList<UUID>();
    public static void DebugLog(String string){
        for(Player p : Bukkit.getOnlinePlayers()){
            if(Debugplayers.contains(p.getUniqueId())){
                p.sendMessage(PluginManager.Debugprefix + string);
            }
            else{
                break;
            }
        }
        PluginManager.log(PluginManager.Debugprefix + string);
    }

}
