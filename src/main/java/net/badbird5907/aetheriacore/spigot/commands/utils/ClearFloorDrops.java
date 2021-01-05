package net.badbird5907.aetheriacore.spigot.commands.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

import static net.badbird5907.aetheriacore.spigot.manager.PluginManager.prefix;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.ClearDrops;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.PermissionMessage;
import static org.bukkit.Bukkit.broadcastMessage;
import static org.bukkit.Bukkit.getWorlds;
import static org.bukkit.ChatColor.RED;
import static org.bukkit.ChatColor.WHITE;

public class ClearFloorDrops implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
		if (player.hasPermission(ClearDrops)) {
			if (args.length == 0) {
				player.sendMessage(prefix + RED + "Usage: /clearfloordrops <World> (For World: This, All)");
				return true;
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("all")) {
					int removed_entities = 0;
					getWorlds().forEach(w -> w.getEntities().forEach(Entity::getType));
					broadcastMessage(prefix + removed_entities + " Entites Removed.");
					player.sendMessage(prefix + WHITE + "Drops Cleared!");
				}
				if (args[0].equalsIgnoreCase("this")) {
					int removed_entities = 0;
					broadcastMessage(prefix + removed_entities + " Entites Removed.");
					player.sendMessage(prefix + WHITE + "Drops Cleared!");
				}
			}
		} else player.sendMessage(PermissionMessage);
		return true;
	}
}