package net.badbird5907.aetheriacore.spigot.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static java.util.Arrays.asList;

public class CreateCustomItemMeta {

	// Nice little method to create a gui item with a custom name, and description
	public static ItemStack createGuiItem(final Material material, final String name, final String... lore) {
		final ItemStack item = new ItemStack(material, 1);
		final ItemMeta meta = item.getItemMeta();
		// Set the name of the item
		assert meta != null;
		meta.setDisplayName(name);
		// Set the lore of the item
		meta.setLore(asList(lore));
		item.setItemMeta(meta);
		return item;
	}
}
