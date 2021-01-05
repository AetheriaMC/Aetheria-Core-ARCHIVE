package net.badbird5907.aetheriacore.spigot.commands.utils;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static net.badbird5907.aetheriacore.spigot.AetheriaCore.getInstance;
import static net.badbird5907.aetheriacore.spigot.manager.DebugLogger.DebugLog;
import static net.badbird5907.aetheriacore.spigot.manager.Permission.MUTE_CHAT;

public class mutechat implements CommandExecutor {
	AetheriaCore plugin;

	public mutechat(AetheriaCore plugin) {
		this.plugin = plugin;
	}

	public static boolean IsChatMuted() {
		return getInstance().getDataFile().getBoolean("mutechatstatus");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		if (sender.hasPermission(MUTE_CHAT.node) && (args.length == 0)) {
			if (plugin.getDataFile().getBoolean("mutechatstatus")) try {
				plugin.getDataFile().set("mutechatstatus", false);
				plugin.getDataFile().save(plugin.customConfigFile);
			} catch (Exception e) {
				e.printStackTrace();
				DebugLog(e.toString());
			}
			if (!plugin.getDataFile().getBoolean("mutechatstatus")) try {
				plugin.getDataFile().set("mutechatstatus", true);
				plugin.getDataFile().save(plugin.customConfigFile);
			} catch (Exception e) {
				e.printStackTrace();
				DebugLog(e.toString());
			}
		}
		return true;
	}
}