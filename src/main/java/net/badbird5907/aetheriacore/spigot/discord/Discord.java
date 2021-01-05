package net.badbird5907.aetheriacore.spigot.discord;

import net.badbird5907.aetheriacore.bungee.discord.listeners.ACListener;
import net.badbird5907.aetheriacore.bungee.discord.listeners.SCListener;
import net.dv8tion.jda.api.JDA;
import net.md_5.bungee.config.Configuration;

import javax.security.auth.login.LoginException;

import static java.util.Objects.requireNonNull;
import static net.badbird5907.aetheriacore.bungee.util.Config.getData;
import static net.badbird5907.aetheriacore.spigot.AetheriaCore.getInstance;
import static net.dv8tion.jda.api.JDABuilder.createDefault;

public class Discord {
	public static void init() {
		Configuration conf1 = getData("bungeeconfig");
		try {
			JDA jda = createDefault(requireNonNull(conf1).getString("Discord.token")).addEventListeners(new SCListener()).addEventListeners(new ACListener()).build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
	}

	public static JDA getJDA() {
		return getInstance().jda;
	}
}