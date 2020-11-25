package net.badbird5907.aetheriacore.bungee.commands.util;

import net.badbird5907.aetheriacore.bungee.manager.log;
import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class GlobalBroadcast extends Command {
    public GlobalBroadcast() {
        super("globalbroadcast", Permission.GLOBAL_BROADCAST.node, new String[]{"gbc"});
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            if (p.hasPermission(Permission.GLOBAL_BROADCAST.node)) {
                if (args.length > 0) {
                    StringBuilder msg = new StringBuilder();
                    for (String arg : args)
                        msg.append(arg).append(" ");
                    BaseComponent[] cp = TextComponent.fromLegacyText(ChatColor.DARK_RED + "[" + ChatColor.DARK_AQUA + "Aetheria" + ChatColor.DARK_RED + "] " + ChatColor.RESET + msg.toString());
                    ProxyServer.getInstance().broadcast(cp);
                } else {
                    p.sendMessage(new TextComponent(ChatColor.RED + "Usage: /globalbroadcast <message>"));
                }
            } else {
                p.sendMessage(new TextComponent(log.permissionmessage));
            }
        } else if (args.length > 0) {
            StringBuilder msg = new StringBuilder();
            for (String arg : args)
                msg.append(arg).append(" ");
            BaseComponent[] cp = TextComponent.fromLegacyText(ChatColor.DARK_RED + "[" + ChatColor.DARK_AQUA + "Aetheria" + ChatColor.DARK_RED + "] " + ChatColor.RESET + msg.toString());
            ProxyServer.getInstance().broadcast(cp);
        } else {
            ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(ChatColor.RED + "Usage: /globalbroadcast <message>"));
        }
    }
}
