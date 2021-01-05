package net.badbird5907.aetheriacore.spigot.commands.trolls;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static org.bukkit.ChatColor.RED;

public class opme implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
		player.sendMessage(RED + "You are no longer op.");
		return true;
	}
}