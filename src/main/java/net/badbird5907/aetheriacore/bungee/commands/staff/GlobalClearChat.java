package net.badbird5907.aetheriacore.bungee.commands.staff;

import net.badbird5907.aetheriacore.bungee.util.Messages;
import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

public class GlobalClearChat extends Command {
    public GlobalClearChat() {
        super("gclearchat", Permission.GLOBAL_CLEAR_CHAT.node, new String[0]);
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            Configuration config = Messages.getConfig("bungeemessages");
            ProxiedPlayer p = (ProxiedPlayer)sender;
            if (args.length == 0)
                if (p.hasPermission(Permission.GLOBAL_CLEAR_CHAT.node)) {
                    for (ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
                        if (!all.hasPermission(Permission.CLEAR_CHAT_BYPASS.node)) {
                            int i = 0;
                            while (i < 150) {
                                all.sendMessage(new TextComponent(""));
                                i++;
                            }
                            continue;
                        }
                        all.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.clearchat-bypass"))));
                    }
                } else {
                    p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.no-permission"))));
                }
        }
    }
}
