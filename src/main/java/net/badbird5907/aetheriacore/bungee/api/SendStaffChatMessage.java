package net.badbird5907.aetheriacore.bungee.api;

import net.badbird5907.aetheriacore.bungee.util.Messages;
import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.badbird5907.aetheriacore.bungee.util.PlayerHandler;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;

public class SendStaffChatMessage {
    public static void Send(ProxiedPlayer p, String message){
        Configuration config = Messages.getConfig("bungeeconfig");
        for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers()) {
            if (staff.hasPermission(Permission.STAFF_CHAT.node)) {
                TextComponent cp = new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.sc-format")
                        .replaceAll("%message%", message)
                        .replaceAll("%player%", PlayerHandler.playerwithrank(p))
                        .replaceAll("%server%", p.getServer().getInfo().getName())));
                staff.sendMessage(cp);
            }
        }
    }
    public static void SendCustom(String sender,String server_null_if_none ,String message){
        Configuration config = Messages.getConfig("bungeeconfig");
        for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers()) {
            if (staff.hasPermission(Permission.STAFF_CHAT.node)) {
                if(server_null_if_none == null){
                    TextComponent cp = new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.sc-format")
                            .replaceAll("%message%", message)
                            .replaceAll("%player%", sender)
                            .replaceFirst("(%server%)", "")
                            .replaceAll("%server%", ""))
                    );
                    staff.sendMessage(cp);
                }
                else{
                    TextComponent cp = new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.sc-format")
                            .replaceAll("%message%", message)
                            .replaceAll("%player%", sender)
                            .replaceAll("%server%", server_null_if_none)));
                    staff.sendMessage(cp);
                }
            }
        }
    }
}
