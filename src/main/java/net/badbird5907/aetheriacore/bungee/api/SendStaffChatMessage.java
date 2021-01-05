package net.badbird5907.aetheriacore.bungee.api;

import net.badbird5907.aetheriacore.bungee.discord.SendDiscordSCM;
import net.badbird5907.aetheriacore.bungee.discord.sendmsg;
import net.badbird5907.aetheriacore.bungee.util.Config;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;

import static net.badbird5907.aetheriacore.bungee.util.Messages.getConfig;
import static net.badbird5907.aetheriacore.bungee.util.Permission.STAFF_CHAT;
import static net.badbird5907.aetheriacore.bungee.util.PlayerHandler.playerwithrank;
import static net.md_5.bungee.api.ChatColor.translateAlternateColorCodes;

public class SendStaffChatMessage {
	public static void Send(ProxiedPlayer p, String message) {
		Configuration config = getConfig("bungeemessages");
		for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers())
			if (staff.hasPermission(STAFF_CHAT.node))
				staff.sendMessage(new TextComponent(translateAlternateColorCodes('&', config.getString("Messages.sc-format").replaceAll("%message%", message).replaceAll("%player%", playerwithrank(p)).replaceAll("%server%", p.getServer().getInfo().getName()))));
		SendDiscordSCM.send(p, message);
	}

	public static void SendCustom(String sender, String server_null_if_none, String message, Boolean discord) {
		Configuration config = getConfig("bungeemessages");
		for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers())
			if (staff.hasPermission(STAFF_CHAT.node))
				staff.sendMessage(new TextComponent(translateAlternateColorCodes('&', (server_null_if_none == null) ? config.getString("Messages.sc-format").replaceAll("%message%", message).replaceAll("%player%", sender).replace("(%server%)", "").replaceFirst("%server%", "").replaceAll("%server%", "") : config.getString("Messages.sc-format").replaceAll("%message%", message).replaceAll("%player%", sender).replaceAll("%server%", server_null_if_none))));
		Configuration conf = Config.getData("bungeeconfig");
		if (discord) sendmsg.sendmsg(message, conf.getString("Discord.staffchat"));
	}

	public static void DiscordSend(String sender, String message) {
		Configuration config = getConfig("bungeemessages");
		for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers())
			staff.sendMessage(new TextComponent(translateAlternateColorCodes('&', config.getString("Messages.discordsc").replaceAll("%user%", sender).replaceAll("%message%", message))));
	}
}
