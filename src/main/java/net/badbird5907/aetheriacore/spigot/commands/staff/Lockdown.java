package net.badbird5907.aetheriacore.spigot.commands.staff;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

import static net.badbird5907.aetheriacore.spigot.manager.Permission.LOCKDOWN;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.PermissionMessage;
import static net.badbird5907.aetheriacore.spigot.util.KickAllNonStaff.KickAll;
import static org.bukkit.ChatColor.*;

public class Lockdown implements CommandExecutor {
	private static final String kickmsg = GOLD + "" + BOLD + "Aetheria\n \n" + RED + "This server is currently in lockdown mode.\n" + AQUA + "Reason: %reason%";
	public static Boolean IsLockdown;
	public static String LockdownSeverity, Reason, Servers;
	public static UUID WhoCalled;

	public static void LockDown(Player sender, String severity, String server_global, String reason) {
		if (severity.equalsIgnoreCase("low")) LowLockdown(sender, server_global, reason);
		if (severity.equalsIgnoreCase("medium")) MedLockdown(sender, server_global, reason);
		if (severity.equalsIgnoreCase("high"))
			sender.sendMessage("This command is a bungeecord command. Please make sure that bungeecord AetheriaCore is installed and enabled. You also must be a player online to have this work.");
	}

	private static void LowLockdown(Player sender, String server_global, String reason) {
		String kickmessage = kickmsg.replace("%reason%", reason);
		IsLockdown = true;
		LockdownSeverity = "LOW";
		Reason = reason;
		Servers = server_global;
		WhoCalled = sender.getUniqueId();
		KickAll(kickmessage);
		//IsLockdown.put("LOW", server_global.toUpperCase());
	}

	private static void MedLockdown(Player sender, String server_global, String reason) {
		String kickmessage = kickmsg.replace("%reason%", reason);
		IsLockdown = true;
		LockdownSeverity = "MEDIUM";
		Reason = reason;
		Servers = server_global;
		WhoCalled = sender.getUniqueId();
		KickAll(kickmessage);
		//IsLockdown.put("MEDIUM", server_global.toUpperCase());
	}

	//public static HashMap<String, String> IsLockdown = new HashMap<String, String>();
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		if ((sender instanceof Player) && (args.length == 2))
			if (args[0].equalsIgnoreCase("low") || args[0].equalsIgnoreCase("medium") || args[0].equalsIgnoreCase("high")) {
				if (args[1].equalsIgnoreCase("here") || args[1].equalsIgnoreCase("server") || args[1].equalsIgnoreCase("network") || args[1].equalsIgnoreCase("global"))
					if (sender.hasPermission(LOCKDOWN.node))
						LockDown((Player) sender, args[0], args[1], args[2].isEmpty() ? "not specified." : args[2]);
					else sender.sendMessage(PermissionMessage);
			} else
				sender.sendMessage(RED + "Error: please use a lockdown level. \n" + RED + "Usage: /lockdown <low/medium/high> <server/network>");
		return true;
	}
}