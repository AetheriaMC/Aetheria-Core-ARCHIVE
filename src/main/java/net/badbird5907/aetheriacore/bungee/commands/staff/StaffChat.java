package net.badbird5907.aetheriacore.bungee.commands.staff;

import net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee;
import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.badbird5907.aetheriacore.bungee.util.PlayerHandler;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

public class StaffChat extends Command {
    public StaffChat() {
        super("sc", Permission.STAFF_CHAT.node, new String[] { "staffchat" });
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer)sender;
            Configuration config = AetheriaCoreBungee.getInstance().getConfig("bungeeconfig");
            if (args.length == 0) {
                if (p.hasPermission(Permission.STAFF_CHAT.node)) {
                    if (AetheriaCoreBungee.inSc.contains(p.getUniqueId())) {
                        p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.sc-disabled"))));
                        AetheriaCoreBungee.inSc.remove(p.getUniqueId());
                    } else {
                        AetheriaCoreBungee.inSc.add(p.getUniqueId());
                        p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.sc-enabled"))));
                    }
                } else {
                    p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.no-permission"))));
                }
            } else if (p.hasPermission("bungee.staff")) {
                String msg = "";
                for (int i = 0; i < args.length; i++)
                    msg = msg + args[i] + " ";
                for (ProxiedPlayer staff : ProxyServer.getInstance().getPlayers()) {
                    if (staff.hasPermission("bungee.staff")) {
                        BaseComponent[] cp = TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.sc-format").replaceAll("%message%", msg).replaceAll("%player%", PlayerHandler.playerwithrank(p)).replaceAll("%server%", p.getServer().getInfo().getName())));
                        staff.sendMessage(cp);
                    }
                }
            } else {
                p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.no-permission"))));
            }
        }
    }
}
