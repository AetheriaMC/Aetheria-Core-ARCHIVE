package net.badbird5907.aetheriacore.spigot.commands.utils;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static java.util.Objects.*;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.*;
import static org.bukkit.Bukkit.getPlayerExact;
import static org.bukkit.ChatColor.*;

public class GetViewDist implements CommandExecutor {
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
		if (args.length == 1) {
			Player target = getPlayerExact(args[0]);
			sender.sendMessage(sender.hasPermission(getviewdist) ? requireNonNull(target).getDisplayName() + "'s render distance is " + valueOf(target.getViewDistance()) : PermissionMessage);
		} else sender.sendMessage(RED + "USAGE: /getviewdistance <player>");
		return true;
	}
}