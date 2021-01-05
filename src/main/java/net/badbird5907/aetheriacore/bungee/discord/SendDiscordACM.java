package net.badbird5907.aetheriacore.bungee.discord;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import static java.util.Objects.requireNonNull;
import static net.badbird5907.aetheriacore.bungee.discord.sendmsg.*;
import static net.badbird5907.aetheriacore.bungee.util.Config.getData;
import static net.badbird5907.aetheriacore.bungee.util.PlayerHandler.RankName;

public class SendDiscordACM {
	public static void send(ProxiedPlayer player, String message) {
		sendmsg("```css\n[" + RankName(player) + "] " + player.getDisplayName() + " (" + player.getServer().getInfo().getName() + ")" + ": " + message + "\n```", requireNonNull(getData("bungeeconfig")).getString("Discord.adminchat"));
	}
}
