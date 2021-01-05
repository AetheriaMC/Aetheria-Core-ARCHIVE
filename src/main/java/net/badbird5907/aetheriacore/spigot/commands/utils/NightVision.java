package net.badbird5907.aetheriacore.spigot.commands.utils;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import static net.badbird5907.aetheriacore.spigot.manager.PluginManager.prefix;
import static org.bukkit.ChatColor.RED;
import static org.bukkit.potion.PotionEffectType.NIGHT_VISION;

public class NightVision implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission(permissionManager.NightVision)) {
				if (player.hasPotionEffect(NIGHT_VISION)) player.removePotionEffect(NIGHT_VISION);
				else player.addPotionEffect(new PotionEffect(NIGHT_VISION, 2147483647, 1, true));
				return true;
			}
		} else sender.sendMessage(prefix + RED + "You Must be a player to do this!");
		return true;
	}
}