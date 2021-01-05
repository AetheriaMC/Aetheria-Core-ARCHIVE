package net.badbird5907.aetheriacore.spigot.events;

import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import static org.bukkit.entity.EntityType.CREEPER;

public class onCreeperExplosion implements Listener {
	public void onCreeperExplosion(EntityExplodeEvent event) {
		Entity entity = event.getEntity();
		if (entity.getType().equals(CREEPER)) event.setYield(1);
	}
}