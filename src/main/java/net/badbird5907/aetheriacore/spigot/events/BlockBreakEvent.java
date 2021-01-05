package net.badbird5907.aetheriacore.spigot.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static net.badbird5907.aetheriacore.spigot.commands.utils.freezePlayer.frozen;

public class BlockBreakEvent implements Listener {
	@EventHandler
	public void Blockbreakevent(org.bukkit.event.block.BlockBreakEvent event) {
		if (frozen.contains(event.getPlayer().getUniqueId())) event.setCancelled(true);
	}
}