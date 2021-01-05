package net.badbird5907.aetheriacore.bungee.api;

import net.badbird5907.aetheriacore.bungee.discord.SendDiscordACM;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;

import static java.util.Objects.requireNonNull;
import static net.badbird5907.aetheriacore.bungee.discord.sendmsg.sendmsg;
import static net.badbird5907.aetheriacore.bungee.util.Config.getData;
import static net.badbird5907.aetheriacore.bungee.util.Messages.getConfig;
import static net.badbird5907.aetheriacore.bungee.util.Permission.ADMIN_CHAT;
import static net.badbird5907.aetheriacore.bungee.util.PlayerHandler.playerwithrank;
import static net.md_5.bungee.api.ChatColor.translateAlternateColorCodes;

public class SendAdminChatMessage {
	public static void Send(ProxiedPlayer p, String message) {
		Configuration config = getConfig("bungeemessages");
		for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers())
			if (staff.hasPermission(ADMIN_CHAT.node))
				staff.sendMessage(new TextComponent(translateAlternateColorCodes('&', config.getString("Messages.ac-format").replaceAll("%message%", message).replaceAll("%player%", playerwithrank(p)).replaceAll("%server%", p.getServer().getInfo().getName()))));
		SendDiscordACM.send(p, message);
	}

	public static void SendCustom(String sender, String server_null_if_none, String message) {
		Configuration config = getConfig("bungeemessages");
		Configuration conf = getData("bungeeconfig");
		for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers())
			if (staff.hasPermission(ADMIN_CHAT.node))
				staff.sendMessage((server_null_if_none == null) ? new TextComponent(translateAlternateColorCodes('&', config.getString("Messages.ac-format").replaceAll("%message%", message).replaceAll("%player%", sender).replaceFirst("(%server%)", "").replaceAll("%server%", ""))) : new TextComponent(translateAlternateColorCodes('&', config.getString("Messages.ac-format").replaceAll("%message%", message).replaceAll("%player%", sender).replaceAll("%server%", server_null_if_none))));
		sendmsg(message, conf.getString("Discord.adminchat"));
	}

	public static void DiscordSend(String sender, String message) {
		for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers())
			staff.sendMessage(new TextComponent(translateAlternateColorCodes('&', requireNonNull(getConfig("bungeemessages")).getString("Messages.discordac").replaceAll("%user%", sender).replaceAll("%message%", message))));
	}
}
