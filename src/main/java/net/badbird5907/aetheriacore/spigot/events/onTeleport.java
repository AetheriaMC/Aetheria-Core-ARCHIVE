package net.badbird5907.aetheriacore.spigot.events;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class onTeleport implements Listener {
	@EventHandler
	public void onTeleport(PlayerTeleportEvent event) {
		Player player = event.getPlayer();
		if (player.hasPermission(permissionManager.teleportparticles)) {

		}
	}
}