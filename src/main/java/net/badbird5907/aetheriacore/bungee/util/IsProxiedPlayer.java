package net.badbird5907.aetheriacore.bungee.util;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class IsProxiedPlayer {
    public static boolean check(String player){
        ProxiedPlayer player1 = null;
        boolean a;
        try{
            player1 = BungeeCord.getInstance().getPlayer(player);
            a = true;
        } catch (Exception e) {
            a=false;
        }
        if(a)
            return true;
        else return false;
    }
}
