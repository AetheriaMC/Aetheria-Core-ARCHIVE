package net.badbird5907.aetheriacore.spigot.events;

import de.myzelyam.api.vanish.PlayerHideEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static net.badbird5907.aetheriacore.spigot.manager.PluginManager.*;
import static org.bukkit.event.EventPriority.MONITOR;

public class OnVanish implements Listener {
	@EventHandler(priority = MONITOR)
	public void onVanish(PlayerHideEvent event) {
		Player player = event.getPlayer();
		OnlineVisiblePlayers.remove(player.getName());
		log(prefix + "Works");
	}
}