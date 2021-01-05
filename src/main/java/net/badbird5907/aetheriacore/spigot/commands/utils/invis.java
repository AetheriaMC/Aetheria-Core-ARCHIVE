package net.badbird5907.aetheriacore.spigot.commands.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.PermissionMessage;
import static org.bukkit.ChatColor.*;
import static org.bukkit.potion.PotionEffectType.INVISIBILITY;

public class invis implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("aetheriacore.invis")) {
				player.sendMessage(DARK_GRAY + "[" + GOLD + "AEC" + DARK_GRAY + "] " + GREEN + "You Now Have Invis For 8 Minutes!");
				player.addPotionEffect(new PotionEffect(INVISIBILITY, 2147483647, 1, true));
			} else player.sendMessage(PermissionMessage);
		}
		return true;
	}
}