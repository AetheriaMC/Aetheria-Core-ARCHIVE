package net.badbird5907.aetheriacore.bungee.listeners;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.event.EventHandler;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Stream.of;
import static net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee.inCSpy;
import static net.badbird5907.aetheriacore.bungee.util.DataFile.getData;
import static net.badbird5907.aetheriacore.bungee.util.DataFile.saveData;
import static net.badbird5907.aetheriacore.bungee.util.Permission.COMMAND_SPY_BYPASS;
import static net.badbird5907.aetheriacore.bungee.util.Permission.GLOBAL_LOCKDOWN;
import static net.badbird5907.aetheriacore.bungee.util.PlayerHandler.playerwithrank;
import static net.md_5.bungee.api.ChatColor.translateAlternateColorCodes;

public class CommandListener implements Listener {
	@EventHandler
	public void Check(ChatEvent e) {
		ProxiedPlayer p = (ProxiedPlayer) e.getSender();
		Configuration datafile = getData("bungeedata");
		if (e.getMessage().equalsIgnoreCase("/lockdown HIGH"))
			if (p.hasPermission(GLOBAL_LOCKDOWN.node)) if (requireNonNull(datafile).getBoolean("Lockdown.enabled")) {
				datafile.set("Lockdown.enabled", false);
				saveData(datafile, "bungeedata");
			} else p.sendMessage(new TextComponent());
		if (e.getMessage().startsWith("/") && !e.getMessage().startsWith("/commandspy") && !e.getMessage().startsWith("/cspy"))
			if (p.hasPermission(COMMAND_SPY_BYPASS.node)) {
				for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers())
					if (inCSpy.contains(staff.getUniqueId()))
						staff.sendMessage(new TextComponent(translateAlternateColorCodes('&', requireNonNull(datafile).getString("Messages.cspy-format")).replaceAll("%server%", p.getServer().getInfo().getName()).replaceAll("%user%", p.getName()).replaceAll("%command%", e.getMessage()).replaceAll("%player%", playerwithrank(p))));
				BungeeCord.getInstance().getConsole().sendMessage(new TextComponent(translateAlternateColorCodes('&', requireNonNull(datafile).getString("Messages.cspy-format")).replaceAll("%server%", p.getServer().getInfo().getName()).replaceAll("%user%", p.getName()).replaceAll("%command%", e.getMessage()).replaceAll("%player%", playerwithrank(p))));
			}
		if (of("/plugins", "/pl", "/bukkit:plugins").anyMatch(s -> e.getMessage().equalsIgnoreCase(s)))
			((ProxiedPlayer) e.getSender()).sendMessage(new TextComponent(translateAlternateColorCodes('&', requireNonNull(datafile).getString("Messages.plugins"))));
	}
}