package net.badbird5907.aetheriacore.bungee.discord;

import net.badbird5907.aetheriacore.bungee.util.Config;
import net.badbird5907.aetheriacore.bungee.util.Messages;
import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.badbird5907.aetheriacore.bungee.util.PlayerHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;

import java.awt.*;
import java.util.Date;

public class SendReport {
    public static void send(String sender, String player, String reason, String sender_server, String target_server){
        Configuration config = Config.getData("bungeeconfig");
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Report");
        embed.setColor(Color.RED);
        embed.setDescription("Report by " + sender);
        embed.addField("Player:", player, false);
        embed.addField("Reason:", reason, false);
        embed.addField(player + "'s Server:", target_server, false);
        embed.addField(sender + "'s Server:", sender_server, false);
        embed.setTimestamp(new Date().toInstant());
        MessageEmbed membed = embed.build();
        sendmsg.sendmsg(membed, config.getString("Discord.reports"));
        Configuration messages = Messages.getConfig("bungeemessages");
        for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers()) {
            if (staff.hasPermission(Permission.SEE_REPORT.node)) {
                net.md_5.bungee.api.chat.TextComponent cp = new TextComponent(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.report")
                        .replaceAll("%sender%", PlayerHandler.playerwithrank(BungeeCord.getInstance().getPlayer(sender)))
                        .replaceAll("%player%", PlayerHandler.playerwithrank(BungeeCord.getInstance().getPlayer(player)))
                        .replaceAll("%sender-server%", sender_server)
                        .replaceAll("%target-server%", target_server)));
                cp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click to go to " + player).create()));
                cp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "goto " + player ));
                staff.sendMessage(cp);
            }
        }
    }
}
