package net.badbird5907.aetheriacore.bungee.util;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class SendToServer {
    public static void send(ProxiedPlayer p, String server){
        ServerInfo s = BungeeCord.getInstance().getServerInfo(server);
        TextComponent message = new TextComponent();
        message.setText(ChatColor.DARK_GRAY + "Sending you to " + s.getName() + ".");
        if(p.hasPermission("bungeecord.command.server")) {
            message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GRAY + "Server: " + s.getName() + "\n" + ChatColor.GREEN + "Click to connect!").create()));
            message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "server " + s.getName()));
        }
        else
            message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GRAY + "Server: " + s.getName()).create()));
        p.sendMessage(message);
        p.connect(s);
    }
}
