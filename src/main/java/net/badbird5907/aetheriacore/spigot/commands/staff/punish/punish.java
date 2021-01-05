package net.badbird5907.aetheriacore.spigot.commands.staff.punish;

import net.badbird5907.aetheriacore.spigot.manager.Permission;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getOfflinePlayer;

public class punish implements CommandExecutor {
	PunishGUI punishGUI = new PunishGUI();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		if (sender instanceof Player) if (sender.hasPermission(Permission.PUNISH.node) && (args.length == 1))
			punishGUI.PunishGUI((Player) sender, getOfflinePlayer(args[0]));
		else return true;
		return true;
	}
}