package net.badbird5907.aetheriacore.spigot.util.inventories;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

import static net.badbird5907.aetheriacore.spigot.util.inventories.itemmenuinv.inv1;
import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.Material.*;

public class ClickListener implements Listener {

	@EventHandler
	public void onInventoryClick(final InventoryClickEvent e) {

		if (e.getInventory() != inv1) return;
		e.setCancelled(true);
		final ItemStack clickedItem = e.getCurrentItem();
		// verify current item is not null
		if (clickedItem == null || clickedItem.getType() == AIR) return;
		final Player p = (Player) e.getWhoClicked();
		// Using slots click is a best option for your inventory click's
		if (clickedItem.getType() == NETHERITE_SWORD) p.sendMessage(GREEN + "Weapons");
		if (clickedItem.getType() == NETHERITE_CHESTPLATE) p.sendMessage(GREEN + "Armour");
		if (clickedItem.getType() == COBBLESTONE) p.sendMessage(GREEN + "Blocks");
		if (clickedItem.getType() == PIG_SPAWN_EGG) p.sendMessage(GREEN + "Animals");
		if (clickedItem.getType() == NAME_TAG) p.sendMessage(GREEN + "Search");

	}

	// Cancel dragging in our inventory
	@EventHandler
	public void onInventoryClick(final InventoryDragEvent e) {
		if (e.getInventory() == inv1) e.setCancelled(true);
	}
}