package net.badbird5907.aetheriacore.bungee.commands.warps;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import static net.md_5.bungee.api.ChatColor.RED;
import static net.md_5.bungee.api.ProxyServer.getInstance;

public class Creative extends Command {
	public Creative() {
		super("creative", "aetheriacore.creative");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (!(sender instanceof ProxiedPlayer)) {
			sender.sendMessage(new ComponentBuilder("This command can only be run by a player!").color(RED).create());
			return;
		}
		ProxiedPlayer player = (ProxiedPlayer) sender;
		if (player.getServer().getInfo().getName().equalsIgnoreCase("creative")) {
			player.sendMessage(new ComponentBuilder("You are already connected Creative!").color(RED).create());
			return;
		}
		ServerInfo target = getInstance().getServerInfo("Creative");
		player.connect(target);
	}
}