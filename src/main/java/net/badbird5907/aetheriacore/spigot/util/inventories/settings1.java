package net.badbird5907.aetheriacore.spigot.util.inventories;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;
import static org.bukkit.Bukkit.createInventory;
import static org.bukkit.Material.*;

public class settings1 implements Listener {
	private Inventory inv;

	public settings1(Inventory inv) {
		this.inv = inv;
	}

	public void ExampleGui() {
		// Create a new inventory, with no owner (as this isn't a real inventory), a size of nine, called example
		inv = createInventory(null, 9, "Settings");

		// Put the items into the inventory
		initializeItems();
	}

	// You can call this whenever you want to put the items in
	public void initializeItems() {
		inv.addItem(createGuiItem(DIAMOND_SWORD, "§c§lEnable PVP", "§cAllow Players To Fight", "§7aetheriacore.togglepvp"));
		inv.addItem(createGuiItem(WOODEN_SWORD, "§a§lDisable PVP", "§aDisAllow Players To Fight", "§7aetheriacore.togglepvp"));
	}

	// Nice little method to create a gui item with a custom name, and description
	protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
		final ItemStack item = new ItemStack(material, 1);
		final ItemMeta meta = item.getItemMeta();
		// Set the name of the item
		requireNonNull(meta).setDisplayName(name);
		// Set the lore of the item
		meta.setLore(asList(lore));
		item.setItemMeta(meta);
		return item;
	}

	// You can open the inventory with this
	public void openInventory(final HumanEntity ent) {
		ent.openInventory(inv);
	}

	// Check for clicks on items
	@EventHandler
	public void onInventoryClick(final InventoryClickEvent e) {
		if (e.getInventory() != inv) return;
		e.setCancelled(true);
		final ItemStack clickedItem = e.getCurrentItem();
		// verify current item is not null
		if ((clickedItem == null) || (clickedItem.getType() == AIR)) return;
		final Player p = (Player) e.getWhoClicked();
		// Using slots click is a best option for your inventory click's
		if (clickedItem.getType() == DIAMOND_SWORD && p.hasPermission("aetheriacore.togglepvp")) {

		}
	}

	// Cancel dragging in our inventory
	@EventHandler
	public void onInventoryClick(final InventoryDragEvent e) {
		if (e.getInventory() == inv) e.setCancelled(true);
	}
}