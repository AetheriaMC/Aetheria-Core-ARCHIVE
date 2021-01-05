package net.badbird5907.aetheriacore.bungee.commands.staff;


import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

import static net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee.inCSpy;
import static net.badbird5907.aetheriacore.bungee.util.Messages.getConfig;
import static net.badbird5907.aetheriacore.bungee.util.Permission.COMMAND_SPY;
import static net.md_5.bungee.api.ChatColor.translateAlternateColorCodes;

public class CSpy extends Command {
	public CSpy() {
		super("cspy", COMMAND_SPY.node, "commandspy");
	}

	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			Configuration config = getConfig("bungeemessages");
			assert config != null;
			if (args.length == 0) if (p.hasPermission(COMMAND_SPY.node)) if (inCSpy.contains(p.getUniqueId())) {
				inCSpy.remove(p.getUniqueId());
				p.sendMessage(new TextComponent(translateAlternateColorCodes('&', config.getString("Messages.cspy-disabled"))));
			} else {
				inCSpy.add(p.getUniqueId());
				p.sendMessage(new TextComponent(translateAlternateColorCodes('&', config.getString("Messages.cspy-enabled"))));
			}
			else
				p.sendMessage(new TextComponent(translateAlternateColorCodes('&', config.getString("Messages.no-permission"))));
		}
	}
}

