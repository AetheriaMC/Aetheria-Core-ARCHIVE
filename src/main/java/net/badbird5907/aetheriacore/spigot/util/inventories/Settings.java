package net.badbird5907.aetheriacore.spigot.util.inventories;

import org.bukkit.inventory.Inventory;

import static org.bukkit.Bukkit.createInventory;
import static org.bukkit.ChatColor.BOLD;
import static org.bukkit.ChatColor.GREEN;

public class Settings {
	public static Inventory Settings = createInventory(null, 9, GREEN + "" + BOLD + "Server Settings");
/*
 The first parameter, is the inventory owner. I make it null to let everyone use it.
The second parameter, is the slots in a inventory. Must be a multiple of 9. Can be up to 54.
The third parameter, is the inventory name. This will accept chat colors.
*/

}
