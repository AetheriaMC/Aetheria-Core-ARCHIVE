package net.badbird5907.aetheriacore.bungee.discord;

import static java.lang.Long.parseLong;
import static java.util.Objects.requireNonNull;
import static net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee.getJDA;

public class sendmsg {
	public static void sendmsg(String message, String channelID) {
		requireNonNull(getJDA().getTextChannelById(parseLong(channelID.replaceAll("L", "")))).sendMessage(message).queue();
	}
}