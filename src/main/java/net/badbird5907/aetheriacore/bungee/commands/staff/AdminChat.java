package net.badbird5907.aetheriacore.bungee.commands.staff;

import net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee;
import net.badbird5907.aetheriacore.bungee.api.SendAdminChatMessage;
import net.badbird5907.aetheriacore.bungee.util.Messages;
import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.badbird5907.aetheriacore.bungee.util.PlayerHandler;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

public class AdminChat extends Command {
    public AdminChat() {
        super("adminchat", Permission.ADMIN_CHAT.node, new String[] { "ac" });
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer)sender;
            Configuration config = Messages.getConfig("bungeemessages");
            if (args.length == 0) {
                if (p.hasPermission(Permission.ADMIN_CHAT.node)) {
                    if(AetheriaCoreBungee.inSc.contains(p.getUniqueId())){
                        AetheriaCoreBungee.inSc.remove(p.getUniqueId());
                        p.sendMessage(new TextComponent(
                                ChatColor.GREEN + "You were in staffchat so staff chat was toggled off"
                        ));
                    }
                    if (AetheriaCoreBungee.inAc.contains(p.getUniqueId())) {
                        p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.ac-disabled"))));
                        AetheriaCoreBungee.inAc.remove(p.getUniqueId());
                    } else {
                        AetheriaCoreBungee.inAc.add(p.getUniqueId());
                        p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.ac-enabled"))));
                    }
                } else {
                    p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.no-permission"))));
                }
            } else if (p.hasPermission(Permission.ADMIN_CHAT.node)) {
                String msg = "";
                for (int i = 0; i < args.length; i++)
                    msg = msg + args[i] + " ";
                SendAdminChatMessage.Send(p, msg);
            } else {
                p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.no-permission"))));
            }
        }
    }
}
