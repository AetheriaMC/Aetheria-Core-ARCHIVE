package net.badbird5907.aetheriacore.spigot.commands.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static java.util.Objects.requireNonNull;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.PermissionMessage;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.getclientbrand;
import static org.bukkit.Bukkit.getPlayerExact;
import static org.bukkit.ChatColor.RED;

public class GetClientBrand implements CommandExecutor {
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
		if (args.length == 1) {
			Player target = getPlayerExact(args[0]);
			sender.sendMessage(sender.hasPermission(getclientbrand) ? requireNonNull(target).getDisplayName() + "'s client brand is " + target.getClientBrandName() : PermissionMessage);
		} else sender.sendMessage(RED + "USAGE: /getclientbrand <player>");
		return true;

	}
}