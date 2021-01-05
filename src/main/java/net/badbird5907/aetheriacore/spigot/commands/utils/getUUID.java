package net.badbird5907.aetheriacore.spigot.commands.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;

public class getUUID implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
		Player target = Bukkit.getPlayerExact(args[0]);
		player.sendMessage((target != null) ? (GREEN + target.getDisplayName() + "'s UUID is:" + BLUE + target.getUniqueId()) : (RED + "Error: Invalid Arguments. Use: /getuuid <Player>" + DARK_GRAY + " " + ITALIC + "INVALID_ARGUMENTS"));
		return true;
	}
}