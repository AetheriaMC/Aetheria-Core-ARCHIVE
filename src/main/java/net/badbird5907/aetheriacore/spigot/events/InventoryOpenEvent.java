package net.badbird5907.aetheriacore.spigot.events;

import net.badbird5907.aetheriacore.spigot.manager.permissionManager;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static java.util.Arrays.asList;
import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.ChatColor.DARK_GRAY;
import static org.bukkit.ChatColor.RED;
import static org.bukkit.Material.*;

public class InventoryOpenEvent implements Listener {

	@EventHandler
	public void InventoryOpenEvent(org.bukkit.event.inventory.InventoryOpenEvent event) {
		Material[] bannedItems = new Material[]{COMMAND_BLOCK, COMMAND_BLOCK_MINECART, CHAIN_COMMAND_BLOCK, REPEATING_COMMAND_BLOCK, BEDROCK, BARRIER, STRUCTURE_BLOCK, SPAWNER, DEBUG_STICK, JIGSAW};
		Player player = (Player) event.getPlayer();
		//Loop thru all elements
		for (Material m : bannedItems)
			//Check if in inventory
			//Check for perms; if no, remove
			if (player.getInventory().contains(m, 1))
				if (player.hasPermission(permissionManager.BypassItemBlacklist)) break;
				else {
					player.getInventory().remove(m);
					player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 10, 1);
					asList(DARK_GRAY + "--------------------------------------------", RED + "You had blacklisted items in your inventory! We had to remove it.", RED + "Please contact a staff member if you believe this is a mistake", RED + "MISSING PERMISSION NODE: aetheriacore.bypass.blacklistitems", RED + "ITEM REMOVED: " + m, DARK_GRAY + "--------------------------------------------").forEach(player::sendMessage);
					getLogger().warning("Blacklisted item (" + m + ") detected & deleted in " + player + "'s inventory");
					//TODO new alert system
					//StaffChatMessage.sendMessage("ItemBlacklist","Blacklisted item (" + m + ") detected & deleted in " + player + "'s inventory");
					return;
				}
	}
}