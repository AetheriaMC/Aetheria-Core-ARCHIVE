package net.badbird5907.aetheriacore.spigot.events;

import de.myzelyam.api.vanish.PlayerShowEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static net.badbird5907.aetheriacore.spigot.manager.PluginManager.*;
import static org.bukkit.event.EventPriority.MONITOR;

public class onUnVanish implements Listener {
	@EventHandler(priority = MONITOR)
	public void onVanish(PlayerShowEvent event) {
		Player player = event.getPlayer();
		if (VanishedPlayers.contains(player.getName())) {
			VanishedPlayers.remove(player.getName());
			if (OnlineVisiblePlayers.contains(player.getName()))
				warn(prefix + "Player is somehow vaniahed but listed as unvanished on AEC. please report this to Badbird5907.");
			else OnlineVisiblePlayers.add(player.getName());
		}
	}
}