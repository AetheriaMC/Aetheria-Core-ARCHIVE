package net.badbird5907.aetheriacore.spigot.commands.management;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import static net.badbird5907.aetheriacore.spigot.manager.permissionManager.PermissionMessage;
import static org.bukkit.Bukkit.*;

public class queuerestart implements CommandExecutor {

	AetheriaCore plugin;

	public queuerestart(AetheriaCore plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		ConsoleCommandSender console = getServer().getConsoleSender();
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("aetheriacore.restart")) {
				broadcastMessage("§c§lSERVER REBOOT IN §a§l15§c§l SECONDS");
				//wait 15 seconds
				getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
					public void run() {
						broadcastMessage("§c§lSERVER REBOOT IN §e§l10§c§l SECONDS");
						dispatchCommand(console, "title @a title {\"text\":\"SERVER REBOOT\",\"bold\":true,\"color\":\"red\"}");
						dispatchCommand(console, "title @a subtitle [\"\",{\"text\":\"IN\",\"bold\":true,\"color\":\"red\"},{\"text\":\" 15\",\"bold\":true,\"color\":\"green\"},{\"text\":\" SECONDS\",\"bold\":true,\"color\":\"red\"}]");
					}
				}, 300, 20);
				getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
				});
			} else player.sendMessage(PermissionMessage);
		}
		return true;
	}
}