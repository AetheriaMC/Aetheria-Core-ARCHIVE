package net.badbird5907.aetheriacore.spigot.events;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import static org.bukkit.entity.EntityType.PLAYER;

public class onPvp implements Listener {
	AetheriaCore plugin;
	String allowpvp = (String) plugin.getConfig().get("allowpvp");

	public onPvp(AetheriaCore plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPvp(EntityDamageByEntityEvent event) {
		if (event.getEntity().getType() == PLAYER && event.getDamager().getType() == PLAYER) {
			if (plugin.getDataFile().getBoolean("pvp")) return;
			event.setCancelled(true);
		}
	}
}