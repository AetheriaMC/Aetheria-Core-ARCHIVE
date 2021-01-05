package net.badbird5907.aetheriacore.bungee.commands.staff;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

import static java.util.stream.IntStream.range;
import static net.badbird5907.aetheriacore.bungee.util.Messages.getConfig;
import static net.badbird5907.aetheriacore.bungee.util.Permission.CLEAR_CHAT_BYPASS;
import static net.badbird5907.aetheriacore.bungee.util.Permission.GLOBAL_CLEAR_CHAT;
import static net.md_5.bungee.api.ChatColor.translateAlternateColorCodes;
import static net.md_5.bungee.api.ProxyServer.getInstance;

public class GlobalClearChat extends Command {
	public GlobalClearChat() {
		super("gclearchat", GLOBAL_CLEAR_CHAT.node, new String[0]);
	}

	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof ProxiedPlayer) {
			Configuration config = getConfig("bungeemessages");
			assert config != null;
			ProxiedPlayer p = (ProxiedPlayer) sender;
			if (args.length == 0)
				if (p.hasPermission(GLOBAL_CLEAR_CHAT.node)) {
					getInstance().getPlayers().forEach(all -> {
						if (!all.hasPermission(CLEAR_CHAT_BYPASS.node)) {
							range(0, 150).mapToObj(i -> new TextComponent("")).forEach(all::sendMessage);
							return;
						}
						all.sendMessage(new TextComponent(translateAlternateColorCodes('&', config.getString("Messages.clearchat-bypass"))));
					});
				} else
					p.sendMessage(new TextComponent(translateAlternateColorCodes('&', config.getString("Messages.no-permission"))));
		}
	}
}