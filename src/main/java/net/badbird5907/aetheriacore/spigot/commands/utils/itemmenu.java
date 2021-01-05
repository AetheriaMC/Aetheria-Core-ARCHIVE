package net.badbird5907.aetheriacore.spigot.commands.utils;

import net.badbird5907.aetheriacore.spigot.util.inventories.itemmenuinv;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static java.util.Objects.requireNonNull;
import static net.badbird5907.aetheriacore.spigot.manager.Permission.ITEM;
import static org.bukkit.Bukkit.getPluginManager;
import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

public class itemmenu implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		if (getPluginManager().isPluginEnabled("AetheriaItems")) {
			if (sender instanceof Player) requireNonNull(((Player) sender).getPlayer()).chat("/aetheriaitems:itemmenu");
		} else if (sender.hasPermission(ITEM.node)) {
			if (sender instanceof Player) {
				if (args.length == 0) {
					Player player = ((Player) sender);
					sender.sendMessage(GREEN + "Attempting To Open The GUI...");
					new itemmenuinv().open(player);
				}
			} else sender.sendMessage(RED + "ERROR: You need to be a player to execute this");
		} else sender.sendMessage(RED + "Error: You do not have permission to do this!");
		return true;
	}
}