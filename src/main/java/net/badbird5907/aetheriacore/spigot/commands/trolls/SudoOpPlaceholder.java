package net.badbird5907.aetheriacore.spigot.commands.trolls;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static net.badbird5907.aetheriacore.spigot.manager.PluginManager.prefix;
import static org.bukkit.ChatColor.RED;

public class SudoOpPlaceholder implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		sender.sendMessage(prefix + RED + "This command still has some bugs and may crash the server.");
		sender.sendMessage(RED + "So it is disabled.");
		return true;
	}
}