package net.badbird5907.aetheriacore.spigot.essentialsreplacement.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.badbird5907.aetheriacore.spigot.manager.Permission.GOD;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.PermissionMessage;
import static org.bukkit.Bukkit.getPlayerExact;
import static org.bukkit.ChatColor.RED;

public class god implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		if (sender.hasPermission(GOD.node)) {
			Player target = getPlayerExact(args[0]);
			if (target != null) target.setInvulnerable(true);
			else player.sendMessage(RED + "ERROR: Usage: /god <Player>");
		} else player.sendMessage(PermissionMessage);
		return true;
	}
}