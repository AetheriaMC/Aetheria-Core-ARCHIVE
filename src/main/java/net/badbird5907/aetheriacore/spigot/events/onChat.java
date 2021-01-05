package net.badbird5907.aetheriacore.spigot.events;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.commands.utils.hush;
import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static net.badbird5907.aetheriacore.spigot.commands.staff.staffchat.staffchatToggle;
import static net.badbird5907.aetheriacore.spigot.manager.PluginManager.log;
import static org.bukkit.ChatColor.*;

public class onChat implements Listener {
	AetheriaCore plugin;

	public onChat(AetheriaCore plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void ChatListener(AsyncPlayerChatEvent event) {
		if (staffchatToggle.contains(event.getPlayer().getUniqueId())) {
			event.setCancelled(true);
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (player.hasPermission(permissionManager.staffchat)) {
					if (hush.hush.contains(player.getUniqueId())) break;
					else {
						player.sendMessage(GOLD + "StaffChat" + DARK_GRAY + " » " + RESET + event.getPlayer().getDisplayName() + ": " + event.getMessage());
						log("StaffChat » " + event.getPlayer().getDisplayName() + ": " + event.getMessage());
					}
				} else break;
			}
		} else if (plugin.getDataFile().getBoolean("mutechatstatus"))
			event.setCancelled(true);
	}
}