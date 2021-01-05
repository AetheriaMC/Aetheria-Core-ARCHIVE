package net.badbird5907.aetheriacore.bungee.commands.staff;


import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee.inAc;
import static net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee.inSc;
import static net.badbird5907.aetheriacore.bungee.api.SendStaffChatMessage.Send;
import static net.badbird5907.aetheriacore.bungee.util.Messages.getConfig;
import static net.badbird5907.aetheriacore.bungee.util.Permission.STAFF_CHAT;
import static net.md_5.bungee.api.ChatColor.GREEN;
import static net.md_5.bungee.api.ChatColor.translateAlternateColorCodes;

public class StaffChat extends Command {

	public StaffChat() {
		super("sc", STAFF_CHAT.node, "staffchat");
	}

	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			Configuration config = getConfig("bungeemessages");
			assert config != null;
			if (args.length == 0) if (p.hasPermission(STAFF_CHAT.node)) {
				if (inAc.contains(p.getUniqueId())) {
					inAc.remove(p.getUniqueId());
					p.sendMessage(new TextComponent(GREEN + "You were in Admin chat so Admin chat was toggled off"));
				}
				if (inSc.contains(p.getUniqueId())) {
					p.sendMessage(new TextComponent(translateAlternateColorCodes('&', config.getString("Messages.sc-disabled"))));
					inSc.remove(p.getUniqueId());
				} else {
					inSc.add(p.getUniqueId());
					p.sendMessage(new TextComponent(translateAlternateColorCodes('&', config.getString("Messages.sc-enabled"))));
				}
			} else
				p.sendMessage(new TextComponent(translateAlternateColorCodes('&', config.getString("Messages.no-permission"))));
			else if (p.hasPermission(STAFF_CHAT.node)) Send(p, stream(args).map(arg -> arg + " ").collect(joining()));
			else
				p.sendMessage(new TextComponent(translateAlternateColorCodes('&', config.getString("Messages.no-permission"))));
		}
	}
}