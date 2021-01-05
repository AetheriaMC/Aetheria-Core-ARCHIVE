package net.badbird5907.aetheriacore.bungee.discord.listeners;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static java.util.Objects.requireNonNull;
import static net.badbird5907.aetheriacore.bungee.api.SendAdminChatMessage.DiscordSend;
import static net.badbird5907.aetheriacore.bungee.manager.log.Log;
import static net.badbird5907.aetheriacore.bungee.util.Config.getData;

public class ACListener extends ListenerAdapter {
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if (event.getChannel().getIdLong() == Long.parseLong(requireNonNull(getData("bungeeconfig")).getString("Discord.adminchat").replaceAll("L", ""))) {
			if (event.getAuthor().isBot()) return;
			Message message = event.getMessage();
			String content = message.getContentRaw(), name = event.getAuthor().getName();
			DiscordSend(name, content);
			Log("Discord Admin Chat > " + name + ": " + content);
		}
	}
}