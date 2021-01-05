package net.badbird5907.aetheriacore.bungee.listeners;

import net.badbird5907.aetheriacore.bungee.api.SendAdminChatMessage;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.event.EventHandler;

import static java.util.Objects.requireNonNull;
import static net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee.*;
import static net.badbird5907.aetheriacore.bungee.api.SendStaffChatMessage.Send;
import static net.badbird5907.aetheriacore.bungee.util.Messages.getConfig;
import static net.badbird5907.aetheriacore.bungee.util.Permission.*;
import static net.badbird5907.aetheriacore.bungee.util.PlayerHandler.playerwithrank;
import static net.md_5.bungee.api.ChatColor.translateAlternateColorCodes;

public class events implements Listener {
	@EventHandler
	public void onChat(ChatEvent e) {
		if (!(e.getSender() instanceof ProxiedPlayer) || e.getMessage().startsWith("/")) return;
		ProxiedPlayer p = (ProxiedPlayer) e.getSender();
		Configuration config = getConfig("bungeemessages");
		if (inSc.contains(p.getUniqueId())) {
			e.setCancelled(true);
			Send(p, e.getMessage());
		}
		if (inAc.contains(p.getUniqueId())) {
			e.setCancelled(true);
			SendAdminChatMessage.Send(p, e.getMessage());
		}
		if (e.getMessage().startsWith("#")) {
			e.setCancelled(true);
			Send(p, e.getMessage().replaceFirst("#", ""));
		}
	}

	@EventHandler
	public void onChat2(ChatEvent e) {
		ProxiedPlayer p = (ProxiedPlayer) e.getSender();
		Configuration config = getConfig("bungeemessages");
		if (p.hasPermission(COMMAND_SPY_BYPASS.node)) return;
		if (e.getMessage().startsWith("/") && !e.getMessage().startsWith("/commandspy") && !e.getMessage().startsWith("/cspy")) {
			for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers())
				if (inCSpy.contains(staff.getUniqueId()))
					staff.sendMessage(new TextComponent(translateAlternateColorCodes('&', requireNonNull(config).getString("Messages.cspy-format")).replaceAll("%server%", p.getServer().getInfo().getName()).replaceAll("%user%", p.getName()).replaceAll("%command%", e.getMessage()).replaceAll("%player%", playerwithrank(p))));
			BungeeCord.getInstance().getConsole().sendMessage(new TextComponent(translateAlternateColorCodes('&', requireNonNull(config).getString("Messages.cspy-format")).replaceAll("%server%", p.getServer().getInfo().getName()).replaceAll("%user%", p.getName()).replaceAll("%command%", e.getMessage()).replaceAll("%player%", playerwithrank(p))));
		}
	}

	@EventHandler
	public void onSwitch(ServerSwitchEvent e) {
		ProxiedPlayer p = e.getPlayer();
		Configuration config = getConfig("bungeemessages");
		if (p.hasPermission(BROADCAST_SWITCH.node) && requireNonNull(config).getBoolean("Config.enable-switch-messages"))
			for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers())
				if (staff.hasPermission(STAFF_CHAT.node) && e.getFrom() != null)
					staff.sendMessage(new TextComponent(translateAlternateColorCodes('&', config.getString("Messages.staff-switch-server").replaceAll("%from%", e.getFrom().getName().toString()).replaceAll("%to%", e.getPlayer().getServer().getInfo().getName()).replaceAll("%player%", playerwithrank(p)))));
	}
}