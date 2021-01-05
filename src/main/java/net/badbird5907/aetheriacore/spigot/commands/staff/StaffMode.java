package net.badbird5907.aetheriacore.spigot.commands.staff;

import net.badbird5907.aetheriacore.spigot.commands.utils.hush;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static de.myzelyam.api.vanish.VanishAPI.hidePlayer;
import static de.myzelyam.api.vanish.VanishAPI.showPlayer;
import static net.badbird5907.aetheriacore.spigot.commands.staff.staffchat.staffchatToggle;
import static net.badbird5907.aetheriacore.spigot.manager.PluginManager.prefix;
import static org.bukkit.ChatColor.*;

public class StaffMode implements CommandExecutor {
	public static List<UUID> StaffModeToggle = new ArrayList<UUID>();

	@Override
	public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
		if (player instanceof Player) {
			if (player.hasPermission(permissionManager.StaffMode)) {
				if (StaffModeToggle.contains(((Player) player).getUniqueId())) {
					StaffModeToggle.remove(((Player) player).getUniqueId());
					showPlayer((Player) player);
					player.sendMessage(prefix + WHITE + "Staff Mode Toggled " + RED + "OFF");
				} else {
					player.sendMessage(prefix + WHITE + "Staff Mode Turned " + GREEN + "ON");
					staffchatToggle.add(((Player) player).getUniqueId());
					StaffModeToggle.add(((Player) player).getUniqueId());
					hush.hush.remove(((Player) player).getUniqueId());
					hidePlayer((Player) player);
				}
				return true;
			}
			player.sendMessage(permissionManager.PermissionMessage);
		} else player.sendMessage(prefix + RED + "You must be a player to use this.");
		return true;
	}
}