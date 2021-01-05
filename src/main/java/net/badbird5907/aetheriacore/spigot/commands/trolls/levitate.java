package net.badbird5907.aetheriacore.spigot.commands.trolls;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.Levitate;
import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.PermissionMessage;
import static org.bukkit.Bukkit.getPlayerExact;
import static org.bukkit.Bukkit.getServer;
import static org.bukkit.ChatColor.RED;
import static org.bukkit.potion.PotionEffectType.INVISIBILITY;

public class levitate implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		ConsoleCommandSender console = getServer().getConsoleSender();
		if (sender.hasPermission(Levitate)) {
			Player target = getPlayerExact(args[0]);
			if (target != null) target.addPotionEffect(new PotionEffect(INVISIBILITY, 2147483647, 1));
			else sender.sendMessage(RED + "Error: " + target.getDisplayName() + " Is Not A Player!");
		} else sender.sendMessage(PermissionMessage);
		return true;
	}
}