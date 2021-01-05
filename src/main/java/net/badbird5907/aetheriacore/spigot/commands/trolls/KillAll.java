package net.badbird5907.aetheriacore.spigot.commands.trolls;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.KillAll;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.PermissionMessage;
import static org.bukkit.Bukkit.getOnlinePlayers;

public class KillAll implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		if (sender.hasPermission(KillAll)) getOnlinePlayers().forEach(player -> player.setHealth(0));
		else sender.sendMessage(PermissionMessage);
		return true;
	}
}