package net.badbird5907.aetheriacore.bungee.api;

import net.badbird5907.aetheriacore.bungee.discord.SendDiscordSCM;
import net.badbird5907.aetheriacore.bungee.discord.sendmsg;
import net.badbird5907.aetheriacore.bungee.util.Config;
import net.badbird5907.aetheriacore.bungee.util.Messages;
import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.badbird5907.aetheriacore.bungee.util.PlayerHandler;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;
import org.w3c.dom.Text;

public class SendStaffChatMessage {
    public static void Send(ProxiedPlayer p, String message){
        Configuration config = Messages.getConfig("bungeemessages");
        for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers()) {
            if (staff.hasPermission(Permission.STAFF_CHAT.node)) {
                TextComponent cp = new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.sc-format")
                        .replaceAll("%message%", message)
                        .replaceAll("%player%", PlayerHandler.playerwithrank(p))
                        .replaceAll("%server%", p.getServer().getInfo().getName())));
                staff.sendMessage(cp);
            }
        }
        SendDiscordSCM.send(p, message);
    }
    public static void SendCustom(String sender,String server_null_if_none ,String message, Boolean discord){
        Configuration config = Messages.getConfig("bungeemessages");
        for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers()) {
            if (staff.hasPermission(Permission.STAFF_CHAT.node)) {
                if(server_null_if_none == null){
                    TextComponent cp = new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.sc-format")
                            .replaceAll("%message%", message)
                            .replaceAll("%player%", sender)
                            .replace("(%server%)", "")
                            .replaceFirst("%server%", "")
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
        Configuration conf = Config.getData("bungeeconfig");
        if(discord)
            sendmsg.sendmsg(message, conf.getString("Discord.staffchat"));
    }
    public static void DiscordSend(String sender, String message){
        Configuration config = Messages.getConfig("bungeemessages");
        for(ProxiedPlayer staff : BungeeCord.getInstance().getPlayers()){
            TextComponent textComponent = new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.discordsc")
                    .replaceAll("%user%", sender)
                    .replaceAll("%message%", message)
            ));
            staff.sendMessage(textComponent);
        }
    }
}
