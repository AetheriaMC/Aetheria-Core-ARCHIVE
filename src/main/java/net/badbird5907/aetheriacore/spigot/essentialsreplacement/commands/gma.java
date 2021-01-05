package net.badbird5907.aetheriacore.spigot.essentialsreplacement.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.badbird5907.aetheriacore.spigot.manager.Permission.GMA;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.PermissionMessage;
import static org.bukkit.Bukkit.getPlayerExact;
import static org.bukkit.ChatColor.RED;
import static org.bukkit.GameMode.ADVENTURE;


public class gma implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		if (sender.hasPermission(GMA.node)) {
			Player target = getPlayerExact(args[0]);
			if (target != null) {
				player.setGameMode(ADVENTURE);
			} else {
				player.sendMessage(RED + "ERROR: Usage: /gma <Player>");
			}
		} else sender.sendMessage(PermissionMessage);
		return true;
	}
}