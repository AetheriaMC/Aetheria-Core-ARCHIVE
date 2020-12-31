package net.badbird5907.aetheriacore.bungee.events;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import static net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee.inCSpy;
import static net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee.Hush;
import static net.badbird5907.aetheriacore.bungee.util.Permission.COMMAND_SPY_BYPASS;
import static net.md_5.bungee.api.ChatColor.*;

public class CommandListener implements Listener {
	@EventHandler
	public void onChat2(ChatEvent e) {
		ProxiedPlayer p = (ProxiedPlayer) e.getSender();
		if (p.hasPermission(COMMAND_SPY_BYPASS.node)) return;
		if (e.getMessage().startsWith("/")) for (ProxiedPlayer staff : ProxyServer.getInstance().getPlayers())
			if (inCSpy.contains(staff.getUniqueId())) {
				//staff.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.cspy-format")).replaceAll("%server%", p.getServer().getInfo().getName()).replaceAll("%player%", p.getName()).replaceAll("%command%", e.getMessage())));
				if (Hush.contains(staff.getUniqueId())) break;
				else
					staff.sendMessage(new TextComponent(GREEN + "CommandSpy" + DARK_GRAY + " » (" + p.getServer().getInfo().getName() + ") " + BLUE + p.getName() + WHITE + ": " + e.getMessage()));
			}
	}
}
