package net.badbird5907.aetheriacore.bungee.commands.staff;

import net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee;
import net.badbird5907.aetheriacore.bungee.api.SendAdminChatMessage;
import net.badbird5907.aetheriacore.bungee.util.Messages;
import net.badbird5907.aetheriacore.bungee.util.Permission;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee.Hush;
import static net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee.*;
import static net.badbird5907.aetheriacore.bungee.manager.log.permissionmessage;
import static net.badbird5907.aetheriacore.bungee.manager.log.prefix;
import static net.badbird5907.aetheriacore.bungee.util.Permission.ADMIN_CHAT;
import static net.badbird5907.aetheriacore.bungee.util.Permission.STAFF_CHAT;
import static net.md_5.bungee.api.ChatColor.*;
import static net.md_5.bungee.api.chat.TextComponent.fromLegacyText;

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

	public AdminChat() {
		super("ac", STAFF_CHAT.node, "adminchat");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if (args.length == 0) if (p.hasPermission(ADMIN_CHAT.node)) {
				if (StaffChatPlayers.contains(p.getUniqueId())) {
					StaffChatPlayers.remove(p.getUniqueId());
					p.sendMessage(new TextComponent(GREEN + "You had staff chat on so it was toggled off!"));
				}
				if (Hush.contains(p.getUniqueId())) {
					p.sendMessage(new TextComponent(RED + "You have hush currently enabled. please disable it with /hush to turn on Admin Chat"));
					return;
				}
				if (AdminChatPlayers.contains(p.getUniqueId())) {
					p.sendMessage(new TextComponent(prefix + WHITE + "AdminChat turned " + RED + "OFF"));
					AdminChatPlayers.remove(p.getUniqueId());
				} else {
					AdminChatPlayers.add(p.getUniqueId());
					p.sendMessage(new TextComponent(prefix + WHITE + "AdminChat turned " + GREEN + "ON"));
				}
			} else p.sendMessage(new TextComponent(permissionmessage));
			else if (p.hasPermission(ADMIN_CHAT.node)) {
				String msg = stream(args).map(arg -> arg + " ").collect(joining());
				if (Hush.contains(p.getUniqueId()))
					p.sendMessage(new TextComponent(RED + "You have hush currently enabled. please disable it with /hush to turn on Admin Chat"));
				else {
					for (ProxiedPlayer staff : ProxyServer.getInstance().getPlayers()) {
						if (staff.hasPermission(ADMIN_CHAT.node)) {
							//BaseComponent[] cp = TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.sc-format").replaceAll("%message%", msg).replaceAll("%player%", p.getName()).replaceAll("%server%", p.getServer().getInfo().getName())));
							BaseComponent[] scm = fromLegacyText(RED + "Admin Chat" + DARK_GRAY + " » (" + p.getServer().getInfo().getName() + ") " + RESET + p.getName() + ": " + msg);
							if (!Hush.contains(staff.getUniqueId())) staff.sendMessage(scm);
							else break;
						}
					}
				}
			} else p.sendMessage(new TextComponent(permissionmessage));
		}
	}
}
