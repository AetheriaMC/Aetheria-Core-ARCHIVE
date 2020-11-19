package net.badbird5907.aetheriacore.bungee.commands.util;

import net.badbird5907.aetheriacore.bungee.manager.log;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static net.md_5.bungee.api.ChatColor.*;
import static net.md_5.bungee.api.ProxyServer.getInstance;
import static net.md_5.bungee.api.chat.TextComponent.fromLegacyText;

public class GlobalBroadcast extends Command {
	public GlobalBroadcast() {
		super("ad", "bungee.staff.ad", "advert");
	}

	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if (p.hasPermission("bungee.staff.ad")) if (args.length > 0)
				getInstance().broadcast(fromLegacyText(DARK_RED + "[" + DARK_AQUA + "Aetheria" + DARK_RED + "] " + RESET + stream(args).map(arg -> arg + " ").collect(joining())));
			else p.sendMessage(new TextComponent(RED + "Usage: /globalbroadcast <message>"));
			else p.sendMessage(new TextComponent(log.permissionmessage));
		} else if (args.length > 0)
			getInstance().broadcast(fromLegacyText(DARK_RED + "[" + DARK_AQUA + "Aetheria" + DARK_RED + "] " + RESET + stream(args).map(arg -> arg + " ").collect(joining())));
		else getInstance().getConsole().sendMessage(new TextComponent(RED + "Usage: /globalbroadcast <message>"));
	}
}
