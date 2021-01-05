package net.badbird5907.aetheriacore.spigot.commands.management;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

import static net.badbird5907.aetheriacore.spigot.manager.DebugLogger.DebugLog;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.togglepvp;
import static org.bukkit.ChatColor.RED;

public class togglePvp implements CommandExecutor {
	AetheriaCore plugin;

	public togglePvp(AetheriaCore plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
		if (player.hasPermission(togglepvp) && (args.length == 0))
			if (plugin.getDataFile().getBoolean("pvp")) try {
				plugin.getDataFile().set("pvp", false);
			} catch (Exception e) {
				e.printStackTrace();
				player.sendMessage(RED + "Error changing PVP value. do /aec debug for more info.");
				DebugLog("Error: Could not change pvp value of data file. Stacktrace:");
				DebugLog(Arrays.toString(e.getStackTrace()));
			}
			else try {
				plugin.getDataFile().set("pvp", true);
			} catch (Exception e) {
				e.printStackTrace();
				player.sendMessage(RED + "Error changing PVP value. do /aec debug for more info.");
				DebugLog("Error: Could not change pvp value of data file. Stacktrace:");
				DebugLog(Arrays.toString(e.getStackTrace()));
			}
		return true;
	}
}