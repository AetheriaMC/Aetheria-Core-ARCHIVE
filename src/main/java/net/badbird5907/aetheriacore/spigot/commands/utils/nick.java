package net.badbird5907.aetheriacore.spigot.commands.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static java.util.Objects.requireNonNull;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.PermissionMessage;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.nick;
import static org.bukkit.Bukkit.getPlayerExact;
import static org.bukkit.ChatColor.GREEN;

public class nick implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		Player player = getPlayerExact(sender.getName());
		if (sender.hasPermission(nick)) {
			requireNonNull(player).setDisplayName(args[0]);
			player.sendMessage(GREEN + "Nick changed to " + args[0]);
		} else requireNonNull(player).sendMessage(PermissionMessage);
		return true;
	}
}