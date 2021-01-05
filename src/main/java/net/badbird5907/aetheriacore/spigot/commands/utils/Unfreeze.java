package net.badbird5907.aetheriacore.spigot.commands.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.badbird5907.aetheriacore.spigot.commands.utils.freezePlayer.frozen;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.PermissionMessage;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.freeze;
import static org.bukkit.Bukkit.getPlayerExact;
import static org.bukkit.ChatColor.RED;

public class Unfreeze implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (sender.hasPermission(freeze)) {
				if (method(sender, args)) return true;
			} else player.sendMessage(PermissionMessage);
			return true;
		} else if (method(sender, args)) return true;
		return true;
	}

	private boolean method(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(RED + "Usage: /unfreeze <Player>");
			return true;
		} else if (args.length <= 1) {
			Player target = getPlayerExact(args[0]);
			if (target != null) {
				if (!frozen.contains(target.getUniqueId())) {
					sender.sendMessage(RED + "Player " + target.getDisplayName() + " is not frozen!");
				} else {
					frozen.remove(target.getUniqueId());
					//TODO new alert system
					//StaffChatMessage.sendMessage("Aetheria Core ", ((Player) sender).getDisplayName() + " Has un-frozen " + target.getDisplayName() + " at coords: X: " + target.getLocation().getX() + " Y: " + target.getLocation().getY() + " Z: " + target.getLocation().getZ());
				}
				return true;
			}
			sender.sendMessage(RED + "Error:" + args[0] + "Is Not A Player!");
		}
		return false;
	}
}