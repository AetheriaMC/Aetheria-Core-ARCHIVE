package net.badbird5907.aetheriacore.spigot.events;

import net.badbird5907.aetheriacore.spigot.AetheriaCore;
import net.badbird5907.aetheriacore.spigot.manager.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static net.badbird5907.aetheriacore.spigot.manager.Permission.*;
import static org.bukkit.Bukkit.*;
import static org.bukkit.ChatColor.*;
import static org.bukkit.Material.*;
import static org.bukkit.Sound.*;

public class LegacyBlacklisteditems implements Listener {
	AetheriaCore plugin;

	public LegacyBlacklisteditems(AetheriaCore plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		/**
		 * @Deceprecated Do not use, causes insane lag as it loops through all items and for each player.
		 */
		Material[] bannedItems = new Material[]{COMMAND_BLOCK, COMMAND_BLOCK_MINECART, CHAIN_COMMAND_BLOCK, REPEATING_COMMAND_BLOCK, BEDROCK, BARRIER,
				STRUCTURE_BLOCK, SPAWNER, DEBUG_STICK, JIGSAW, BAT_SPAWN_EGG, BEE_SPAWN_EGG, BLAZE_SPAWN_EGG, CAT_SPAWN_EGG, CAVE_SPIDER_SPAWN_EGG,
				CHICKEN_SPAWN_EGG, COD_SPAWN_EGG, COW_SPAWN_EGG, CREEPER_SPAWN_EGG, DOLPHIN_SPAWN_EGG, DONKEY_SPAWN_EGG, DROWNED_SPAWN_EGG,
				ELDER_GUARDIAN_SPAWN_EGG, ENDERMAN_SPAWN_EGG, ENDERMITE_SPAWN_EGG, EVOKER_SPAWN_EGG, FOX_SPAWN_EGG, GHAST_SPAWN_EGG, GUARDIAN_SPAWN_EGG, HOGLIN_SPAWN_EGG,
				HORSE_SPAWN_EGG, HUSK_SPAWN_EGG, LLAMA_SPAWN_EGG, MAGMA_CUBE_SPAWN_EGG, MOOSHROOM_SPAWN_EGG, MULE_SPAWN_EGG, OCELOT_SPAWN_EGG, PANDA_SPAWN_EGG, PARROT_SPAWN_EGG,
				PARROT_SPAWN_EGG, PHANTOM_SPAWN_EGG, PIG_SPAWN_EGG, PIGLIN_SPAWN_EGG, PILLAGER_SPAWN_EGG, POLAR_BEAR_SPAWN_EGG, PUFFERFISH, RABBIT_SPAWN_EGG, RAVAGER_SPAWN_EGG,
				SALMON_SPAWN_EGG, SHEEP_SPAWN_EGG, SHULKER_SPAWN_EGG, SILVERFISH_SPAWN_EGG, SKELETON_SPAWN_EGG, SKELETON_HORSE_SPAWN_EGG, SLIME_SPAWN_EGG, SPIDER_SPAWN_EGG, SQUID_SPAWN_EGG,
				STRAY_SPAWN_EGG, STRIDER_SPAWN_EGG, TRADER_LLAMA_SPAWN_EGG, TROPICAL_FISH_SPAWN_EGG, TURTLE_SPAWN_EGG, VEX_SPAWN_EGG, VILLAGER_SPAWN_EGG, VINDICATOR_SPAWN_EGG, WANDERING_TRADER_SPAWN_EGG,
				WITCH_SPAWN_EGG, WITHER_SKELETON_SPAWN_EGG, WOLF_SPAWN_EGG, ZOMBIE_SPAWN_EGG, ZOMBIFIED_PIGLIN_SPAWN_EGG, ZOMBIE_HORSE_SPAWN_EGG, ZOMBIE_VILLAGER_SPAWN_EGG, ZOGLIN_SPAWN_EGG,

		};
		Player player = (Player) event.getWhoClicked();

		for (Material m : bannedItems) { //Loop thru all elements
			//Check if in inventory
			//Check for perms; if no, remove
			if (player.getInventory().contains(m, 1)) if (player.hasPermission(BYPASS_ITEM_BLACKLIST.node)) break;
			else if (plugin.getConfig().getBoolean("enableblacklistitems")) {
				player.getInventory().remove(m);
				player.playSound(player.getLocation(), ENTITY_ENDERMAN_TELEPORT, 10, 1);
				player.sendMessage(DARK_GRAY + "--------------------------------------------");
				player.sendMessage(RED + "You had blacklisted items in your inventory! We had to remove it.");
				player.sendMessage(RED + "Please contact a staff member if you believe this is a mistake.");
				player.sendMessage(RED + "ITEM REMOVED: " + m);
				player.sendMessage(DARK_GRAY + "--------------------------------------------");
				getLogger().warning("Blacklisted item (" + m + ") detected & deleted in " + player.getDisplayName() + "'s inventory");
				//loop through all players and see if they are staff
				getOnlinePlayers().stream().filter(loopplayer -> loopplayer.hasPermission("aetheriacore.blacklistitems.viewlogs")).forEach(loopplayer -> player.sendMessage(RED + "Blacklisted item " + BOLD + "(" + m + ")" + RESET + " " + RED + "detected & deleted in " + BOLD + player.getDisplayName() + RESET + "" + RED + "'s inventory"));

			} else break;
		}
	}
}