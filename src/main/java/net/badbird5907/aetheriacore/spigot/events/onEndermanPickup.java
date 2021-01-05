package net.badbird5907.aetheriacore.spigot.events;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class onEndermanPickup implements Listener {
	AetheriaCore plugin;

	public onEndermanPickup(AetheriaCore plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onEndermanPickup(EntityChangeBlockEvent event) {
		if (event.getEntity().getType().equals(EntityType.ENDERMAN) && plugin.getConfig().getBoolean("disable-enderman-pickup"))
			event.setCancelled(true);
	}
}