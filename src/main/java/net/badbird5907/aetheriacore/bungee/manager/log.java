package net.badbird5907.aetheriacore.bungee.manager;

import net.badbird5907.aetheriacore.bungee.AetheriaCoreBungee;
import net.md_5.bungee.api.chat.TextComponent;

import static net.md_5.bungee.api.ProxyServer.getInstance;

public class log {
	public static AetheriaCoreBungee plugin;
	public static String prefix = "§8[§6AEC§8] ";
	public static String permissionmessage = "§cYou do not have permissions to do this!§8 §oNO_PERMISSIONS";

	public log(AetheriaCoreBungee plugin) {
		log.plugin = plugin;
	}

	public static void Log(String s) {
		getInstance().getConsole().sendMessage(new TextComponent(prefix + s));
	}

	public static void Warn(String s) {
		getInstance().getConsole().sendMessage(new TextComponent(prefix + s));
	}
}