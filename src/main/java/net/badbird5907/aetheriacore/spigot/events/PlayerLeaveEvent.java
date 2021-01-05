package net.badbird5907.aetheriacore.spigot.events;

import net.badbird5907.aetheriacore.spigot.commands.utils.hush;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static net.badbird5907.aetheriacore.spigot.manager.PluginManager.OnlinePlayers;
import static net.badbird5907.aetheriacore.spigot.manager.PluginManager.warn;
import static org.bukkit.event.EventPriority.MONITOR;

public class PlayerLeaveEvent implements Listener {
	@EventHandler(priority = MONITOR)
	public void LeaveEvent(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		if (hush.hush.contains(player.getName())) hush.hush.remove(player.getName());
		else return;
		if (OnlinePlayers.contains(player.getName())) OnlinePlayers.remove(player.getName());
		else
			warn("Array List \"OnlinePlayers\" does not contain " + player.getDisplayName() + "did the server reload?");
	}
}