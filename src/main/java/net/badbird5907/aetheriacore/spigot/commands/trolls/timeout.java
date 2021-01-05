package net.badbird5907.aetheriacore.spigot.commands.trolls;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import static java.util.Objects.requireNonNull;
import static org.bukkit.Bukkit.getPlayerExact;

public class timeout implements CommandExecutor {
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
		if (sender.hasPermission(permissionManager.timeout))
			requireNonNull(getPlayerExact(args[0])).kickPlayer("Timed Out");
		return true;
	}
}