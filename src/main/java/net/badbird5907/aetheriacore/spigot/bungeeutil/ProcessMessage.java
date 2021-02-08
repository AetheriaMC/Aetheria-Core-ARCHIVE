package net.badbird5907.aetheriacore.spigot.bungeeutil;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ProcessMessage {
    public static void process_goto(String p, String t){
        Player p1 = null;
        Player t1 = null;
        Boolean cont;
        try{
            p1 = Bukkit.getPlayerExact(p);
            t1 = Bukkit.getPlayerExact(t);
            cont = true;
        } catch (Exception e) {
            cont = false;
            e.printStackTrace();
        }
        if(cont){
            p1.teleport(t1);
        }
    }
}
