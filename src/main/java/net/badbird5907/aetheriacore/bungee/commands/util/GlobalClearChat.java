package net.badbird5907.aetheriacore.bungee.commands.util;

import net.badbird5907.aetheriacore.bungee.manager.log;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import static java.util.stream.IntStream.range;
import static net.badbird5907.aetheriacore.bungee.util.Permission.*;
import static net.badbird5907.aetheriacore.bungee.util.PlayerHandler.playerwithrank;
import static net.md_5.bungee.api.ChatColor.GREEN;

public class GlobalClearChat extends Command {
	public GlobalClearChat() {
		super("globalclearchat", STAFF_CHAT.node, "gcc");
	}


	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if (args.length == 0) if (p.hasPermission(GLOBAL_CLEAR_CHAT.node))
				for (ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
					if (!all.hasPermission(CLEAR_CHAT_BYPASS.node)) {
						range(0, 150).mapToObj(i -> new TextComponent("")).forEach(all::sendMessage);
						all.sendMessage(new TextComponent(GREEN + "Chat was cleared by " + playerwithrank(p)));
						continue;
					}
					all.sendMessage(new TextComponent(GREEN + "Chat was cleared by " + playerwithrank(p) + GREEN + " but your are immune."));
				}
			else p.sendMessage(new TextComponent(log.permissionmessage));
		}
	}
}