package net.badbird5907.aetheriacore.bungee.commands.staff;


import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee.Hush;
import static net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee.*;
import static net.badbird5907.aetheriacore.bungee.manager.log.permissionmessage;
import static net.badbird5907.aetheriacore.bungee.manager.log.prefix;
import static net.badbird5907.aetheriacore.bungee.util.Permission.STAFF_CHAT;
import static net.md_5.bungee.api.ChatColor.*;
import static net.md_5.bungee.api.chat.TextComponent.fromLegacyText;

public class StaffChat extends Command {

	public StaffChat() {
		super("sc", STAFF_CHAT.node, "staffchat");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if (args.length == 0) if (p.hasPermission(STAFF_CHAT.node)) {
				if (AdminChatPlayers.contains(p.getUniqueId())) {
					AdminChatPlayers.remove(p.getUniqueId());
					p.sendMessage(new TextComponent(GREEN + "You had admin chat on so it was toggled off!"));
				}
				if (Hush.contains(p.getUniqueId())) {
					p.sendMessage(new TextComponent(RED + "You have hush currently enabled. please disable it with /hush to turn on Staff Chat"));
					return;
				}
				if (StaffChatPlayers.contains(p.getUniqueId())) {
					p.sendMessage(new TextComponent(prefix + WHITE + "StaffChat turned " + RED + "OFF"));
					StaffChatPlayers.remove(p.getUniqueId());
				} else {
					StaffChatPlayers.add(p.getUniqueId());
					p.sendMessage(new TextComponent(prefix + WHITE + "StaffChat turned " + GREEN + "ON"));
				}
			} else p.sendMessage(new TextComponent(permissionmessage));
			else if (p.hasPermission(STAFF_CHAT.node)) {
				String msg = stream(args).map(arg -> arg + " ").collect(joining());
				if (Hush.contains(p.getUniqueId()))
					p.sendMessage(new TextComponent(RED + "You have hush currently enabled. please disable it with /hush to turn on Staff Chat"));
				else for (ProxiedPlayer staff : ProxyServer.getInstance().getPlayers())
					if (staff.hasPermission(STAFF_CHAT.node)) {
						//BaseComponent[] cp = TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.sc-format").replaceAll("%message%", msg).replaceAll("%player%", p.getName()).replaceAll("%server%", p.getServer().getInfo().getName())));
						if (!Hush.contains(staff.getUniqueId()))
							staff.sendMessage(fromLegacyText(GOLD + "StaffChat" + DARK_GRAY + " » (" + p.getServer().getInfo().getName() + ") " + RESET + p.getName() + ": " + msg));
						else break;
					}
			} else p.sendMessage(new TextComponent(permissionmessage));
		}
	}
}
