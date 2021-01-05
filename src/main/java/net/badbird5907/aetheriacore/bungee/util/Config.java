package net.badbird5907.aetheriacore.bungee.util;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static java.util.Objects.requireNonNull;
import static net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee.getInstance;
import static net.md_5.bungee.config.ConfigurationProvider.getProvider;

public class Config {
	public static void createFile(String fileName) {
		if (!getInstance().getDataFolder().exists()) getInstance().getDataFolder().mkdir();
		File file = new File(getInstance().getDataFolder(), fileName + ".yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
				if (fileName.equals("bungeeconfig")) {
					Configuration config = getData(fileName);
					requireNonNull(config).set("Database.uri", "URI");
					config.set("Database.password", "PASSWORD");
					config.set("Database.username", "USERNAME");
					config.set("Database.port", "3306");
					config.set("Database.name", "NAME_HERE");

					config.set("Discord.staffchat", "784502009876578344L");
					config.set("Discord.adminchat", "784502023650672640L");
					config.set("Discord.reports", "784502051731406888L");
					config.set("Discord.spy", "784502122770464778L");
					config.set("Discord.token", "TOKEN_HERE");
					saveData(config, fileName);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static Configuration getData(String fileName) {
		try {
			return getProvider(YamlConfiguration.class).load(new File(getInstance().getDataFolder(), fileName + ".yml"));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void saveData(Configuration config, String fileName) {
		try {
			getProvider(YamlConfiguration.class).save(config, new File(getInstance().getDataFolder(), fileName + ".yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}