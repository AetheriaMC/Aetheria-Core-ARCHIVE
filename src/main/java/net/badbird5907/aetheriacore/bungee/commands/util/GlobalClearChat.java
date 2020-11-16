package net.badbird5907.aetheriacore.bungee.commands.util;

import net.badbird5907.aetheriacore.bungee.manager.log;
import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.badbird5907.aetheriacore.bungee.util.PlayerHandler;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class GlobalClearChat extends Command {
    public GlobalClearChat() {
        super("globalclearchat",  Permission.STAFF_CHAT.node, new String[] { "gcc" });
    }


    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer)sender;
            if (args.length == 0) {
                if (p.hasPermission(Permission.GLOBAL_CLEAR_CHAT.node)) {
                    for (ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
                        if (!all.hasPermission(Permission.CLEAR_CHAT_BYPASS.node)) {
                            int i = 0;
                            while (i < 150) {
                                all.sendMessage(new TextComponent(""));
                                i++;
                            }
                            all.sendMessage(new TextComponent(ChatColor.GREEN + "Chat was cleared by " + PlayerHandler.playerwithrank(p)));
                            continue;
                        }
                        all.sendMessage(new TextComponent(ChatColor.GREEN + "Chat was cleared by " + PlayerHandler.playerwithrank(p) + ChatColor.GREEN + " but your are immune."));
                    }
                } else {
                    p.sendMessage(new TextComponent(log.permissionmessage));
                }
            }
        }
    }
}