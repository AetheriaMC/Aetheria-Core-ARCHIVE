package net.badbird5907.aetheriacore.bungee.commands.warps;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import static net.md_5.bungee.api.ChatColor.RED;

public class Hub extends Command {
	public Hub() {
		super("hub", "aetheriacore.hub");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (!(sender instanceof ProxiedPlayer)) {
			sender.sendMessage(new ComponentBuilder("This command can only be run by a player!").color(RED).create());
			return;
		}
		ProxiedPlayer player = (ProxiedPlayer) sender;
		if (player.getServer().getInfo().getName().equalsIgnoreCase("hub")) {
			player.sendMessage(new ComponentBuilder("You are already connected to the Hub!").color(RED).create());
			return;
		}
		ServerInfo target = ProxyServer.getInstance().getServerInfo("Hub");
		player.connect(target);
	}

}