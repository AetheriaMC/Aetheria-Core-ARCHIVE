package net.badbird5907.aetheriacore.bungee.features.features;

import net.badbird5907.aetheriacore.bungee.util.IsProxiedPlayer;
import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bukkit.ChatColor;

public class Message {
    public static void processMessage(String sender, String reciever, String msg){
        ProxiedPlayer s = null;
        ProxiedPlayer r = null;
        boolean c;
        if(IsProxiedPlayer.check(reciever))
            s = BungeeCord.getInstance().getPlayer(sender);
        if(IsProxiedPlayer.check(reciever))
            r = BungeeCord.getInstance().getPlayer(reciever);
        if(s == null || r == null)
            c = false;
        String format = ChatColor.AQUA + "From " + ChatColor.RESET + "%sender%: %message%";
        final String message = format.replaceFirst("%sender%", sender)
                .replaceFirst("%message%", FigureOutColors(sender, msg));
    }
    public static String FigureOutColors(String sender, String message){
        ProxiedPlayer player = null;
        boolean c;
        try{
            player = BungeeCord.getInstance().getPlayer(sender);
            c =true;
        } catch (Exception e) {
            c=false;
        }
        if(c){
            if(player.hasPermission(Permission.MSG_COLORS.node)){
                return ChatColor.translateAlternateColorCodes('&', message);
            }else return ChatColor.stripColor(message);
        }else return ChatColor.stripColor(message);
    }
}
