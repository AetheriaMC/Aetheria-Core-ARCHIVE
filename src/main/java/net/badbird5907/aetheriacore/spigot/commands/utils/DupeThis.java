package net.badbird5907.aetheriacore.spigot.commands.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static java.util.Objects.requireNonNull;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.PermissionMessage;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.dupethis;
import static org.bukkit.Bukkit.getPlayer;

public class DupeThis implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		if (player.hasPermission(dupethis)) {
			ItemStack item = requireNonNull(getPlayer(sender.getName())).getInventory().getItemInMainHand();
			player.getInventory().addItem(item);
		} else player.sendMessage(PermissionMessage);
		return true;
	}
}