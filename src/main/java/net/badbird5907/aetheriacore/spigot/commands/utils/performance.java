package net.badbird5907.aetheriacore.spigot.commands.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static java.lang.Math.round;
import static java.lang.Runtime.getRuntime;
import static java.lang.System.getProperty;
import static java.lang.management.ManagementFactory.getOperatingSystemMXBean;
import static java.util.Arrays.asList;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.Performance;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.PermissionMessage;
import static net.badbird5907.aetheriacore.spigot.other.Lag.getTPS;
import static org.bukkit.Bukkit.getOnlinePlayers;
import static org.bukkit.Bukkit.getServer;
import static org.bukkit.ChatColor.*;

public class performance implements CommandExecutor {
	Runtime r = getRuntime();
	long memUsed = (r.totalMemory() - r.freeMemory()) >> 20;

	@Override
	public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
		if (player.hasPermission(Performance)) {
			asList(GREEN + "Server: " + getServer().getName(), GREEN + "OS: " + getProperty("os.name"), getTPS() > 20 ? GOLD + "Tps: 20.00" : GOLD + "Tps: " + (round(getTPS() * 100.0) / 100.0), cpuUsageBoolean() ? (GOLD + "Cpu Usage: " + getOperatingSystemMXBean().getSystemLoadAverage()) : (GOLD + "Cpu Usage: " + RED + "Not Supported")).forEach(player::sendMessage);
			//player.sendMessage(ChatColor.GOLD + "Cpu Usage: " + cpuUsageBoolean());
			asList(GOLD + "RAM Usage: " + memUsed + "/" + r.totalMemory() / 1048576, GOLD + "Players: " + getOnlinePlayers().size()).forEach(player::sendMessage);
			//player.sendMessage(ChatColor.GREEN + "NOTE: If the tps (" + Lag.getTPS() + ") is over 20, round it down to 20");
		} else player.sendMessage(PermissionMessage);
		return true;
	}

	private boolean cpuUsageBoolean() {
		return getOperatingSystemMXBean().getSystemLoadAverage() != -1;
	}
}