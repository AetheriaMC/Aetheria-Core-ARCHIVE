package net.badbird5907.aetheriacore.bungee.listeners;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.event.EventHandler;

import static java.util.Objects.requireNonNull;
import static net.badbird5907.aetheriacore.bungee.util.DataFile.getData;
import static net.badbird5907.aetheriacore.bungee.util.DataFile.saveData;
import static net.badbird5907.aetheriacore.bungee.util.Permission.GLOBAL_LOCKDOWN;

public class LockdownListener implements Listener {
	@EventHandler
	public void Check(ChatEvent e) {
		ProxiedPlayer p = (ProxiedPlayer) e.getSender();
		Configuration datafile = getData("bungeedata");
		if (e.getMessage().equalsIgnoreCase("/lockdown HIGH") && p.hasPermission(GLOBAL_LOCKDOWN.node))
			if (requireNonNull(datafile).getBoolean("Lockdown.enabled")) {
				datafile.set("Lockdown.enabled", false);
				saveData(datafile, "bungeedata");
			} else p.sendMessage(new TextComponent());
	}
}