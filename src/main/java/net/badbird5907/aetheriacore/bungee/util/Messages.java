package net.badbird5907.aetheriacore.bungee.util;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.TRUE;
import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;
import static net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee.getInstance;
import static net.md_5.bungee.config.ConfigurationProvider.getProvider;

public class Messages {
	public static void createFile(String fileName) {
		if (!getInstance().getDataFolder().exists())
			getInstance().getDataFolder().mkdir();
		File file = new File(getInstance().getDataFolder(), fileName + ".yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
				if (fileName.equals("bungeemessages")) {
					Configuration config = getConfig(fileName);
					asList("Config.enable-join-message", "Config.enable-leave-message", "Config.enable-switch-messages").forEach(s -> requireNonNull(config).set(s, TRUE));
					requireNonNull(config).set("Messages.sc-hover", "&8Player: %player%\nServer: %server%");
					config.set("Messages.sc-enabled", "&8[&6AEC&8] &8&7Staff chat is now &aenabled&7.");
					config.set("Messages.sc-disabled", "&8[&6AEC&8] &8&7Staff chat is now &cdisabled&7.");
					config.set("Messages.sc-format", "&6&lStaffChat&r&8 »&r&7 (%server%)&r %player%&r: %message%");
					config.set("Messages.ac-enabled", "&8[&6AEC&8] &8&7Admin chat is now &aenabled&7.");
					config.set("Messages.ac-disabled", "&8[&6AEC&8] &8&7Admin chat is now &cdisabled&7.");
					config.set("Messages.ac-format", "&c&lAdminChat&r&8 »&r&7 (%server%)&r %player%&r: %message%");
					config.set("Messages.cspy-enabled", "&8[&6AEC&8] &8&7Command spy is now &aenabled&7.");
					config.set("Messages.cspy-disabled", "&8[&6AEC&8] &8&7Command spy is now &cdisabled&7.");
					config.set("Messages.cspy-format", "&9&lCommandSpy &8» &7(%server%) &r%user%: %command%");
					config.set("Messages.no-permission", "&cYou do not have permissions to do this!&8 &oNO_PERMISSIONS");
					config.set("Messages.helpop-no-message", "&c&lUsage: /gethelp <message>");
					config.set("Messages.clearchat-bypass", "&aChat was cleared by %player% but you're immune");
					config.set("Messages.clearchat", "&aChat was cleared globally by %player%");
					config.set("Messages.discordsc", "&6&lDiscordSC&r&8 »&r%user%: %message%");
					config.set("Messages.discordac", "&c&lDiscordAC&r&8 »&r%user%: %message%");
					config.set("Messages.global-lockdown", "A &c&lGLOBAL&r lockdown has been called by %player%. &r All non staff players have been kicked.");
					config.set("Messages.already-lockdown", "&cNetwork is already under lockdown. do /bungeeendlockdown to end it.");
					config.set("Messages.plugins", "&bPlugins we use:\n&aAetheriaCore&r, &aAetheriaItems&r, &aAetheria-Anti-Cheat");
					List<String> list = new ArrayList<>(asList("&7&m-----------------------------------", "&7----&r&9&lReport&r&7----", "&9Reporter: &e%reporter% &7(%reporter-server%)", "&9Reported: &e%reported% &7(%reported-server%)", "&9Reason: &e%reason%"));
					list.add("&7&m-----------------------------------");
					config.set("Messages.report-to-staff-message", list);
					config.set("Messages.report-to-player-message", "&8[&6AEC&8] &8&aYou have reported: &e%player%&a, &afor &athe &areason: &e%reason%&7.");
					config.set("Messages.report-incorrect-format", "&8[&6AEC&8] &cUsage: /report <player> <reason>");
					config.set("Messages.report-offline-player", "&8[&6AEC&8] &8&cThat player is currently offline.");
					config.set("Messages.staff-join-network", "&aStaff &8» &f%player% &aConnected");
					config.set("Messages.staff-leave-network", "&aStaff &8» &f%player% &cDisconnected");
					config.set("Messages.staff-switch-server", "&aStaff &8 » &f%player% &aSwitched from %from% to %to%");
					List<String> list2 = new ArrayList<>(asList("&7&m-----------------------------------", "&c&l----Help Requested----", "&e%player% &7(%server%)", "&eMessage: %msg%"));
					list2.add("&7&m-----------------------------------");
					config.set("Messages.helpop-to-staff", list2);
					config.set("Messages.helpop-to-player", "&8[&6AEC&8] &8&7Requested help from staff: &c%msg%7.");
					config.set("Messages.in-cooldown", "&9You are still on cooldown: &7%seconds% left.");
					saveConfig(config, fileName);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static Configuration getConfig(String fileName) {
		try {
			return getProvider(YamlConfiguration.class).load(new File(getInstance().getDataFolder(), fileName + ".yml"));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void saveConfig(Configuration config, String fileName) {
		try {
			getProvider(YamlConfiguration.class).save(config, new File(getInstance().getDataFolder(), fileName + ".yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
