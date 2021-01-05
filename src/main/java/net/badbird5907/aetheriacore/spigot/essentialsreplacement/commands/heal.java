package net.badbird5907.aetheriacore.spigot.essentialsreplacement.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static java.util.Objects.requireNonNull;
import static net.badbird5907.aetheriacore.spigot.manager.Permission.HEAL;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.PermissionMessage;
import static org.bukkit.Bukkit.getPlayerExact;
import static org.bukkit.ChatColor.RED;
import static org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH;

public class heal implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		if (sender.hasPermission(HEAL.node)) {
			Player target = getPlayerExact(args[0]);
			if (target != null) {
				target.setHealth(requireNonNull(player.getAttribute(GENERIC_MAX_HEALTH)).getDefaultValue());
				player.setFireTicks(0);
			} else player.sendMessage(RED + "ERROR: Usage: /heal <Player>");
		} else player.sendMessage(PermissionMessage);
		return true;
	}
}