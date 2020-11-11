package net.badbird5907.aetheriacore.spigot.util;

import net.badbird5907.aetheriacore.spigot.manager.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class KickAllNonStaff {
    public static void KickAll(String reason){
        for(Player p : Bukkit.getOnlinePlayers()){
            if(!p.hasPermission(Permission.STAFF.node))
                p.kickPlayer(reason);
            else
                break;
        }
    }
}
