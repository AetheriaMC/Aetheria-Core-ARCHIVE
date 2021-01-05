package net.badbird5907.aetheriacore.spigot.commands.staff;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

public class QuickChat implements CommandExecutor {
	AetheriaCore plugin;

	public QuickChat(AetheriaCore plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
		String allArgs = stream(args).map(arg -> arg + " ").collect(joining()).trim();
		if (player.hasPermission(permissionManager.staffchat)) {
			//StaffChatMessage.sendMessage(player.getName(), allArgs);
		} else player.sendMessage(permissionManager.PermissionMessage);
		return true;
	}
}