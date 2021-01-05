package net.badbird5907.aetheriacore.bungee.commands.warps;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import static net.md_5.bungee.api.ChatColor.RED;
import static net.md_5.bungee.api.ProxyServer.getInstance;

public class Survival extends Command {
	public Survival() {
		super("survival", "permission.survival");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (!(sender instanceof ProxiedPlayer)) {
			sender.sendMessage(new ComponentBuilder("This command can only be run by a player!").color(RED).create());
			return;
		}
		ProxiedPlayer player = (ProxiedPlayer) sender;
		if (player.getServer().getInfo().getName().equalsIgnoreCase("survival")) {
			player.sendMessage(new ComponentBuilder("You are already connected to Survival!").color(RED).create());
			return;
		}
		ServerInfo target = getInstance().getServerInfo("Survival");
		player.connect(target);
	}
}